<?php

declare(strict_types=1);

namespace Northgate\Gateway\Tests;

use PHPUnit\Framework\TestCase;

/** PCI DSS v4.0.1 Req. 12.3.3 evidence: the crypto inventory exists, names the transport, and prohibits insecure versions. */
final class CryptoInventoryTest extends TestCase
{
    private static function inventory(): array
    {
        return json_decode((string) file_get_contents(__DIR__ . '/../docs/crypto-inventory.json'), true, 512, JSON_THROW_ON_ERROR);
    }

    public function testInventoryHasReviewDatesAndOwner(): void
    {
        $inv = self::inventory();
        self::assertNotEmpty($inv['owner']);
        self::assertMatchesRegularExpression('/^\d{4}-\d{2}-\d{2}$/', $inv['reviewed']);
        self::assertMatchesRegularExpression('/^\d{4}-\d{2}-\d{2}$/', $inv['next_review_due']);
        self::assertLessThanOrEqual(366, (strtotime($inv['next_review_due']) - strtotime($inv['reviewed'])) / 86400);
    }

    public function testInsecureProtocolsAreProhibitedAndTransportIsCovered(): void
    {
        $inv = self::inventory();
        $byName = array_column($inv['protocols'], 'status', 'name');
        self::assertSame('prohibited', $byName['TLS 1.0']);
        self::assertSame('prohibited', $byName['TLS 1.1']);
        $inUse = array_filter($inv['protocols'], static fn (array $p): bool => str_starts_with($p['status'], 'in-use'));
        self::assertNotEmpty($inUse);
        foreach ($inUse as $p) {
            self::assertStringContainsString('CurlTransport', $p['where'], "{$p['name']} must name where it is used");
        }
        self::assertNotEmpty($inv['cipher_suites']);
    }
}
