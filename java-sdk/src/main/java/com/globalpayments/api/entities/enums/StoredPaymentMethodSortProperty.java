package com.globalpayments.api.entities.enums;

public enum StoredPaymentMethodSortProperty {
    TIME_CREATED("TIME_CREATED");

    private final String value;

    StoredPaymentMethodSortProperty(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static StoredPaymentMethodSortProperty fromValue(String value) {
        for (StoredPaymentMethodSortProperty item : values()) {
            if (item.value.equals(value)) {
                return item;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }
}
