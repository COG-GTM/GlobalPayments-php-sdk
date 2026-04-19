package com.globalpayments.api.entities.enums;

public enum PayByLinkStatus {
    ACTIVE("ACTIVE"),
    INACTIVE("INACTIVE"),
    CLOSED("CLOSED"),
    EXPIRED("EXPIRED"),
    PAID("PAID");

    private final String value;

    PayByLinkStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static PayByLinkStatus fromValue(String value) {
        for (PayByLinkStatus item : values()) {
            if (item.value.equals(value)) {
                return item;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }
}
