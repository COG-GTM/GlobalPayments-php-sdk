package com.globalpayments.api.entities.enums;

public enum PaymentMethodUsageMode {
    SINGLE("SINGLE"),
    MULTIPLE("MULTIPLE");

    private final String value;

    PaymentMethodUsageMode(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static PaymentMethodUsageMode fromValue(String value) {
        for (PaymentMethodUsageMode item : values()) {
            if (item.value.equals(value)) {
                return item;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }
}
