package com.globalpayments.api.entities.enums;

public enum PaymentType {
    REFUND("REFUND"),
    SALE("SALE");

    private final String value;

    PaymentType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static PaymentType fromValue(String value) {
        for (PaymentType item : values()) {
            if (item.value.equals(value)) {
                return item;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }
}
