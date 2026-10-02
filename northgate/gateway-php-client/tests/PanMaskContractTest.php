<?php

declare(strict_types=1);

namespace Northgate\Gateway\Tests;

use Northgate\Gateway\Util\PanMask;
use PHPUnit\Framework\TestCase;

/**
 * Cross-SDK contract: this SDK must render exactly the vectors in sdk-shared-contract. NG_CONTRACT_DIR points at the
 * sdk-shared-contract checkout in CI; falls back to the synced copy under tests/contract.
 */
final class PanMaskContractTest extends TestCase
{
    private static function contract(): array
    {
        $dir = getenv('NG_CONTRACT_DIR') ?: __DIR__ . '/contract';
        $files = glob($dir . '/pan-masking.*.json') ?: [];
        sort($files);
        foreach ($files as $f) {
            $c = json_decode((string) file_get_contents($f), true, 512, JSON_THROW_ON_ERROR);
            if (($c['status'] ?? '') === 'active') {
                return $c;
            }
        }
        throw new \RuntimeException('no active pan-masking contract in ' . $dir);
    }

    public function testSdkMatchesEveryVectorOfTheActiveContract(): void
    {
        $c = self::contract();
        self::assertSame($c['visible_prefix'], PanMask::VISIBLE_PREFIX, 'visible_prefix: SDK constant must equal the shared contract');
        self::assertSame($c['visible_suffix'], PanMask::VISIBLE_SUFFIX, 'visible_suffix: SDK constant must equal the shared contract');
        self::assertGreaterThanOrEqual(4, count($c['vectors']));
        foreach ($c['vectors'] as $v) {
            self::assertSame($v['masked'], PanMask::mask($v['pan']), 'vector ' . $v['pan']);
        }
    }
}
