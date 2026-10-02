<?php

declare(strict_types=1);

namespace Northgate\Gateway\Tests;

use Northgate\Gateway\Http\CurlTransport;
use PHPUnit\Framework\TestCase;

/** PCI DSS v4.0.1 Req. 4.2.1 evidence: the SDK transport negotiates TLS 1.2+ only and verifies the peer. */
final class CurlTransportTlsTest extends TestCase
{
    public function testMinimumTlsVersionIsOnePointTwo(): void
    {
        $o = CurlTransport::options('https://api.northgate.example/v1/authorizations', '{}', []);
        self::assertSame(CURL_SSLVERSION_TLSv1_2, $o[CURLOPT_SSLVERSION]);
        self::assertNotSame(CURL_SSLVERSION_TLSv1, $o[CURLOPT_SSLVERSION]);
        self::assertNotSame(CURL_SSLVERSION_TLSv1_0, $o[CURLOPT_SSLVERSION]);
        self::assertNotSame(CURL_SSLVERSION_TLSv1_1, $o[CURLOPT_SSLVERSION]);
    }

    public function testPeerAndHostAreVerified(): void
    {
        $o = CurlTransport::options('https://api.northgate.example/v1/authorizations', '{}', []);
        self::assertTrue($o[CURLOPT_SSL_VERIFYPEER]);
        self::assertSame(2, $o[CURLOPT_SSL_VERIFYHOST]);
    }
}
