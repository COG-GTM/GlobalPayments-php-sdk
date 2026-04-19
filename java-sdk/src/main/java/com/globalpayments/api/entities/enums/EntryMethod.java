package com.globalpayments.api.entities.enums;

public enum EntryMethod {
    SWIPE(0),
    PROXIMITY(1),
    MANUAL(2);

    private final int value;

    EntryMethod(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static EntryMethod fromValue(int value) {
        for (EntryMethod item : values()) {
            if (item.value == value) {
                return item;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }
}
