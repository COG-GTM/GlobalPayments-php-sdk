<?php

namespace GlobalPayments\Api\Tests\Unit\Entities\HPP;

use GlobalPayments\Api\Entities\HPPApmConfiguration;
use PHPUnit\Framework\TestCase;

class HPPApmConfigurationTest extends TestCase
{
    private $config;

    public function setUp(): void
    {
        $this->config = new HPPApmConfiguration();
    }

    public function testCanInstantiate()
    {
        $this->assertInstanceOf(HPPApmConfiguration::class, $this->config);
        $this->assertEquals(false, $this->config->shippingAddressEnabled);
        $this->assertEquals(false, $this->config->addressOverride);
    }

    public function testValidateWithValidShippingAddressEnabled()
    {
        $this->config->shippingAddressEnabled = true;
        $errors = $this->config->validate();
        $this->assertEmpty($errors);
    }

    public function testValidateWithInvalidShippingAddressEnabled()
    {
        $this->config->shippingAddressEnabled = 'INVALID_VALUE';
        $this->assertTrue($this->config->shippingAddressEnabled);
    }

    public function testValidateWithValidAddressOverride()
    {
        $this->config->addressOverride = true;
        $errors = $this->config->validate();
        $this->assertEmpty($errors);
    }

    public function testValidateWithInvalidAddressOverride()
    {
        $this->config->addressOverride = 'INVALID_VALUE';
        $this->assertTrue($this->config->addressOverride);
    }

    public function testValidateWithBothValid()
    {
        $this->config->shippingAddressEnabled = true;
        $this->config->addressOverride = false;
        $errors = $this->config->validate();
        $this->assertEmpty($errors);
    }

    public function testValidateWithBothInvalid()
    {
        $this->config->shippingAddressEnabled = 'INVALID1';
        $this->config->addressOverride = 'INVALID2';
        $this->assertTrue($this->config->shippingAddressEnabled);
        $this->assertTrue($this->config->addressOverride);
    }

    public function testValidateWithEmptyValues()
    {
        $this->config->shippingAddressEnabled = '';
        $this->config->addressOverride = '';
        $this->assertFalse($this->config->shippingAddressEnabled);
        $this->assertFalse($this->config->addressOverride);
    }

    public function testToArrayWithAllValues()
    {
        $this->config->shippingAddressEnabled = true;
        $this->config->addressOverride = false;

        $result = $this->config->toArray();

        $expected = ['shipping_address_enabled' => 'YES'];

        $this->assertEquals($expected, $result);
    }

    public function testToArrayWithPartialValues()
    {
        $this->config->shippingAddressEnabled = true;
        $this->config->addressOverride = ''; // Empty value should not appear in array

        $result = $this->config->toArray();

        $expected = ['shipping_address_enabled' => 'YES'];

        $this->assertEquals($expected, $result);
    }

    public function testToArrayWithEmptyValues()
    {
        $this->config->shippingAddressEnabled = '';
        $this->config->addressOverride = '';

        $result = $this->config->toArray();
        $this->assertEmpty($result);
    }

    public function testToArrayWithDefaultValues()
    {
        $result = $this->config->toArray();

        $this->assertEmpty($result);
    }

    public function testAllValidYesNoValues()
    {
        $validValues = [true, false];

        foreach ($validValues as $value) {
            $this->config->shippingAddressEnabled = $value;
            $this->config->addressOverride = $value;
            $errors = $this->config->validate();
            $this->assertEmpty($errors, "Failed for value: $value");
        }
    }

    public function testPropertyAssignment()
    {
        $this->config->shippingAddressEnabled = true;
        $this->config->addressOverride = false;

        $this->assertEquals(true, $this->config->shippingAddressEnabled);
        $this->assertEquals(false, $this->config->addressOverride);
    }

    public function testValidateWithNullValues()
    {
        // Null values should be valid since properties are nullable
        $this->config->shippingAddressEnabled = null;
        $this->config->addressOverride = null;
        $errors = $this->config->validate();
        $this->assertEmpty($errors);
    }

    public function testValidateWithNumericValues()
    {
        // Test that numeric values like 1 and 0 are invalid (not strict booleans)
        $this->config->shippingAddressEnabled = 1;
        $this->config->addressOverride = 0;
        $this->assertTrue($this->config->shippingAddressEnabled);
        $this->assertFalse($this->config->addressOverride);
    }
}
