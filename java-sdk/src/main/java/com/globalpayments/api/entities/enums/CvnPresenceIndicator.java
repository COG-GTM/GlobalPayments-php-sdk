package com.globalpayments.api.entities.enums;

public enum CvnPresenceIndicator {
    PRESENT(1),
    ILLEGIBLE(2),
    NOT_ON_CARD(3),
    NOT_REQUESTED(4);

    private final int value;

    CvnPresenceIndicator(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static CvnPresenceIndicator fromValue(int value) {
        for (CvnPresenceIndicator item : values()) {
            if (item.value == value) {
                return item;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }
}
