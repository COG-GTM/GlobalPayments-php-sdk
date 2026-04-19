package com.globalpayments.api.entities.enums;

public enum FraudFilterMode {
    NONE("NONE"),
    OFF("OFF"),
    PASSIVE("PASSIVE"),
    ACTIVE("ACTIVE");

    private final String value;

    FraudFilterMode(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static FraudFilterMode fromValue(String value) {
        for (FraudFilterMode item : values()) {
            if (item.value.equals(value)) {
                return item;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }
}
