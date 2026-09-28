<?php

declare(strict_types=1);

namespace Northgate\BinLookup;

final class BinRepository
{
    /** 6- or 8-digit BIN (ISO/IEC 7812). Anything else is rejected before it reaches the database. */
    private const BIN_PATTERN = '/^\d{6,8}$/';

    public function __construct(private readonly QueryExecutor $db)
    {
    }

    /** Longest-prefix match on the BIN table. The BIN is validated and bound as a parameter (Req. 6.2.4). */
    public function findByBin(string $bin): ?array
    {
        if (preg_match(self::BIN_PATTERN, $bin) !== 1) {
            throw new \InvalidArgumentException('BIN must be 6 to 8 digits');
        }
        $sql = 'SELECT bin, issuer, product, country FROM bin_ranges WHERE :bin LIKE CONCAT(bin, \'%\') ORDER BY LENGTH(bin) DESC LIMIT 1';

        return $this->db->fetchOne($sql, ['bin' => $bin]);
    }
}
