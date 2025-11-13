<?php

namespace GlobalPayments\Api\Tests\Unit\Gateways\GpEcomConnector;

use GlobalPayments\Api\Entities\HostedPaymentData;
use GlobalPayments\Api\Entities\Enums\HppVersion;
use GlobalPayments\Api\ServiceConfigs\Gateways\GpEcomConfig;
use GlobalPayments\Api\ServicesContainer;
use GlobalPayments\Api\HostedPaymentConfig;
use GlobalPayments\Api\Services\HostedService;
use PHPUnit\Framework\TestCase;

class HostedPaymentDataTest extends TestCase
{
    protected function getConfig()
    {
        $config = new GpEcomConfig();
        $config->merchantId = 'test_merchant';
        $config->accountId = 'internet';
        $config->sharedSecret = 'secret';
        $config->serviceUrl = 'https://pay.sandbox.realexpayments.com/pay';
        $config->hostedPaymentConfig = new HostedPaymentConfig();
        $config->hostedPaymentConfig->version = HppVersion::VERSION_2;
        return $config;
    }

    public function setup(): void
    {
        ServicesContainer::configureService($this->getConfig());
    }

    /**
     * Test that customerExists can be set to 0 (create new payer)
     */
    public function testCustomerExistsValueZero()
    {
        $hostedPaymentData = new HostedPaymentData();
        $hostedPaymentData->customerExists = 0;

        $service = new HostedService($this->getConfig());
        $json = $service->charge(10)
            ->withCurrency('EUR')
            ->withHostedPaymentData($hostedPaymentData)
            ->serialize();

        $data = json_decode($json, true);
        $this->assertEquals('0', $data['PAYER_EXIST']);
    }

    /**
     * Test that customerExists can be set to 1 (use existing payer)
     */
    public function testCustomerExistsValueOne()
    {
        $hostedPaymentData = new HostedPaymentData();
        $hostedPaymentData->customerExists = 1;

        $service = new HostedService($this->getConfig());
        $json = $service->charge(10)
            ->withCurrency('EUR')
            ->withHostedPaymentData($hostedPaymentData)
            ->serialize();

        $data = json_decode($json, true);
        $this->assertEquals('1', $data['PAYER_EXIST']);
    }

    /**
     * Test that customerExists can be set to 2 (use existing or create new)
     */
    public function testCustomerExistsValueTwo()
    {
        $hostedPaymentData = new HostedPaymentData();
        $hostedPaymentData->customerExists = 2;

        $service = new HostedService($this->getConfig());
        $json = $service->charge(10)
            ->withCurrency('EUR')
            ->withHostedPaymentData($hostedPaymentData)
            ->serialize();

        $data = json_decode($json, true);
        $this->assertEquals('2', $data['PAYER_EXIST']);
    }

    /**
     * Test backward compatibility: boolean true should map to "1"
     */
    public function testCustomerExistsBooleanTrue()
    {
        $hostedPaymentData = new HostedPaymentData();
        $hostedPaymentData->customerExists = true;

        $service = new HostedService($this->getConfig());
        $json = $service->charge(10)
            ->withCurrency('EUR')
            ->withHostedPaymentData($hostedPaymentData)
            ->serialize();

        $data = json_decode($json, true);
        $this->assertEquals('1', $data['PAYER_EXIST']);
    }

    /**
     * Test backward compatibility: boolean false should map to "0"
     */
    public function testCustomerExistsBooleanFalse()
    {
        $hostedPaymentData = new HostedPaymentData();
        $hostedPaymentData->customerExists = false;

        $service = new HostedService($this->getConfig());
        $json = $service->charge(10)
            ->withCurrency('EUR')
            ->withHostedPaymentData($hostedPaymentData)
            ->serialize();

        $data = json_decode($json, true);
        $this->assertEquals('0', $data['PAYER_EXIST']);
    }

    /**
     * Test that customerExists as string "0" works
     */
    public function testCustomerExistsStringZero()
    {
        $hostedPaymentData = new HostedPaymentData();
        $hostedPaymentData->customerExists = "0";

        $service = new HostedService($this->getConfig());
        $json = $service->charge(10)
            ->withCurrency('EUR')
            ->withHostedPaymentData($hostedPaymentData)
            ->serialize();

        $data = json_decode($json, true);
        $this->assertEquals('0', $data['PAYER_EXIST']);
    }

    /**
     * Test that customerExists as string "1" works
     */
    public function testCustomerExistsStringOne()
    {
        $hostedPaymentData = new HostedPaymentData();
        $hostedPaymentData->customerExists = "1";

        $service = new HostedService($this->getConfig());
        $json = $service->charge(10)
            ->withCurrency('EUR')
            ->withHostedPaymentData($hostedPaymentData)
            ->serialize();

        $data = json_decode($json, true);
        $this->assertEquals('1', $data['PAYER_EXIST']);
    }

    /**
     * Test that customerExists as string "2" works
     */
    public function testCustomerExistsStringTwo()
    {
        $hostedPaymentData = new HostedPaymentData();
        $hostedPaymentData->customerExists = "2";

        $service = new HostedService($this->getConfig());
        $json = $service->charge(10)
            ->withCurrency('EUR')
            ->withHostedPaymentData($hostedPaymentData)
            ->serialize();

        $data = json_decode($json, true);
        $this->assertEquals('2', $data['PAYER_EXIST']);
    }
}
