package com.globalpayments.api.entities.enums;

public enum RecurringType {
    FIXED("FIXED"),
    VARIABLE("VARIABLE");

    private final String value;

    RecurringType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static RecurringType fromValue(String value) {
        for (RecurringType item : values()) {
            if (item.value.equals(value)) {
                return item;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }
}
