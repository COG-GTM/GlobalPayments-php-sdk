package com.globalpayments.api.entities.enums;

public enum PhoneNumberType {
    HOME("HOME"),
    WORK("WORK"),
    SHIPPING("SHIPPING"),
    MOBILE("MOBILE");

    private final String value;

    PhoneNumberType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static PhoneNumberType fromValue(String value) {
        for (PhoneNumberType item : values()) {
            if (item.value.equals(value)) {
                return item;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }
}
