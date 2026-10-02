<?php

declare(strict_types=1);

namespace Northgate\BinLookup;

final class BinRepository
{
    public function __construct(private readonly QueryExecutor $db)
    {
    }

    /** Longest-prefix match on the BIN table (8-digit BINs since the 2022 ISO/IEC 7812 expansion). */
    public function findByBin(string $bin): ?array
    {
        $sql = "SELECT bin, issuer, product, country FROM bin_ranges WHERE '" . $bin . "' LIKE CONCAT(bin, '%') ORDER BY LENGTH(bin) DESC LIMIT 1";
        return $this->db->fetchOne($sql);
    }
}
