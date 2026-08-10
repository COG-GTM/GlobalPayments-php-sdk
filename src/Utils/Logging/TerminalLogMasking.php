<?php

namespace GlobalPayments\Api\Utils\Logging;

use GlobalPayments\Api\Terminals\Enums\ControlCodes;

/**
 * Removes cardholder data from terminal messages before they are written to a log.
 *
 * Primary account numbers keep at most the first six and the last four digits.
 * Card verification values, expiration dates and track data are never logged.
 */
class TerminalLogMasking
{
    const MASK_SYMBOL = 'X';

    const REDACTED = '[REDACTED]';

    const UNMASKED_FIRST_CHARS = 6;

    const UNMASKED_LAST_CHARS = 4;

    /**
     * Sensitive keys used by the JSON based terminal messages (UPA, Diamond).
     */
    const SENSITIVE_KEYS = [
        'cvv', 'cvv2', 'cvn', 'cvc', 'cid', 'securityCode', 'cardSecurityCode',
        'expiry', 'expiryDate', 'expd', 'expMonth', 'expYear', 'expirationDate',
        'track', 'track1', 'track2', 'track3', 'trackData', 'trackdata', 'pin', 'pinBlock'
    ];

    /**
     * @param mixed $message
     * @return mixed the message with all cardholder data masked or redacted
     */
    public static function maskMessage($message)
    {
        if (!is_string($message) || $message === '') {
            return $message;
        }

        $message = self::redactTrackData($message);
        $message = self::maskAccountSubGroup($message);
        $message = self::redactSensitiveKeys($message);

        return self::maskAccountNumbers($message);
    }

    /**
     * Masks a primary account number, keeping at most first six / last four digits.
     *
     * @param string $accountNumber
     * @return string
     */
    public static function maskAccountNumber(string $accountNumber) : string
    {
        $length = strlen($accountNumber);
        if ($length <= self::UNMASKED_FIRST_CHARS + self::UNMASKED_LAST_CHARS) {
            return str_repeat(self::MASK_SYMBOL, $length);
        }

        return substr($accountNumber, 0, self::UNMASKED_FIRST_CHARS)
            . str_repeat(self::MASK_SYMBOL, $length - self::UNMASKED_FIRST_CHARS - self::UNMASKED_LAST_CHARS)
            . substr($accountNumber, -self::UNMASKED_LAST_CHARS);
    }

    /**
     * Track 1 and track 2 data must never be retained, so it is dropped entirely.
     */
    private static function redactTrackData(string $message) : string
    {
        $patterns = [
            '/%B\d{1,19}\^[^?]*\?/',
            '/;\d{1,19}=[^?]*\?/'
        ];

        return preg_replace($patterns, self::REDACTED, $message);
    }

    /**
     * Masks the PAX account sub group: accountNumber US expd US cvvCode.
     *
     * The sub group is masked as a unit so the expiration date and the CVV are
     * removed even when they are shorter than a stand alone match would allow.
     */
    private static function maskAccountSubGroup(string $message) : string
    {
        $us = preg_quote(chr(ControlCodes::US), '/');
        $pattern = '/(?<!\d)(\d{12,19})(' . $us . ')(\d{0,6})(' . $us . ')(\d{0,4})/';

        return preg_replace_callback(
            $pattern,
            function ($matches) {
                return self::maskAccountNumber($matches[1])
                    . $matches[2]
                    . ($matches[3] === '' ? '' : self::REDACTED)
                    . $matches[4]
                    . ($matches[5] === '' ? '' : self::REDACTED);
            },
            $message
        );
    }

    /**
     * Redacts the values of known sensitive keys in JSON / key-value messages.
     */
    private static function redactSensitiveKeys(string $message) : string
    {
        $keys = implode('|', array_map(function ($key) {
            return preg_quote($key, '/');
        }, self::SENSITIVE_KEYS));

        return preg_replace(
            '/(["\']?(?:' . $keys . ')["\']?\s*[:=]\s*)(["\']?)([^"\'&,}\s]+)(\2)/i',
            '${1}${2}' . self::REDACTED . '${4}',
            $message
        );
    }

    /**
     * Masks any remaining stand alone account number.
     */
    private static function maskAccountNumbers(string $message) : string
    {
        return preg_replace_callback(
            '/(?<!\d)\d{12,19}(?!\d)/',
            function ($matches) {
                return self::isLuhnValid($matches[0])
                    ? self::maskAccountNumber($matches[0])
                    : $matches[0];
            },
            $message
        );
    }

    private static function isLuhnValid(string $number) : bool
    {
        $sum = 0;
        $double = false;
        for ($i = strlen($number) - 1; $i >= 0; $i--) {
            $digit = (int) $number[$i];
            if ($double) {
                $digit *= 2;
                if ($digit > 9) {
                    $digit -= 9;
                }
            }
            $sum += $digit;
            $double = !$double;
        }

        return $sum % 10 === 0;
    }
}
