<?php

namespace GlobalPayments\Api\Gateways;

use GlobalPayments\Api\Builders\AuthorizationBuilder;
use GlobalPayments\Api\Builders\ManagementBuilder;
use GlobalPayments\Api\Builders\ReportBuilder;
use GlobalPayments\Api\Entities\Enums\TransactionType;
use GlobalPayments\Api\Entities\Exceptions\GatewayException;
use GlobalPayments\Api\Entities\Exceptions\UnsupportedTransactionException;
use GlobalPayments\Api\Entities\Transaction;
use GlobalPayments\Api\Mapping\PayPalMapping;
use GlobalPayments\Api\PaymentMethods\AlternativePaymentMethod;

class PayPalConnector extends RestGateway implements IPaymentGateway
{
    /** @var string */
    public $clientId;

    /** @var string */
    public $clientSecret;

    /** @var string */
    private $accessToken;

    /** @var int */
    private $tokenExpires = 0;

    public function __construct()
    {
        parent::__construct();
        $this->headers['Accept'] = 'application/json';
        $this->headers['Content-Type'] = 'application/json';
    }

    public function supportsOpenBanking(): bool
    {
        return false;
    }

    public function signIn(): void
    {
        if (!empty($this->accessToken) && time() < $this->tokenExpires) {
            return;
        }

        $authHeader = base64_encode($this->clientId . ':' . $this->clientSecret);

        $request = curl_init($this->serviceUrl . '/v1/oauth2/token');
        curl_setopt($request, CURLOPT_RETURNTRANSFER, true);
        curl_setopt($request, CURLOPT_SSL_VERIFYPEER, true);
        curl_setopt($request, CURLOPT_SSL_VERIFYHOST, 2);
        curl_setopt($request, CURLOPT_POST, true);
        curl_setopt($request, CURLOPT_POSTFIELDS, 'grant_type=client_credentials');
        curl_setopt($request, CURLOPT_HTTPHEADER, [
            'Authorization: Basic ' . $authHeader,
            'Content-Type: application/x-www-form-urlencoded',
            'Accept: application/json',
        ]);
        curl_setopt($request, CURLOPT_CONNECTTIMEOUT, $this->timeout);
        curl_setopt($request, CURLOPT_TIMEOUT, $this->timeout);
        curl_setopt($request, CURLOPT_PROTOCOLS, CURLPROTO_HTTPS);
        curl_setopt($request, CURLOPT_SSLVERSION, CURL_SSLVERSION_TLSv1_2);

        if (!empty($this->webProxy)) {
            curl_setopt($request, CURLOPT_PROXY, $this->webProxy->uri);
            if (!empty($this->webProxy->username) && !empty($this->webProxy->password)) {
                curl_setopt($request, CURLOPT_PROXYUSERPWD, $this->webProxy->username . ':' . $this->webProxy->password);
            }
        }

        $curlResponse = curl_exec($request);
        $curlInfo = curl_getinfo($request);
        curl_close($request);

        if ($curlInfo['http_code'] !== 200 || empty($curlResponse)) {
            throw new GatewayException(
                sprintf('Failed to obtain PayPal access token. Status Code: %s', $curlInfo['http_code'])
            );
        }

        $tokenData = json_decode($curlResponse);
        if (!isset($tokenData->access_token)) {
            throw new GatewayException('PayPal OAuth2 response did not contain an access token.');
        }

        $this->accessToken = $tokenData->access_token;
        $this->tokenExpires = time() + ($tokenData->expires_in ?? 3600) - 60;
    }

    private function executePayPalRequest(string $verb, string $endpoint, $data = null): string
    {
        $this->signIn();
        $this->headers['Authorization'] = 'Bearer ' . $this->accessToken;

        return $this->doTransaction($verb, $endpoint, $data);
    }

    /**
     * {@inheritDoc}
     */
    public function processAuthorization(AuthorizationBuilder $builder): Transaction
    {
        $transactionType = $builder->transactionType;

        if ($transactionType === TransactionType::AUTH) {
            $intent = 'AUTHORIZE';
        } elseif ($transactionType === TransactionType::SALE) {
            $intent = 'CAPTURE';
        } else {
            throw new UnsupportedTransactionException(
                sprintf('Transaction type %s is not supported by PayPal.', $transactionType)
            );
        }

        $returnUrl = null;
        $cancelUrl = null;
        if ($builder->paymentMethod instanceof AlternativePaymentMethod) {
            $returnUrl = $builder->paymentMethod->returnUrl ?? null;
            $cancelUrl = $builder->paymentMethod->cancelUrl ?? null;
        }

        $requestData = PayPalMapping::buildOrderRequest(
            $builder->amount,
            $builder->currency,
            $intent,
            $returnUrl,
            $cancelUrl,
            $builder->description ?? null
        );

        $response = $this->executePayPalRequest(
            'POST',
            '/v2/checkout/orders',
            json_encode($requestData, JSON_UNESCAPED_SLASHES)
        );

        return PayPalMapping::mapOrderResponse($response);
    }

