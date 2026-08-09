<?php
namespace GlobalPayments\Api\Tests\Integration\Gateways\Terminals\PAX;

use GlobalPayments\Api\Entities\Enums\PaymentMethodType;
use GlobalPayments\Api\Terminals\ConnectionConfig;
use GlobalPayments\Api\Terminals\Enums\ConnectionModes;
use GlobalPayments\Api\Terminals\Enums\DeviceType;
use GlobalPayments\Api\Services\DeviceService;
use PHPUnit\Framework\TestCase;
use GlobalPayments\Api\Tests\Integration\Gateways\Terminals\RequestIdProvider;
use GlobalPayments\Api\Terminals\Enums\CurrencyType;

class PaxEBTTests extends TestCase
{

    private $device;

    public function setUp() : void
    {
        $this->device = DeviceService::create($this->getConfig());
    }

    public function tearDown() : void
    {
        sleep(3);
    }

    protected function getConfig()
    {
        $config = new ConnectionConfig();
        $config->ipAddress = '192.168.42.219';
        $config->port = '10009';
        $config->deviceType = DeviceType::PAX_S300;
        $config->connectionMode = ConnectionModes::TCP_IP;
        $config->timeout = 30;
        $config->requestIdProvider = new RequestIdProvider();

        return $config;
    }

    public function testEbtFoodstampPurchase()
    {
        $response = $this->device->sale(10)
            ->withPaymentMethodType(PaymentMethodType::EBT)
            ->withCurrency(CurrencyType::FOODSTAMPS)
            ->execute();

        $this->assertNotNull($response);
        $this->assertEquals("00", $response->deviceResponseCode);
        $this->assertEquals('F', $response->ebtType);
    }

    public function testEbtCashBenefitPurchase()
    {
        $response = $this->device->sale(10)
            ->withPaymentMethodType(PaymentMethodType::EBT)
            ->withCurrency(CurrencyType::CASH_BENEFITS)
            ->execute();

        $this->assertNotNull($response);
        $this->assertEquals("00", $response->deviceResponseCode);
        $this->assertEquals('C', $response->ebtType);
    }

    public function testEbtVoucherPurchase()
    {
        $response = $this->device->sale(10)
            ->withPaymentMethodType(PaymentMethodType::EBT)
            ->withCurrency(CurrencyType::VOUCHER)
            ->withAllowDuplicates(true)
            ->execute();
            $this->assertNotNull($response);
            $this->assertEquals("00", $response->deviceResponseCode);
    }

    public function testEbtFoodstampBalanceInquiry()
    {
        $response = $this->device->balance()
            ->withPaymentMethodType(PaymentMethodType::EBT)
            ->withCurrency(CurrencyType::FOODSTAMPS)
            ->execute();
            $this->assertNotNull($response);
            $this->assertEquals("00", $response->deviceResponseCode);
    }

    public function testEbtCashBenefitsBalanceInquiry()
    {
        $response = $this->device->balance()
            ->withPaymentMethodType(PaymentMethodType::EBT)
            ->withCurrency(CurrencyType::CASH_BENEFITS)
            ->execute();
            $this->assertNotNull($response);
            $this->assertEquals("00", $response->deviceResponseCode);
    }

    public function testEbtBalanceInquiryWithVoucher()
    {
        $this->expectException(\GlobalPayments\Api\Entities\Exceptions\BuilderException::class);
        $this->expectExceptionMessage('Property `currency`is equal to the expected value `VOUCHER`');
        $this->device->balance()
            ->withPaymentMethodType(PaymentMethodType::EBT)
            ->withCurrency(CurrencyType::VOUCHER)
            ->execute();
    }

    public function testEbtFoodStampRefund()
    {
        $response = $this->device->refund(10)
            ->withPaymentMethodType(PaymentMethodType::EBT)
            ->withCurrency(CurrencyType::FOODSTAMPS)
            ->execute();
            $this->assertNotNull($response);
            $this->assertEquals("00", $response->deviceResponseCode);
    }

    public function testEbtCashBenefitRefund()
    {
        $response = $this->device->refund(10)
            ->withPaymentMethodType(PaymentMethodType::EBT)
            ->withCurrency(CurrencyType::FOODSTAMPS)
            ->execute();

        $this->assertNotNull($response);
        $this->assertEquals("00", $response->deviceResponseCode);
    }

    public function testEbtRefundAllowDup()
    {
        $this->expectException(\GlobalPayments\Api\Entities\Exceptions\BuilderException::class);
        $this->expectExceptionMessage('amount cannot be null for this transaction type');
        $this->device->refund()
            ->withPaymentMethodType(PaymentMethodType::EBT)
            ->withAllowDuplicates(true)
            ->execute();
    }

    public function testEbtCashBenefitWithdrawal()
    {
        $response = $this->device->withdrawal(10)
            ->withCurrency(CurrencyType::CASH_BENEFITS)
            ->execute();
            $this->assertNotNull($response);
            $this->assertEquals("00", $response->deviceResponseCode);
    }

    public function testEbtBenefitWithdrawalAllowDup()
    {
        $this->expectException(\GlobalPayments\Api\Entities\Exceptions\BuilderException::class);
        $this->expectExceptionMessage('currency cannot be null for this transaction type');
        $this->device->withdrawal(10)
            ->withAllowDuplicates(true)
            ->execute();
    }
}
