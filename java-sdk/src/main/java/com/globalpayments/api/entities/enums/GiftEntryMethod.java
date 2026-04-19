package com.globalpayments.api.entities.enums;

public enum GiftEntryMethod {
    SWIPE(0),
    PROXIMITY(1),
    MANUAL(2);

    private final int value;

    GiftEntryMethod(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static GiftEntryMethod fromValue(int value) {
        for (GiftEntryMethod item : values()) {
            if (item.value == value) {
                return item;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }
}
