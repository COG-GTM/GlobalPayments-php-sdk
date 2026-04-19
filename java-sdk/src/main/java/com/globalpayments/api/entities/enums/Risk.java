package com.globalpayments.api.entities.enums;

public enum Risk {
    HIGH("HIGH"),
    LOW("LOW");

    private final String value;

    Risk(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Risk fromValue(String value) {
        for (Risk item : values()) {
            if (item.value.equals(value)) {
                return item;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }
}
