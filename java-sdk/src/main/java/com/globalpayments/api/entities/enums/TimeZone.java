package com.globalpayments.api.entities.enums;

public enum TimeZone {
    UTC("UTC"),
    PT("PT"),
    MST("MST"),
    MT("MT"),
    CT("CT"),
    ET("ET"),
    HST("HST"),
    AT("AT"),
    AST("AST"),
    AKST("AKST");

    private final String value;

    TimeZone(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static TimeZone fromValue(String value) {
        for (TimeZone item : values()) {
            if (item.value.equals(value)) {
                return item;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }
}
