<?php

declare(strict_types=1);

namespace Northgate\BinLookup;

/** Thin DB seam so the repository is testable without a database. */
interface QueryExecutor
{
    /** @param array<string, scalar> $params */
    public function fetchOne(string $sql, array $params = []): ?array;
}
