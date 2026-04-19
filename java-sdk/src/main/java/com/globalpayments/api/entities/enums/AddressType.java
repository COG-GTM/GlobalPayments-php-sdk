package com.globalpayments.api.entities.enums;

public enum AddressType {
    BILLING("BILLING"),
    SHIPPING("SHIPPING"),
    BUSINESS("BUSINESS");

    private final String value;

    AddressType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static AddressType fromValue(String value) {
        for (AddressType item : values()) {
            if (item.value.equals(value)) {
                return item;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }
}
