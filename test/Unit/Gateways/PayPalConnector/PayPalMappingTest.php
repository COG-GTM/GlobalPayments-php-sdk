<?php

namespace Unit\Gateways\PayPalConnector;

use GlobalPayments\Api\Entities\Enums\TransactionStatus;
use GlobalPayments\Api\Entities\Transaction;
use GlobalPayments\Api\Mapping\PayPalMapping;
use PHPUnit\Framework\TestCase;

class PayPalMappingTest extends TestCase
{
    public function testMapTransactionStatusCreated()
    {
        $this->assertEquals(TransactionStatus::INITIATED, PayPalMapping::mapTransactionStatus('CREATED'));
    }

    public function testMapTransactionStatusApproved()
    {
        $this->assertEquals(TransactionStatus::PREAUTHORIZED, PayPalMapping::mapTransactionStatus('APPROVED'));
    }

    public function testMapTransactionStatusCompleted()
    {
        $this->assertEquals(TransactionStatus::CAPTURED, PayPalMapping::mapTransactionStatus('COMPLETED'));
    }

    public function testMapTransactionStatusVoided()
    {
        $this->assertEquals(TransactionStatus::REVERSED, PayPalMapping::mapTransactionStatus('VOIDED'));
    }

    public function testMapTransactionStatusSaved()
    {
        $this->assertEquals(TransactionStatus::PENDING, PayPalMapping::mapTransactionStatus('SAVED'));
    }

    public function testMapTransactionStatusUnknown()
    {
        $this->assertEquals('UNKNOWN_STATUS', PayPalMapping::mapTransactionStatus('UNKNOWN_STATUS'));
    }

    public function testBuildOrderRequestAuthorize()
    {
        $result = PayPalMapping::buildOrderRequest(25.50, 'USD', 'AUTHORIZE');

        $this->assertEquals('AUTHORIZE', $result['intent']);
        $this->assertEquals('25.50', $result['purchase_units'][0]['amount']['value']);
        $this->assertEquals('USD', $result['purchase_units'][0]['amount']['currency_code']);
        $this->assertArrayNotHasKey('payment_source', $result);
    }

    public function testBuildOrderRequestCapture()
    {
        $result = PayPalMapping::buildOrderRequest(100.00, 'EUR', 'CAPTURE');

        $this->assertEquals('CAPTURE', $result['intent']);
        $this->assertEquals('100.00', $result['purchase_units'][0]['amount']['value']);
        $this->assertEquals('EUR', $result['purchase_units'][0]['amount']['currency_code']);
    }

    public function testBuildOrderRequestWithDescription()
    {
        $result = PayPalMapping::buildOrderRequest(10.00, 'USD', 'AUTHORIZE', null, null, 'Test order');

        $this->assertEquals('Test order', $result['purchase_units'][0]['description']);
    }

    public function testBuildOrderRequestWithUrls()
    {
        $result = PayPalMapping::buildOrderRequest(
            10.00,
            'USD',
            'AUTHORIZE',
            'https://example.com/return',
            'https://example.com/cancel'
        );

        $this->assertArrayHasKey('payment_source', $result);
        $this->assertEquals(
            'https://example.com/return',
            $result['payment_source']['paypal']['experience_context']['return_url']
        );
        $this->assertEquals(
            'https://example.com/cancel',
            $result['payment_source']['paypal']['experience_context']['cancel_url']
        );
    }

    public function testBuildOrderRequestWithoutUrls()
    {
        $result = PayPalMapping::buildOrderRequest(10.00, 'USD', 'AUTHORIZE');

        $this->assertArrayNotHasKey('payment_source', $result);
    }

    public function testBuildRefundRequestWithAmount()
    {
        $result = PayPalMapping::buildRefundRequest(15.00, 'USD');

        $this->assertNotNull($result);
        $this->assertEquals('15.00', $result['amount']['value']);
        $this->assertEquals('USD', $result['amount']['currency_code']);
    }

    public function testBuildRefundRequestWithoutAmount()
    {
        $result = PayPalMapping::buildRefundRequest(null, 'USD');

        $this->assertNull($result);
    }

