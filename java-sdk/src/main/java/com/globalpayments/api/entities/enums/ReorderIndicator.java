package com.globalpayments.api.entities.enums;

public enum ReorderIndicator {
    FIRST_TIME_ORDER("FIRST_TIME_ORDER"),
    REORDER("REORDER");

    private final String value;

    ReorderIndicator(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static ReorderIndicator fromValue(String value) {
        for (ReorderIndicator item : values()) {
            if (item.value.equals(value)) {
                return item;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }
}
