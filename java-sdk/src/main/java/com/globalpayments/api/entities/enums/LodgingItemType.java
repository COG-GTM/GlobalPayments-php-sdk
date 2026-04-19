package com.globalpayments.api.entities.enums;

public enum LodgingItemType {
    RESTAURANT("RESTAURANT"),
    GIFT_SHOP("GIFT_SHOP"),
    MINI_BAR("MINI_BAR"),
    PHONE("PHONE"),
    LAUNDRY("LAUNDRY"),
    OTHER("OTHER"),
    NO_SHOW("NO_SHOW");

    private final String value;

    LodgingItemType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static LodgingItemType fromValue(String value) {
        for (LodgingItemType item : values()) {
            if (item.value.equals(value)) {
                return item;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }
}