    public function testMapOrderResponse()
    {
        $json = json_encode([
            'id' => 'ORDER-123',
            'status' => 'CREATED',
            'purchase_units' => [
                [
                    'amount' => [
                        'currency_code' => 'USD',
                        'value' => '25.00',
                    ],
                ],
            ],
            'links' => [
                ['rel' => 'self', 'href' => 'https://api.paypal.com/v2/checkout/orders/ORDER-123'],
                ['rel' => 'approve', 'href' => 'https://www.paypal.com/checkoutnow?token=ORDER-123'],
            ],
        ]);

        $transaction = PayPalMapping::mapOrderResponse($json);

        $this->assertInstanceOf(Transaction::class, $transaction);
        $this->assertEquals('ORDER-123', $transaction->transactionId);
        $this->assertEquals('CREATED', $transaction->responseCode);
        $this->assertEquals(TransactionStatus::INITIATED, $transaction->responseMessage);
        $this->assertEquals('25.00', $transaction->authorizedAmount);
        $this->assertNotNull($transaction->alternativePaymentResponse);
        $this->assertEquals('PAYPAL', $transaction->alternativePaymentResponse->providerName);
        $this->assertEquals('ORDER-123', $transaction->alternativePaymentResponse->providerReference);
        $this->assertEquals(
            'https://www.paypal.com/checkoutnow?token=ORDER-123',
            $transaction->alternativePaymentResponse->redirectUrl
        );
    }

    public function testMapOrderResponseWithPayer()
    {
        $json = json_encode([
            'id' => 'ORDER-456',
            'status' => 'APPROVED',
            'purchase_units' => [
                ['amount' => ['currency_code' => 'USD', 'value' => '50.00']],
            ],
            'payer' => [
                'name' => ['given_name' => 'John', 'surname' => 'Doe'],
                'address' => ['country_code' => 'US'],
            ],
            'links' => [],
        ]);

        $transaction = PayPalMapping::mapOrderResponse($json);

        $this->assertEquals('John Doe', $transaction->alternativePaymentResponse->accountHolderName);
        $this->assertEquals('US', $transaction->alternativePaymentResponse->country);
    }

    public function testMapCaptureResponse()
    {
        $json = json_encode([
            'id' => 'ORDER-789',
            'status' => 'COMPLETED',
            'purchase_units' => [
                [
                    'payments' => [
                        'captures' => [
                            [
                                'id' => 'CAPTURE-001',
                                'status' => 'COMPLETED',
                                'amount' => ['currency_code' => 'USD', 'value' => '25.00'],
                            ],
                        ],
                    ],
                ],
            ],
        ]);

        $transaction = PayPalMapping::mapCaptureResponse($json);

        $this->assertInstanceOf(Transaction::class, $transaction);
        $this->assertEquals('COMPLETED', $transaction->responseCode);
        $this->assertEquals('25.00', $transaction->authorizedAmount);
        $this->assertEquals('CAPTURE-001', $transaction->transactionReference->transactionId);
        $this->assertEquals('PAYPAL', $transaction->alternativePaymentResponse->providerName);
    }

    public function testMapRefundResponse()
    {
        $json = json_encode([
            'id' => 'REFUND-001',
            'status' => 'COMPLETED',
            'amount' => ['currency_code' => 'USD', 'value' => '10.00'],
        ]);

        $transaction = PayPalMapping::mapRefundResponse($json);

        $this->assertInstanceOf(Transaction::class, $transaction);
        $this->assertEquals('REFUND-001', $transaction->transactionId);
        $this->assertEquals('COMPLETED', $transaction->responseCode);
        $this->assertEquals('10.00', $transaction->balanceAmount);
        $this->assertEquals('PAYPAL', $transaction->alternativePaymentResponse->providerName);
    }

    public function testMapVoidResponse()
    {
        $transaction = PayPalMapping::mapVoidResponse('');

        $this->assertInstanceOf(Transaction::class, $transaction);
        $this->assertEquals('VOIDED', $transaction->responseCode);
        $this->assertEquals(TransactionStatus::REVERSED, $transaction->responseMessage);
        $this->assertEquals('PAYPAL', $transaction->alternativePaymentResponse->providerName);
        $this->assertEquals('VOIDED', $transaction->alternativePaymentResponse->paymentStatus);
    }

    public function testBuildOrderRequestFormatsAmountCorrectly()
    {
        $result = PayPalMapping::buildOrderRequest(10, 'USD');
        $this->assertEquals('10.00', $result['purchase_units'][0]['amount']['value']);

        $result = PayPalMapping::buildOrderRequest(9.9, 'USD');
        $this->assertEquals('9.90', $result['purchase_units'][0]['amount']['value']);

        $result = PayPalMapping::buildOrderRequest(0.01, 'USD');
        $this->assertEquals('0.01', $result['purchase_units'][0]['amount']['value']);
    }
}
