package com.globalpayments.api.entities.enums;

public enum ManualEntryMethod {
    MOTO(0),
    MAIL(1),
    PHONE(2),
    KEYED(3);

    private final int value;

    ManualEntryMethod(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static ManualEntryMethod fromValue(int value) {
        for (ManualEntryMethod item : values()) {
            if (item.value == value) {
                return item;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }
}
