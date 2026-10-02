<?php

declare(strict_types=1);

namespace Northgate\Gateway\Tests;

use Northgate\Gateway\Http\CurlTransport;
use PHPUnit\Framework\TestCase;

final class GatewayClientTest extends TestCase
{
    public function testOptionsCarryAuthAndIdempotencyHeaders(): void
    {
        $o = CurlTransport::options('https://api.northgate.example/v1/authorizations', '{}', ['Authorization' => 'Bearer k', 'X-Idempotency-Key' => 'i-1']);
        self::assertContains('Authorization: Bearer k', $o[CURLOPT_HTTPHEADER]);
        self::assertContains('X-Idempotency-Key: i-1', $o[CURLOPT_HTTPHEADER]);
        self::assertTrue($o[CURLOPT_SSL_VERIFYPEER]);
    }
}
