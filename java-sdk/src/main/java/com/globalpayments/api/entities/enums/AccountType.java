package com.globalpayments.api.entities.enums;

public enum AccountType {
    CHECKING(0),
    SAVINGS(1),
    CREDIT(2);

    private final int value;

    AccountType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static AccountType fromValue(int value) {
        for (AccountType item : values()) {
            if (item.value == value) {
                return item;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }
}
