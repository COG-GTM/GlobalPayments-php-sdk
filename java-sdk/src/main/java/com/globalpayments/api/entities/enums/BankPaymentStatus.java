package com.globalpayments.api.entities.enums;

public enum BankPaymentStatus {
    PAYMENT_INITIATED("PAYMENT_INITIATED"),
    REQUEST_CONSUMER_CONSENT("REQUEST_CONSUMER_CONSENT"),
    PROCESSING("PROCESSING"),
    UNKNOWN("UNKNOWN"),
    SUCCESS("SUCCESS"),
    INVALID_STATUS("INVALID_STATUS"),
    FAILURE_INSUFFICIENT_FUNDS("FAILURE_INSUFFICIENT_FUNDS"),
    FAILURE_INVALID_CURRENCY("FAILURE_INVALID_CURRENCY"),
    FAILURE_GENERIC("FAILURE_GENERIC"),
    FAILURE_PERMISSION_DENIED("FAILURE_PERMISSION_DENIED"),
    FAILURE_CANCELED("FAILURE_CANCELED"),
    FAILURE_QUOTE_EXPIRED("FAILURE_QUOTE_EXPIRED"),
    FAILURE_INVALID_AMOUNT("FAILURE_INVALID_AMOUNT"),
    FAILURE_INVALID_QUOTE("FAILURE_INVALID_QUOTE"),
    FAILURE_EXPIRED("FAILURE_EXPIRED"),
    PENDING_EXTERNAL_AUTHORIZATION("PENDING_EXTERNAL_AUTHORIZATION"),
    FAILURE_DECLINED("FAILURE_DECLINED"),
    STATUS_NOT_AVAILABLE("STATUS_NOT_AVAILABLE"),
    PAYMENT_NOT_COMPLETED("PAYMENT_NOT_COMPLETED"),
    INITIATION_PROCESSING("INITIATION_PROCESSING");

    private final String value;

    BankPaymentStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static BankPaymentStatus fromValue(String value) {
        for (BankPaymentStatus item : values()) {
            if (item.value.equals(value)) {
                return item;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }
}
