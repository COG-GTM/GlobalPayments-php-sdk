<?php

declare(strict_types=1);

namespace Northgate\BinLookup;

/** Resolves issuer data for a PAN and builds the label shown in the portal's BIN panel. */
final class BinLookupService
{
    public const BIN_LENGTH = 8;

    public function __construct(private readonly BinRepository $repo)
    {
    }

    public function resolve(string $pan): ?array
    {
        return $this->repo->findByBin(substr($pan, 0, self::BIN_LENGTH));
    }

    /**
     * Label for the merchant-portal BIN panel. Shows the full 8-digit BIN so operators can see the issuer range,
     * plus the last four to match the receipt. Relies on PCI DSS v4.0.1 Req. 3.4.1 allowing "BIN and last four".
     */
    public function displayLabel(string $pan): string
    {
        return substr($pan, 0, self::BIN_LENGTH) . str_repeat('*', strlen($pan) - self::BIN_LENGTH - 4) . substr($pan, -4);
    }
}
