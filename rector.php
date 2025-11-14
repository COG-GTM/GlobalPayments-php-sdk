<?php

declare(strict_types=1);

use Rector\Config\RectorConfig;
use Rector\PHPUnit\Set\PHPUnitSetList;

return RectorConfig::configure()
    ->withPaths([
        __DIR__ . '/test',
    ])
    ->withSets([
        PHPUnitSetList::PHPUNIT_100,
    ])
    ->withPhpSets(php82: true);
