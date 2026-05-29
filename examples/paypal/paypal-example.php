<?php

/**
 * PayPal Direct Integration Example
 *
 * This example demonstrates how to use the PayPal gateway connector
 * to process payments directly through PayPal's REST API.
 *
 * Prerequisites:
 *   - PayPal developer account (https://developer.paypal.com)
 *   - REST API app with Client ID and Secret
 */

require_once '../../vendor/autoload.php';

use GlobalPayments\Api\Entities\Enums\AlternativePaymentType;
use GlobalPayments\Api\Entities\Enums\Environment;
use GlobalPayments\Api\PaymentMethods\AlternativePaymentMethod;
use GlobalPayments\Api\ServiceConfigs\Gateways\PayPalConfig;
use GlobalPayments\Api\ServicesContainer;

// ----------------------------------------------------------------
// 1. Configure the PayPal gateway
// ----------------------------------------------------------------
$config = new PayPalConfig();
$config->clientId     = 'YOUR_PAYPAL_CLIENT_ID';
$config->clientSecret = 'YOUR_PAYPAL_CLIENT_SECRET';
$config->environment  = Environment::TEST; // or Environment::PRODUCTION

ServicesContainer::configureService($config);

// ----------------------------------------------------------------
// 2. Set up the payment method with return/cancel URLs
// ----------------------------------------------------------------
$paypal = new AlternativePaymentMethod(AlternativePaymentType::PAYPAL);
$paypal->returnUrl = 'https://your-site.com/payment/return';
$paypal->cancelUrl = 'https://your-site.com/payment/cancel';

// ----------------------------------------------------------------
// 3a. Authorize (creates a PayPal order with AUTHORIZE intent)
// ----------------------------------------------------------------
try {
    $authResponse = $paypal->authorize(25.00)
        ->withCurrency('USD')
        ->withDescription('Order #12345')
        ->execute();

    echo "Authorization created!\n";
    echo "Order ID: " . $authResponse->transactionId . "\n";
    echo "Status: " . $authResponse->responseCode . "\n";

    // Redirect customer to PayPal approval URL
    $approvalUrl = $authResponse->alternativePaymentResponse->redirectUrl ?? null;
    if ($approvalUrl) {
        echo "Redirect customer to: " . $approvalUrl . "\n";
    }
} catch (\Exception $e) {
    echo "Authorization failed: " . $e->getMessage() . "\n";
}

// ----------------------------------------------------------------
// 3b. Charge / Sale (creates a PayPal order with CAPTURE intent)
// ----------------------------------------------------------------
try {
    $chargeResponse = $paypal->charge(50.00)
        ->withCurrency('USD')
        ->withDescription('Direct payment')
        ->execute();

    echo "\nCharge created!\n";
    echo "Order ID: " . $chargeResponse->transactionId . "\n";
} catch (\Exception $e) {
    echo "Charge failed: " . $e->getMessage() . "\n";
}

// ----------------------------------------------------------------
// 4. Capture an authorized order (after customer approves)
// ----------------------------------------------------------------
try {
    // Assuming $authResponse from step 3a
    if (isset($authResponse)) {
        $captureResponse = $authResponse->capture()->execute();

        echo "\nCapture completed!\n";
        echo "Status: " . $captureResponse->responseCode . "\n";
    }
} catch (\Exception $e) {
    echo "Capture failed: " . $e->getMessage() . "\n";
}

// ----------------------------------------------------------------
// 5. Void an authorization
// ----------------------------------------------------------------
try {
    if (isset($authResponse)) {
        $voidResponse = $authResponse->void()->execute();

        echo "\nVoid completed!\n";
        echo "Status: " . $voidResponse->responseCode . "\n";
    }
} catch (\Exception $e) {
    echo "Void failed: " . $e->getMessage() . "\n";
}

// ----------------------------------------------------------------
// 6. Refund a captured payment
// ----------------------------------------------------------------
try {
    if (isset($captureResponse)) {
        $refundResponse = $captureResponse->refund(25.00)
            ->withCurrency('USD')
            ->execute();

        echo "\nRefund completed!\n";
        echo "Status: " . $refundResponse->responseCode . "\n";
    }
} catch (\Exception $e) {
    echo "Refund failed: " . $e->getMessage() . "\n";
}
