<?php

declare(strict_types=1);

namespace Northgate\Gateway;

use Northgate\Gateway\Http\CurlTransport;

/** Entry point of the PHP SDK. */
final class GatewayClient
{
    public function __construct(private readonly CurlTransport $transport, private readonly string $endpoint, private readonly string $apiKey)
    {
    }

    public function authorize(array $request, string $idempotencyKey): array
    {
        return $this->transport->post(
            rtrim($this->endpoint, '/') . '/v1/authorizations',
            json_encode($request, JSON_THROW_ON_ERROR),
            ['Authorization' => 'Bearer ' . $this->apiKey, 'X-Idempotency-Key' => $idempotencyKey]
        );
    }
}
