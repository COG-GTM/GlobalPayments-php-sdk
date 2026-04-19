package com.globalpayments.api.entities.enums;

public enum BNPLShippingMethod {
    DELIVERY("DELIVERY"),
    COLLECTION("COLLECTION"),
    EMAIL("EMAIL");

    private final String value;

    BNPLShippingMethod(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static BNPLShippingMethod fromValue(String value) {
        for (BNPLShippingMethod item : values()) {
            if (item.value.equals(value)) {
                return item;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }
}
