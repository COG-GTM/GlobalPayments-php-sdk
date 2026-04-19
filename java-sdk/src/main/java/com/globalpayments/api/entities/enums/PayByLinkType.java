package com.globalpayments.api.entities.enums;

public enum PayByLinkType {
    PAYMENT("PAYMENT"),
    HOSTED_PAYMENT_PAGE("HOSTED_PAYMENT_PAGE"),
    THIRD_PARTY_PAGE("THIRD_PARTY_PAGE"),
    EXCHANGE_APP_CREDENTIALS("EXCHANGE_APP_CREDENTIALS");

    private final String value;

    PayByLinkType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static PayByLinkType fromValue(String value) {
        for (PayByLinkType item : values()) {
            if (item.value.equals(value)) {
                return item;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }
}
