package com.globalpayments.api.entities.enums;

public enum PreOrderIndicator {
    MERCHANDISE_AVAILABLE("MERCHANDISE_AVAILABLE"),
    FUTURE_AVAILABILITY("FUTURE_AVAILABILITY");

    private final String value;

    PreOrderIndicator(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static PreOrderIndicator fromValue(String value) {
        for (PreOrderIndicator item : values()) {
            if (item.value.equals(value)) {
                return item;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }
}
