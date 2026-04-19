package com.globalpayments.api.entities.enums;

public enum PaymentProvider {
    OPEN_BANKING("OPEN_BANKING");

    private final String value;

    PaymentProvider(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static PaymentProvider fromValue(String value) {
        for (PaymentProvider item : values()) {
            if (item.value.equals(value)) {
                return item;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }
}
