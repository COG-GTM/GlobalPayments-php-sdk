<?php

namespace GlobalPayments\Api\Mapping;

use GlobalPayments\Api\Entities\AlternativePaymentResponse;
use GlobalPayments\Api\Entities\Enums\TransactionStatus;
use GlobalPayments\Api\Entities\Transaction;
use GlobalPayments\Api\PaymentMethods\TransactionReference;

class PayPalMapping
{
    private static $statusMap = [
        'CREATED' => TransactionStatus::INITIATED,
        'SAVED' => TransactionStatus::PENDING,
        'APPROVED' => TransactionStatus::PREAUTHORIZED,
        'VOIDED' => TransactionStatus::REVERSED,
        'COMPLETED' => TransactionStatus::CAPTURED,
        'PAYER_ACTION_REQUIRED' => TransactionStatus::PENDING,
    ];

    public static function mapTransactionStatus(string $paypalStatus): string
    {
        return self::$statusMap[$paypalStatus] ?? $paypalStatus;
    }

    public static function mapOrderResponse($response): Transaction
    {
        $doc = json_decode($response);
        $transaction = new Transaction();

        $transRef = new TransactionReference();
        $transRef->transactionId = $doc->id ?? null;
        $transaction->transactionReference = $transRef;
        $transaction->transactionId = $doc->id ?? null;
        $transaction->responseCode = $doc->status ?? null;
        $transaction->responseMessage = self::mapTransactionStatus($doc->status ?? '');

        if (isset($doc->purchase_units) && !empty($doc->purchase_units)) {
            $unit = $doc->purchase_units[0];
            if (isset($unit->amount)) {
                $transaction->authorizedAmount = $unit->amount->value ?? null;
            }
        }

        $apmResponse = new AlternativePaymentResponse();
        $apmResponse->providerName = 'PAYPAL';
        $apmResponse->providerReference = $doc->id ?? null;
        $apmResponse->paymentStatus = $doc->status ?? null;

        if (isset($doc->links)) {
            foreach ($doc->links as $link) {
                if ($link->rel === 'approve') {
                    $apmResponse->redirectUrl = $link->href;
                    break;
                }
            }
        }

        if (isset($doc->payer)) {
            $apmResponse->accountHolderName = trim(
                ($doc->payer->name->given_name ?? '') . ' ' . ($doc->payer->name->surname ?? '')
            );
            $apmResponse->country = $doc->payer->address->country_code ?? null;
        }

        $transaction->alternativePaymentResponse = $apmResponse;

        return $transaction;
    }

    public static function mapCaptureResponse($response): Transaction
    {
        $doc = json_decode($response);
        $transaction = new Transaction();

        $transRef = new TransactionReference();
        $transRef->transactionId = $doc->id ?? null;
        $transaction->transactionReference = $transRef;
        $transaction->transactionId = $doc->id ?? null;
        $transaction->responseCode = $doc->status ?? null;
        $transaction->responseMessage = self::mapTransactionStatus($doc->status ?? '');

        if (isset($doc->purchase_units) && !empty($doc->purchase_units)) {
            $unit = $doc->purchase_units[0];
            if (isset($unit->payments->captures) && !empty($unit->payments->captures)) {
                $capture = $unit->payments->captures[0];
                $transaction->authorizedAmount = $capture->amount->value ?? null;

                $captureRef = new TransactionReference();
                $captureRef->transactionId = $capture->id ?? null;
                $transaction->transactionReference = $captureRef;
            }
        }

        $apmResponse = new AlternativePaymentResponse();
        $apmResponse->providerName = 'PAYPAL';
        $apmResponse->providerReference = $doc->id ?? null;
        $apmResponse->paymentStatus = $doc->status ?? null;
        $transaction->alternativePaymentResponse = $apmResponse;

        return $transaction;
    }

    public static function mapRefundResponse($response): Transaction
    {
        $doc = json_decode($response);
        $transaction = new Transaction();

        $transRef = new TransactionReference();
        $transRef->transactionId = $doc->id ?? null;
        $transaction->transactionReference = $transRef;
        $transaction->transactionId = $doc->id ?? null;
        $transaction->responseCode = $doc->status ?? null;
        $transaction->responseMessage = self::mapTransactionStatus($doc->status ?? '');

        if (isset($doc->amount)) {
            $transaction->balanceAmount = $doc->amount->value ?? null;
        }

        $apmResponse = new AlternativePaymentResponse();
        $apmResponse->providerName = 'PAYPAL';
        $apmResponse->providerReference = $doc->id ?? null;
        $apmResponse->paymentStatus = $doc->status ?? null;
        $transaction->alternativePaymentResponse = $apmResponse;

        return $transaction;
    }

    public static function mapVoidResponse($response): Transaction
    {
        $transaction = new Transaction();
        $transaction->responseCode = 'VOIDED';
        $transaction->responseMessage = TransactionStatus::REVERSED;

        $apmResponse = new AlternativePaymentResponse();
        $apmResponse->providerName = 'PAYPAL';
        $apmResponse->paymentStatus = 'VOIDED';
        $transaction->alternativePaymentResponse = $apmResponse;

        return $transaction;
    }

    public static function buildOrderRequest(
        $amount,
        $currency,
        string $intent = 'AUTHORIZE',
        ?string $returnUrl = null,
        ?string $cancelUrl = null,
        ?string $description = null
    ): array {
        $order = [
            'intent' => $intent,
            'purchase_units' => [
                [
                    'amount' => [
                        'currency_code' => $currency ?? 'USD',
                        'value' => number_format((float)$amount, 2, '.', ''),
                    ],
                ],
            ],
        ];

        if ($description !== null) {
            $order['purchase_units'][0]['description'] = $description;
        }

        if ($returnUrl !== null || $cancelUrl !== null) {
            $order['payment_source'] = [
                'paypal' => [
                    'experience_context' => [
                        'return_url' => $returnUrl ?? '',
                        'cancel_url' => $cancelUrl ?? '',
                    ],
                ],
            ];
        }

        return $order;
    }

    public static function buildRefundRequest($amount = null, $currency = null): ?array
    {
        if ($amount === null) {
            return null;
        }

        return [
            'amount' => [
                'value' => number_format((float)$amount, 2, '.', ''),
                'currency_code' => $currency ?? 'USD',
            ],
        ];
    }
}
