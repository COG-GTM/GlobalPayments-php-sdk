package com.globalpayments.api.entities.enums;

public enum BNPLType {
    AFFIRM("AFFIRM"),
    CLEARPAY("CLEARPAY"),
    KLARNA("KLARNA");

    private final String value;

    BNPLType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static BNPLType fromValue(String value) {
        for (BNPLType item : values()) {
            if (item.value.equals(value)) {
                return item;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }
}
