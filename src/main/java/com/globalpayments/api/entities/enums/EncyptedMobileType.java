package com.globalpayments.api.entities.enums;

public enum EncyptedMobileType {
    APPLE_PAY("apple-pay"),
    GOOGLE_PAY("pay-with-google"),
    CLICK_TO_PAY("click-to-pay");

    private final String value;

    EncyptedMobileType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static EncyptedMobileType fromValue(String value) {
        for (EncyptedMobileType item : values()) {
            if (item.value.equals(value)) {
                return item;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }
}
