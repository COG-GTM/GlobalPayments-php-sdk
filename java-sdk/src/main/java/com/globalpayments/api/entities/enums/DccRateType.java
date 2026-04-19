package com.globalpayments.api.entities.enums;

public enum DccRateType {
    SALE("S"),
    REFUND("R");

    private final String value;

    DccRateType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static DccRateType fromValue(String value) {
        for (DccRateType item : values()) {
            if (item.value.equals(value)) {
                return item;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }
}
