<?php

namespace GlobalPayments\Api\ServiceConfigs\Gateways;

use GlobalPayments\Api\ConfiguredServices;
use GlobalPayments\Api\Entities\Enums\Environment;
use GlobalPayments\Api\Entities\Enums\GatewayProvider;
use GlobalPayments\Api\Entities\Enums\ServiceEndpoints;
use GlobalPayments\Api\Entities\Exceptions\ConfigurationException;
use GlobalPayments\Api\Gateways\PayPalConnector;

class PayPalConfig extends GatewayConfig
{
    /** @var string */
    public $gatewayProvider = GatewayProvider::PAYPAL;

    /** @var string */
    public $clientId;

    /** @var string */
    public $clientSecret;

    public function __construct()
    {
        $this->gatewayProvider = GatewayProvider::PAYPAL;
    }

    public function configureContainer(ConfiguredServices $services)
    {
        if (empty($this->serviceUrl)) {
            $this->serviceUrl = ($this->environment == Environment::PRODUCTION)
                ? ServiceEndpoints::PAYPAL_PRODUCTION
                : ServiceEndpoints::PAYPAL_TEST;
        }

        $gateway = new PayPalConnector();
        $gateway->clientId = $this->clientId;
        $gateway->clientSecret = $this->clientSecret;
        $gateway->serviceUrl = $this->serviceUrl;
        $gateway->timeout = $this->timeout;
        $gateway->requestLogger = $this->requestLogger;
        $gateway->webProxy = $this->webProxy;
        $gateway->dynamicHeaders = $this->dynamicHeaders;
        $gateway->environment = $this->environment;

        $services->gatewayConnector = $gateway;
    }

    public function validate()
    {
        parent::validate();

        if (empty($this->clientId)) {
            throw new ConfigurationException('PayPal clientId is required.');
        }

        if (empty($this->clientSecret)) {
            throw new ConfigurationException('PayPal clientSecret is required.');
        }
    }
}
