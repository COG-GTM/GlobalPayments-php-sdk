package com.globalpayments.api.entities.enums;

public enum HPPTypes {
    THIRD_PARTY_PAGE("THIRD_PARTY_PAGE"),
    PAYMENT("PAYMENT"),
    HOSTED_PAYMENT_PAGE("HOSTED_PAYMENT_PAGE"),
    EXCHANGE_APP_CREDENTIALS("EXCHANGE_APP_CREDENTIALS");

    private final String value;

    HPPTypes(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static HPPTypes fromValue(String value) {
        for (HPPTypes item : values()) {
            if (item.value.equals(value)) {
                return item;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }
}
