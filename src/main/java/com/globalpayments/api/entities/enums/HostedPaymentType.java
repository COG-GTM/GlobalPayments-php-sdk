package com.globalpayments.api.entities.enums;

public enum HostedPaymentType {
    NONE("NONE"),
    MAKE_PAYMENT("MAKE_PAYMENT"),
    MAKE_PAYMENT_RETURN_TOKEN("MAKE_PAYMENT_RETURN_TOKEN"),
    GET_TOKEN("GET_TOKEN"),
    MY_ACCOUNT("MY_ACCOUNT");

    private final String value;

    HostedPaymentType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static HostedPaymentType fromValue(String value) {
        for (HostedPaymentType item : values()) {
            if (item.value.equals(value)) {
                return item;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }
}
