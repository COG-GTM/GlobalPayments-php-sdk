package com.globalpayments.api.entities.enums;

public enum RecurringSequence {
    FIRST("FIRST"),
    SUBSEQUENT("SUBSEQUENT"),
    LAST("LAST");

    private final String value;

    RecurringSequence(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static RecurringSequence fromValue(String value) {
        for (RecurringSequence item : values()) {
            if (item.value.equals(value)) {
                return item;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }
}
