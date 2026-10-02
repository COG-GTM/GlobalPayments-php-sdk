<?php

declare(strict_types=1);

namespace Northgate\BinLookup\Tests;

use Northgate\BinLookup\BinLookupService;
use Northgate\BinLookup\BinRepository;
use Northgate\BinLookup\QueryExecutor;
use PHPUnit\Framework\TestCase;

final class BinLookupServiceTest extends TestCase
{
    public static function fakeDb(array &$captured): QueryExecutor
    {
        return new class($captured) implements QueryExecutor {
            public function __construct(private array &$captured)
            {
            }

            public function fetchOne(string $sql, array $params = []): ?array
            {
                $this->captured[] = ['sql' => $sql, 'params' => $params];
                return ['bin' => '41111111', 'issuer' => 'Northgate Test Bank', 'product' => 'Visa Classic', 'country' => 'GB'];
            }
        };
    }

    public function testResolveUsesEightDigitBin(): void
    {
        $captured = [];
        $svc = new BinLookupService(new BinRepository(self::fakeDb($captured)));
        $row = $svc->resolve('4111111111111111');
        self::assertSame('Northgate Test Bank', $row['issuer']);
        self::assertStringContainsString('41111111', $captured[0]['sql'] . json_encode($captured[0]['params']));
    }

    public function testDisplayLabelShowsBinAndLastFour(): void
    {
        $captured = [];
        $svc = new BinLookupService(new BinRepository(self::fakeDb($captured)));
        self::assertSame('41111111****1111', $svc->displayLabel('4111111111111111'));
    }
}