    /**
     * {@inheritDoc}
     */
    public function manageTransaction(ManagementBuilder $builder): Transaction
    {
        $transactionType = $builder->transactionType;
        $transactionId = $builder->transactionId;

        if (empty($transactionId) && $builder->paymentMethod instanceof \GlobalPayments\Api\PaymentMethods\TransactionReference) {
            $transactionId = $builder->paymentMethod->transactionId;
        }

        switch ($transactionType) {
            case TransactionType::CAPTURE:
                $response = $this->executePayPalRequest(
                    'POST',
                    sprintf('/v2/checkout/orders/%s/capture', $transactionId),
                    '{}'
                );
                return PayPalMapping::mapCaptureResponse($response);

            case TransactionType::REFUND:
                $refundId = $transactionId;
                $requestData = PayPalMapping::buildRefundRequest(
                    $builder->amount,
                    $builder->currency
                );
                $response = $this->executePayPalRequest(
                    'POST',
                    sprintf('/v2/payments/captures/%s/refund', $refundId),
                    $requestData !== null ? json_encode($requestData, JSON_UNESCAPED_SLASHES) : '{}'
                );
                return PayPalMapping::mapRefundResponse($response);

            case TransactionType::VOID:
                $authorizationId = $transactionId;
                $response = $this->executePayPalRequest(
                    'POST',
                    sprintf('/v2/payments/authorizations/%s/void', $authorizationId),
                    null
                );
                return PayPalMapping::mapVoidResponse($response ?: '{}');

            case TransactionType::REVERSAL:
                $authorizationId = $transactionId;
                $response = $this->executePayPalRequest(
                    'POST',
                    sprintf('/v2/payments/authorizations/%s/void', $authorizationId),
                    null
                );
                return PayPalMapping::mapVoidResponse($response ?: '{}');

            default:
                throw new UnsupportedTransactionException(
                    sprintf('Transaction type %s is not supported for PayPal management.', $transactionType)
                );
        }
    }

    /**
     * {@inheritDoc}
     */
    public function processReport(ReportBuilder $builder)
    {
        throw new UnsupportedTransactionException('Reporting is not supported by the PayPal connector.');
    }

    /**
     * {@inheritDoc}
     */
    public function serializeRequest(AuthorizationBuilder $builder)
    {
        throw new UnsupportedTransactionException('Serialization is not supported by the PayPal connector.');
    }

    /**
     * {@inheritDoc}
     */
    protected function doTransaction(
        $verb,
        $endpoint,
        $data = null,
        ?array $queryStringParams = null
    ) {
        if (!empty($data) && is_string($data)) {
            $decoded = json_decode($data, true);
            if ($decoded !== null) {
                $data = json_encode($decoded, JSON_UNESCAPED_SLASHES | JSON_PRETTY_PRINT);
            }
        }

        $response = $this->sendRequest($verb, $endpoint, $data, $queryStringParams);

        if (!in_array($response->statusCode, [200, 201, 204])) {
            $parsed = json_decode($response->rawResponse);
            $errorMessage = '';

            if (isset($parsed->message)) {
                $errorMessage = $parsed->message;
            } elseif (isset($parsed->error_description)) {
                $errorMessage = $parsed->error_description;
            } elseif (isset($parsed->details) && is_array($parsed->details)) {
                $descriptions = array_map(function ($d) {
                    return $d->description ?? ($d->issue ?? '');
                }, $parsed->details);
                $errorMessage = implode('; ', $descriptions);
            }

            if ($this->requestLogger) {
                $gatewayException = new GatewayException(
                    sprintf('Status Code: %s - %s', $response->statusCode, $errorMessage)
                );
                $this->requestLogger->responseError($gatewayException, $response->header);
            }

            throw new GatewayException(
                sprintf('Status Code: %s - %s', $response->statusCode, $errorMessage ?: 'Unknown PayPal error')
            );
        }

        return $response->rawResponse;
    }
}
