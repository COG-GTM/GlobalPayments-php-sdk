package com.globalpayments.api.entities.enums;

public enum PaymentMethodName {
    APM("APM"),
    DIGITAL_WALLET("DIGITAL WALLET"),
    CARD("CARD"),
    BANK_TRANSFER("BANK_TRANSFER"),
    BANK_PAYMENT("BANK PAYMENT"),
    BNPL("BNPL");

    private final String value;

    PaymentMethodName(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static PaymentMethodName fromValue(String value) {
        for (PaymentMethodName item : values()) {
            if (item.value.equals(value)) {
                return item;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }
}
