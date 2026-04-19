package com.globalpayments.api.entities.enums;

public enum ExtraChargeType {
    RESTAURANT(1),
    GIFT_SHOP(2),
    MINI_BAR(3),
    TELEPHONE(4),
    LAUNDRY(5),
    OTHER(10);

    private final int value;

    ExtraChargeType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static ExtraChargeType fromValue(int value) {
        for (ExtraChargeType item : values()) {
            if (item.value == value) {
                return item;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }
}
