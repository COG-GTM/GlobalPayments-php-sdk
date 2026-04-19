package com.globalpayments.api.entities.enums;

public enum Environment {
    TEST("TEST"),
    PRODUCTION("PRODUCTION");

    private final String value;

    Environment(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Environment fromValue(String value) {
        for (Environment item : values()) {
            if (item.value.equals(value)) {
                return item;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }
}
