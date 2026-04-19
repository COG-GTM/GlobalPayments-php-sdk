package com.globalpayments.api.entities.enums;

public enum AuthenticationSource {
    BROWSER("BROWSER"),
    STORED_RECURRING("STORED_RECURRING"),
    MOBILE_SDK("MOBILE_SDK"),
    MERCHANT_INITIATED("MERCHANT_INITIATED");

    private final String value;

    AuthenticationSource(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static AuthenticationSource fromValue(String value) {
        for (AuthenticationSource item : values()) {
            if (item.value.equals(value)) {
                return item;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }
}
