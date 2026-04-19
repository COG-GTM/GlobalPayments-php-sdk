package com.globalpayments.api.entities.enums;

public enum MobilePaymentMethodType {
    APPLEPAY("apple-pay"),
    GOOGLEPAY("pay-with-google");

    private final String value;

    MobilePaymentMethodType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static MobilePaymentMethodType fromValue(String value) {
        for (MobilePaymentMethodType item : values()) {
            if (item.value.equals(value)) {
                return item;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }
}
