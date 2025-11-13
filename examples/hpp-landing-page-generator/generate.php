<?php

require_once('../../autoload_standalone.php');

use GlobalPayments\Api\Entities\Enums\HppVersion;
use GlobalPayments\Api\Entities\HostedPaymentData;
use GlobalPayments\Api\HostedPaymentConfig;
use GlobalPayments\Api\ServiceConfigs\Gateways\GpEcomConfig;
use GlobalPayments\Api\Services\HostedService;
use GlobalPayments\Api\ServicesContainer;

function loadConfig($configFile = 'config.json')
{
    if (!file_exists($configFile)) {
        throw new Exception("Configuration file not found: $configFile");
    }
    
    $configJson = file_get_contents($configFile);
    $config = json_decode($configJson, true);
    
    if (json_last_error() !== JSON_ERROR_NONE) {
        throw new Exception("Invalid JSON in configuration file: " . json_last_error_msg());
    }
    
    return $config;
}

function generateStyles($config)
{
    $primaryColor = $config['brand']['primary_color'];
    $secondaryColor = $config['brand']['secondary_color'];
    $fontFamily = $config['brand']['font_family'];
    
    $css = <<<CSS
* {
    margin: 0;
    padding: 0;
    box-sizing: border-box;
}

body {
    font-family: {$fontFamily};
    background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
    min-height: 100vh;
    display: flex;
    align-items: center;
    justify-content: center;
    padding: 20px;
}

.container {
    max-width: 600px;
    width: 100%;
    background: {$secondaryColor};
    border-radius: 12px;
    box-shadow: 0 10px 40px rgba(0, 0, 0, 0.1);
    overflow: hidden;
}

.page-header {
    background: {$primaryColor};
    color: {$secondaryColor};
    padding: 40px 30px;
    text-align: center;
}

.brand-logo {
    max-width: 200px;
    height: auto;
    margin-bottom: 20px;
}

.page-header h1 {
    font-size: 28px;
    margin-bottom: 10px;
    font-weight: 600;
}

.page-description {
    font-size: 16px;
    opacity: 0.9;
}

.payment-section {
    padding: 40px 30px;
}

.payment-card h2 {
    font-size: 22px;
    margin-bottom: 20px;
    color: #333;
}

.payment-info {
    background: #f8f9fa;
    padding: 20px;
    border-radius: 8px;
    margin-bottom: 30px;
}

.payment-info p {
    font-size: 16px;
    color: #555;
    margin-bottom: 10px;
}

.payment-info p:last-child {
    margin-bottom: 0;
}

.amount {
    font-size: 24px;
    font-weight: 700;
    color: {$primaryColor};
}

#payment-form {
    margin-bottom: 20px;
}

.form-actions {
    display: flex;
    gap: 15px;
    margin-top: 30px;
}

.btn {
    flex: 1;
    padding: 15px 30px;
    font-size: 16px;
    font-weight: 600;
    border: none;
    border-radius: 8px;
    cursor: pointer;
    text-decoration: none;
    text-align: center;
    transition: all 0.3s ease;
    display: inline-block;
}

.btn-primary {
    background: {$primaryColor};
    color: {$secondaryColor};
}

.btn-primary:hover {
    opacity: 0.9;
    transform: translateY(-2px);
    box-shadow: 0 5px 15px rgba(0, 0, 0, 0.2);
}

.btn-secondary {
    background: #6c757d;
    color: white;
}

.btn-secondary:hover {
    background: #5a6268;
}

.security-notice {
    display: flex;
    align-items: center;
    gap: 10px;
    padding: 15px;
    background: #e8f5e9;
    border-radius: 8px;
    color: #2e7d32;
    font-size: 14px;
}

.security-icon {
    flex-shrink: 0;
}

.page-footer {
    background: #f8f9fa;
    padding: 20px 30px;
    text-align: center;
    color: #666;
    font-size: 14px;
}

.page-footer p {
    margin-bottom: 5px;
}

.powered-by {
    font-size: 12px;
    opacity: 0.7;
}

@media (max-width: 600px) {
    .form-actions {
        flex-direction: column;
    }
    
    .btn {
        width: 100%;
    }
}
CSS;
    
    return $css;
}

