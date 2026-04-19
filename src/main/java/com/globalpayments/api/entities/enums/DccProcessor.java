package com.globalpayments.api.entities.enums;

public enum DccProcessor {
    FEXCO("Fexco"),
    EUROCONEX("Euroconex");

    private final String value;

    DccProcessor(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static DccProcessor fromValue(String value) {
        for (DccProcessor item : values()) {
            if (item.value.equals(value)) {
                return item;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }
}
