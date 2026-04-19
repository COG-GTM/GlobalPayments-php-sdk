package com.globalpayments.api.entities.enums;

public enum MerchantAccountStatus {
    ACTIVE("ACTIVE"),
    INACTIVE("INACTIVE");

    private final String value;

    MerchantAccountStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static MerchantAccountStatus fromValue(String value) {
        for (MerchantAccountStatus item : values()) {
            if (item.value.equals(value)) {
                return item;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }
}
