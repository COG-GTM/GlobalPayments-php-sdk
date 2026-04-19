package com.globalpayments.api.entities.enums;

public enum MerchantAccountsSortProperty {
    TIME_CREATED("TIME_CREATED");

    private final String value;

    MerchantAccountsSortProperty(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static MerchantAccountsSortProperty fromValue(String value) {
        for (MerchantAccountsSortProperty item : values()) {
            if (item.value.equals(value)) {
                return item;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }
}
