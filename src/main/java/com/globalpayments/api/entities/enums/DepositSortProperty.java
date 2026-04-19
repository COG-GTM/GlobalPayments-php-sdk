package com.globalpayments.api.entities.enums;

public enum DepositSortProperty {
    TIME_CREATED("TIME_CREATED"),
    STATUS("STATUS"),
    TYPE("TYPE"),
    DEPOSIT_ID("DEPOSIT_ID");

    private final String value;

    DepositSortProperty(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static DepositSortProperty fromValue(String value) {
        for (DepositSortProperty item : values()) {
            if (item.value.equals(value)) {
                return item;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }
}
