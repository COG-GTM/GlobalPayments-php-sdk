package com.globalpayments.api.entities.enums;

public enum MessageCategory {
    PAYMENT_AUTHENTICATION("PAYMENT_AUTHENTICATION"),
    NON_PAYMENT_AUTHENTICATION("NON_PAYMENT_AUTHENTICATION");

    private final String value;

    MessageCategory(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static MessageCategory fromValue(String value) {
        for (MessageCategory item : values()) {
            if (item.value.equals(value)) {
                return item;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }
}
