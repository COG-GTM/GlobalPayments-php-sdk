package com.globalpayments.api.entities.enums;

public enum BillingLoadType {
    NONE("NONE"),
    BILLS("BILLS"),
    SECURE_PAYMENT("SECURE_PAYMENT");

    private final String value;

    BillingLoadType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static BillingLoadType fromValue(String value) {
        for (BillingLoadType item : values()) {
            if (item.value.equals(value)) {
                return item;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }
}