function generateHppFields($config)
{
    $gpEcomConfig = new GpEcomConfig();
    $gpEcomConfig->merchantId = $config['payment']['merchant_id'];
    $gpEcomConfig->accountId = $config['payment']['account'];
    $gpEcomConfig->sharedSecret = $config['payment']['shared_secret'];
    $gpEcomConfig->serviceUrl = 'https://pay.sandbox.realexpayments.com/pay';
    $gpEcomConfig->hostedPaymentConfig = new HostedPaymentConfig();
    $gpEcomConfig->hostedPaymentConfig->version = HppVersion::VERSION_2;
    
    ServicesContainer::configureService($gpEcomConfig);
    
    $hostedPaymentData = new HostedPaymentData();
    $hostedPaymentData->customerExists = false;
    
    $service = new HostedService($gpEcomConfig);
    
    $hppJson = $service->charge($config['payment']['amount'])
        ->withCurrency($config['payment']['currency'])
        ->withHostedPaymentData($hostedPaymentData)
        ->serialize();
    
    $hppData = json_decode($hppJson, true);
    
    $fieldsHtml = '';
    foreach ($hppData as $key => $value) {
        $escapedValue = htmlspecialchars($value, ENT_QUOTES, 'UTF-8');
        $fieldsHtml .= "                    <input type=\"hidden\" name=\"$key\" value=\"$escapedValue\">\n";
    }
    
    return [
        'fields' => $fieldsHtml,
        'url' => $gpEcomConfig->serviceUrl
    ];
}

function generateLandingPage($config)
{
    $headerTemplate = file_get_contents('templates/header.html');
    $paymentFormTemplate = file_get_contents('templates/payment-form.html');
    $footerTemplate = file_get_contents('templates/footer.html');
    
    $hppData = generateHppFields($config);
    
    $replacements = [
        '{{PAGE_TITLE}}' => htmlspecialchars($config['page']['title'], ENT_QUOTES, 'UTF-8'),
        '{{PAGE_DESCRIPTION}}' => htmlspecialchars($config['page']['description'], ENT_QUOTES, 'UTF-8'),
        '{{LOGO_URL}}' => htmlspecialchars($config['brand']['logo_url'], ENT_QUOTES, 'UTF-8'),
        '{{BRAND_NAME}}' => htmlspecialchars($config['brand']['name'], ENT_QUOTES, 'UTF-8'),
        '{{CURRENCY}}' => htmlspecialchars($config['payment']['currency'], ENT_QUOTES, 'UTF-8'),
        '{{AMOUNT}}' => htmlspecialchars($config['payment']['amount'], ENT_QUOTES, 'UTF-8'),
        '{{HPP_URL}}' => htmlspecialchars($hppData['url'], ENT_QUOTES, 'UTF-8'),
        '{{HPP_FIELDS}}' => $hppData['fields'],
        '{{CANCEL_URL}}' => htmlspecialchars($config['page']['cancel_url'], ENT_QUOTES, 'UTF-8'),
        '{{CURRENT_YEAR}}' => date('Y')
    ];
    
    $html = $headerTemplate . $paymentFormTemplate . $footerTemplate;
    
    foreach ($replacements as $placeholder => $value) {
        $html = str_replace($placeholder, $value, $html);
    }
    
    return $html;
}

try {
    echo "HPP Landing Page Generator\n";
    echo "==========================\n\n";
    
    $config = loadConfig();
    echo "✓ Configuration loaded successfully\n";
    
    if (!is_dir('output')) {
        mkdir('output', 0755, true);
    }
    
    $styles = generateStyles($config);
    file_put_contents('output/styles.css', $styles);
    echo "✓ Generated styles.css\n";
    
    $html = generateLandingPage($config);
    file_put_contents('output/index.html', $html);
    echo "✓ Generated index.html\n";
    
    echo "\n✓ Landing page generated successfully!\n";
    echo "  Output files:\n";
    echo "  - output/index.html\n";
    echo "  - output/styles.css\n";
    echo "\nYou can now deploy these files to your web server.\n";
    
} catch (Exception $e) {
    echo "✗ Error: " . $e->getMessage() . "\n";
    exit(1);
}
