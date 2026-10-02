<?php

declare(strict_types=1);

namespace Northgate\BinLookup\Tests;

use Northgate\BinLookup\BinRepository;
use PHPUnit\Framework\TestCase;

/** PCI DSS v4.0.1 Req. 6.2.4 evidence: caller input never reaches the SQL text. */
final class BinRepositoryInjectionTest extends TestCase
{
    public function testBinIsBoundNotConcatenated(): void
    {
        $captured = [];
        $repo = new BinRepository(BinLookupServiceTest::fakeDb($captured));
        $repo->findByBin('41111111');
        self::assertStringNotContainsString('41111111', $captured[0]['sql']);
        self::assertSame(['bin' => '41111111'], $captured[0]['params']);
        self::assertStringContainsString(':bin', $captured[0]['sql']);
    }

    public function testHostileInputIsRejectedBeforeTheDatabase(): void
    {
        $captured = [];
        $repo = new BinRepository(BinLookupServiceTest::fakeDb($captured));
        $this->expectException(\InvalidArgumentException::class);
        try {
            $repo->findByBin("411111' OR '1'='1");
        } finally {
            self::assertSame([], $captured, 'no query executed for invalid input');
        }
    }
}
