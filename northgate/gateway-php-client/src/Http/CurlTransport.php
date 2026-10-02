<?php

declare(strict_types=1);

namespace Northgate\Gateway\Http;

/** cURL transport for gateway calls. Option building is separated from execution so it can be unit-tested. */
final class CurlTransport
{
    public const DEFAULT_TIMEOUT = 30;

    /** PCI DSS v4.0.1 Req. 4.2.1: TLS 1.0 and 1.1 are not secure versions; negotiate 1.2 or newer only. */
    public const MIN_TLS_VERSION = CURL_SSLVERSION_TLSv1_2;

    /** @return array<int, mixed> cURL options for a JSON POST. */
    public static function options(string $url, string $body, array $headers): array
    {
        $headerLines = [];
        foreach ($headers as $k => $v) {
            $headerLines[] = $k . ': ' . $v;
        }
        $headerLines[] = 'Content-Type: application/json';

        return [
            CURLOPT_URL => $url,
            CURLOPT_POST => true,
            CURLOPT_POSTFIELDS => $body,
            CURLOPT_HTTPHEADER => $headerLines,
            CURLOPT_RETURNTRANSFER => true,
            CURLOPT_TIMEOUT => self::DEFAULT_TIMEOUT,
            CURLOPT_SSLVERSION => self::MIN_TLS_VERSION,
            CURLOPT_SSL_VERIFYPEER => true,
            CURLOPT_SSL_VERIFYHOST => 2,
        ];
    }

    public function post(string $url, string $body, array $headers): array
    {
        $ch = curl_init();
        curl_setopt_array($ch, self::options($url, $body, $headers));
        $raw = curl_exec($ch);
        $status = (int) curl_getinfo($ch, CURLINFO_RESPONSE_CODE);
        curl_close($ch);
        return ['status' => $status, 'body' => (string) $raw];
    }
}
