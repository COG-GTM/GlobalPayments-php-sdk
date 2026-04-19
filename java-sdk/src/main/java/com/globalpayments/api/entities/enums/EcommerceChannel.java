package com.globalpayments.api.entities.enums;

public enum EcommerceChannel {
    ECOM("ECOM"),
    MOTO("MOTO");

    private final String value;

    EcommerceChannel(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static EcommerceChannel fromValue(String value) {
        for (EcommerceChannel item : values()) {
            if (item.value.equals(value)) {
                return item;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }
}
