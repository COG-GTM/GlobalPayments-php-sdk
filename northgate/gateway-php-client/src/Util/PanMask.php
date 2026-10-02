<?php

declare(strict_types=1);

namespace Northgate\Gateway\Util;

/**
 * PAN display masking per sdk-shared-contract pan-masking.v1: first 6 (BIN) + last 4 visible.
 * Same convention as ProtectSensitiveData::hideValue($key, $pan, 4, 6) in globalpayments/php-sdk, so the Java and
 * PHP SDKs, the portal and receipts all render the same string (PCI DSS v4.0.1 Req. 3.4.1).
 */
final class PanMask
{
    public const VISIBLE_PREFIX = 6;
    public const VISIBLE_SUFFIX = 4;
    public const MASK_CHAR = '*';

    public static function mask(string $pan): string
    {
        return self::maskWith($pan, self::VISIBLE_PREFIX, self::VISIBLE_SUFFIX);
    }

    public static function maskWith(string $pan, int $prefix, int $suffix): string
    {
        if (strlen($pan) <= $prefix + $suffix) {
            throw new \InvalidArgumentException('PAN too short to mask');
        }
        return substr($pan, 0, $prefix)
            . str_repeat(self::MASK_CHAR, strlen($pan) - $prefix - $suffix)
            . substr($pan, -$suffix);
    }
}
