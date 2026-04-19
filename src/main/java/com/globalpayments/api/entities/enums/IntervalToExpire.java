package com.globalpayments.api.entities.enums;

public enum IntervalToExpire {
    WEEK("WEEK"),
    DAY("DAY"),
    TWELVE_HOURS("12_HOURS"),
    SIX_HOURS("6_HOURS"),
    THREE_HOURS("3_HOURS"),
    ONE_HOUR("1_HOUR"),
    THIRTY_MINUTES("30_MINUTES"),
    TEN_MINUTES("10_MINUTES"),
    FIVE_MINUTES("5_MINUTES");

    private final String value;

    IntervalToExpire(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static IntervalToExpire fromValue(String value) {
        for (IntervalToExpire item : values()) {
            if (item.value.equals(value)) {
                return item;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }
}
