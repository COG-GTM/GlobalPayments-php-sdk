<?php

namespace Gateways\PayPalConnector;

use GlobalPayments\Api\Entities\AlternativePaymentResponse;
use GlobalPayments\Api\Entities\Enums\AlternativePaymentType;
use GlobalPayments\Api\Entities\Enums\Environment;
use GlobalPayments\Api\Entities\Enums\TransactionType;
use GlobalPayments\Api\Entities\Transaction;
use GlobalPayments\Api\PaymentMethods\AlternativePaymentMethod;
use GlobalPayments\Api\ServiceConfigs\Gateways\PayPalConfig;
use GlobalPayments\Api\ServicesContainer;
use PHPUnit\Framework\TestCase;

class PayPalTest extends TestCase
{
    private AlternativePaymentMethod $paymentMethod;
    private string $currency = 'USD';
    private float $amount = 10.00;

    public function setup(): void
    {
        $config = new PayPalConfig();
        $config->clientId = 'PAYPAL_SANDBOX_CLIENT_ID';
        $config->clientSecret = 'PAYPAL_SANDBOX_CLIENT_SECRET';
        $config->environment = Environment::TEST;

        ServicesContainer::configureService($config);

        $this->paymentMethod = new AlternativePaymentMethod(AlternativePaymentType::PAYPAL);
        $this->paymentMethod->returnUrl = 'https://example.com/return';
        $this->paymentMethod->cancelUrl = 'https://example.com/cancel';
    }

    public function testPayPalConfigValidation()
    {
        $config = new PayPalConfig();
        $config->clientId = 'test-client-id';
        $config->clientSecret = 'test-client-secret';
        $config->environment = Environment::TEST;

        $config->validate();
        $this->assertTrue($config->validated);
    }

    public function testPayPalConfigValidationMissingClientId()
    {
        $this->expectException(\GlobalPayments\Api\Entities\Exceptions\ConfigurationException::class);
        $this->expectExceptionMessage('PayPal clientId is required.');

        $config = new PayPalConfig();
        $config->clientSecret = 'test-secret';
        $config->validate();
    }

    public function testPayPalConfigValidationMissingClientSecret()
    {
        $this->expectException(\GlobalPayments\Api\Entities\Exceptions\ConfigurationException::class);
        $this->expectExceptionMessage('PayPal clientSecret is required.');

        $config = new PayPalConfig();
        $config->clientId = 'test-client-id';
        $config->validate();
    }

    public function testPayPalConfigProductionEndpoint()
    {
        $config = new PayPalConfig();
        $config->clientId = 'test-client-id';
        $config->clientSecret = 'test-secret';
        $config->environment = Environment::PRODUCTION;

        $services = new \GlobalPayments\Api\ConfiguredServices();
        $config->configureContainer($services);

        $this->assertNotNull($services->gatewayConnector);
        $this->assertInstanceOf(
            \GlobalPayments\Api\Gateways\PayPalConnector::class,
            $services->gatewayConnector
        );
    }

    public function testPayPalConfigSandboxEndpoint()
    {
        $config = new PayPalConfig();
        $config->clientId = 'test-client-id';
        $config->clientSecret = 'test-secret';
        $config->environment = Environment::TEST;

        $services = new \GlobalPayments\Api\ConfiguredServices();
        $config->configureContainer($services);

        $this->assertNotNull($services->gatewayConnector);
        $this->assertInstanceOf(
            \GlobalPayments\Api\Gateways\PayPalConnector::class,
            $services->gatewayConnector
        );
    }

    /**
     * Test authorization creates an order with AUTHORIZE intent.
     * Note: This test requires valid PayPal sandbox credentials.
     * @group paypal-sandbox
     */
    public function testPayPalAuthorization()
    {
        $this->markTestSkipped('Requires PayPal sandbox credentials.');

        $response = $this->paymentMethod->authorize($this->amount)
            ->withCurrency($this->currency)
            ->withDescription('Test Authorization')
            ->execute();

        $this->assertNotNull($response);
        $this->assertInstanceOf(Transaction::class, $response);
        $this->assertNotNull($response->transactionId);
        $this->assertEquals('CREATED', $response->responseCode);
        $this->assertNotNull($response->alternativePaymentResponse);
        $this->assertEquals('PAYPAL', $response->alternativePaymentResponse->providerName);
    }

    /**
     * Test sale creates an order with CAPTURE intent.
     * Note: This test requires valid PayPal sandbox credentials.
     * @group paypal-sandbox
     */
    public function testPayPalCharge()
    {
        $this->markTestSkipped('Requires PayPal sandbox credentials.');

        $response = $this->paymentMethod->charge($this->amount)
            ->withCurrency($this->currency)
            ->withDescription('Test Charge')
            ->execute();

        $this->assertNotNull($response);
        $this->assertInstanceOf(Transaction::class, $response);
        $this->assertNotNull($response->transactionId);
        $this->assertEquals('CREATED', $response->responseCode);
    }

    /**
     * Test capture of an authorized order.
     * Note: This test requires valid PayPal sandbox credentials.
     * @group paypal-sandbox
     */
    public function testPayPalCapture()
    {
        $this->markTestSkipped('Requires PayPal sandbox credentials.');

        $authResponse = $this->paymentMethod->authorize($this->amount)
            ->withCurrency($this->currency)
            ->execute();

        $this->assertNotNull($authResponse);

        $captureResponse = $authResponse->capture()
            ->execute();

        $this->assertNotNull($captureResponse);
        $this->assertInstanceOf(Transaction::class, $captureResponse);
    }

    /**
     * Test void of an authorized order.
     * Note: This test requires valid PayPal sandbox credentials.
     * @group paypal-sandbox
     */
    public function testPayPalVoid()
    {
        $this->markTestSkipped('Requires PayPal sandbox credentials.');

        $authResponse = $this->paymentMethod->authorize($this->amount)
            ->withCurrency($this->currency)
            ->execute();

        $this->assertNotNull($authResponse);

        $voidResponse = $authResponse->void()
            ->execute();

        $this->assertNotNull($voidResponse);
        $this->assertInstanceOf(Transaction::class, $voidResponse);
        $this->assertEquals('VOIDED', $voidResponse->responseCode);
    }

    /**
     * Test refund of a captured payment.
     * Note: This test requires valid PayPal sandbox credentials.
     * @group paypal-sandbox
     */
    public function testPayPalRefund()
    {
        $this->markTestSkipped('Requires PayPal sandbox credentials.');

        $chargeResponse = $this->paymentMethod->charge($this->amount)
            ->withCurrency($this->currency)
            ->execute();

        $this->assertNotNull($chargeResponse);

        $refundResponse = Transaction::fromId($chargeResponse->transactionId)
            ->refund($this->amount)
            ->withCurrency($this->currency)
            ->execute();

        $this->assertNotNull($refundResponse);
        $this->assertInstanceOf(Transaction::class, $refundResponse);
    }
}
