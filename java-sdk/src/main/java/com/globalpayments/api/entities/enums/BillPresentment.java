package com.globalpayments.api.entities.enums;

public enum BillPresentment {
    FULL("FULL");

    private final String value;

    BillPresentment(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static BillPresentment fromValue(String value) {
        for (BillPresentment item : values()) {
            if (item.value.equals(value)) {
                return item;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }
}
