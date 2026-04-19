package com.globalpayments.api.entities.enums;

public enum DeliveryTimeFrame {
    ELECTRONIC_DELIVERY("ELECTRONIC_DELIVERY"),
    SAME_DAY("SAME_DAY"),
    OVERNIGHT("OVERNIGHT"),
    TWO_DAYS_OR_MORE("TWO_DAYS_OR_MORE");

    private final String value;

    DeliveryTimeFrame(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static DeliveryTimeFrame fromValue(String value) {
        for (DeliveryTimeFrame item : values()) {
            if (item.value.equals(value)) {
                return item;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }
}
