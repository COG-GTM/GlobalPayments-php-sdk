<?php

namespace GlobalPayments\Api\Gateways\Interfaces;

interface IBillPayResponse
{
    public function map();

    public function withResponseTagName(string $tagName): IBillPayResponse;

    public function withResponse(string $response): IBillPayResponse;
}
