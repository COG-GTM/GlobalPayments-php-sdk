package com.globalpayments.api.entities.enums;

public enum CheckType {
    PERSONAL(0),
    BUSINESS(1),
    PAYROLL(2);

    private final int value;

    CheckType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static CheckType fromValue(int value) {
        for (CheckType item : values()) {
            if (item.value == value) {
                return item;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }
}
