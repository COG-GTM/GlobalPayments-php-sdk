package com.globalpayments.api.entities.enums;

public enum HostedPaymentMethods {
    OB("ob"),
    CARDS("cards");

    private final String value;

    HostedPaymentMethods(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static HostedPaymentMethods fromValue(String value) {
        for (HostedPaymentMethods item : values()) {
            if (item.value.equals(value)) {
                return item;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }
}
