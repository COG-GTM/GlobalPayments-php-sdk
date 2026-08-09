<?php
/**
 * APM Configuration for Hosted Payment Pages, goes in order.payment_method_configuration.amp
 * 
 */

namespace GlobalPayments\Api\Entities;

use GlobalPayments\Api\Utils\StringUtils;

/**
 * Configuration class for AMP's in hosted payment pages
 * These properties are PayPal specific. From the Documentation:
 * shippingAddressEnabled - This field determines whether the passing of PayPal shipping address details will be activated or not
 * addressOverride - Determines whether the shipping address can be changed by the customer on the PayPal review page
 */
class HPPApmConfiguration
{
    /**
     * Determines whether shipping address passing will be activated for PayPal
     * @var bool|null
     */
    public ?bool $shippingAddressEnabled = false;
    
    /**
     * Determines whether the shipping address can be changed by the customer on the PayPal review page
     * @var bool|null
     */
    public ?bool $addressOverride = false;
    
    /**
     * The nullable boolean properties are type-enforced, so no runtime validation remains.
     *
     * @return array Empty validation errors
     */
    public function validate(): array
    {
        return [];
    }

    /**
     * Convert to array representation.
     *
     * @return array
     */
    public function toArray(): array
    {
        $result = [];

        if (!empty($this->shippingAddressEnabled)) {
            $result['shipping_address_enabled'] = StringUtils::boolToYesNo($this->shippingAddressEnabled);
        }

        if (!empty($this->addressOverride)) {
            $result['address_override'] = StringUtils::boolToYesNo($this->addressOverride);
        }

        return $result;
    }
}
