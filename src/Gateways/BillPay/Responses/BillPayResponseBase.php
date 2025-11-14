<?php

namespace GlobalPayments\Api\Gateways\BillPay\Responses;

use GlobalPayments\Api\Entities\Enums\PaymentMethodType;
use GlobalPayments\Api\Gateways\Interfaces\IBillPayResponse;
use GlobalPayments\Api\Utils\{Element, ElementTree};

abstract class BillPayResponseBase implements IBillPayResponse
{
    /** @var Element */
    protected $response;

    /** @var string */
    protected $responseTagName;

    public function withResponseTagName(string $tagName): IBillPayResponse
    {
        $this->responseTagName = $tagName;
        return $this;
    }

    public function withResponse(string $response): IBillPayResponse
    {
        /** @var array<String> */
        $namespaces = [];

        $namespaces["s"] = "http://schemas.xmlsoap.org/soap/envelope/";
        $namespaces[""] = "https://test.heartlandpaymentservices.net/BillingDataManagement/v3/BillingDataManagementService";
        $namespaces["a"] = "http://schemas.datacontract.org/2004/07/BDMS.NewModel";
        $namespaces["i"] = "http://www.w3.org/2001/XMLSchema-instance";

        $this->response = ElementTree::parse($response, $namespaces)->get($this->responseTagName);

        return $this;
    }

    protected function getFirstResponseCode(Element $response): string
    {
        $message = $response->get("a:Messages");
        return $message->getString("a:Code");
    }

    protected function getFirstResponseMessage(Element $response): ?string
    {
        $message = $response->get("a:Messages");
        return $message->getString("a:MessageDescription");
    }

    protected function getPaymentMethodType($paymentMethod)
    {
        $paymentMethodType = null;

        if (strpos($paymentMethod, "Credit") !== false) {
            $paymentMethodType = PaymentMethodType::CREDIT;
        } elseif (strpos($paymentMethod, "Debit") !== false) {
            $paymentMethodType = PaymentMethodType::DEBIT;
        } elseif (strpos($paymentMethod, "ACH") !== false) {
            $paymentMethodType = PaymentMethodType::ACH;
        }

        return $paymentMethodType;
    }

    protected function getCardType(string $cardType)
    {
        if (strpos($cardType, "Visa") !== false) {
            return "VISA";
        } elseif (strpos($cardType, "Mastercard") !== false) {
            return "MC";
        } elseif (strpos($cardType, "Discover") !== false) {
            return "DISC";
        } elseif (strpos($cardType, "AmericanExpress") !== false) {
            return "AMEX";
        } else {
            return "";
        }
    }
}
