package com.globalpayments.api.entities.enums;

public enum FundsStatus {
    CAPTURED("CAPTURED"),
    DECLINE("DECLINE");

    private final String value;

    FundsStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static FundsStatus fromValue(String value) {
        for (FundsStatus item : values()) {
            if (item.value.equals(value)) {
                return item;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }
}
