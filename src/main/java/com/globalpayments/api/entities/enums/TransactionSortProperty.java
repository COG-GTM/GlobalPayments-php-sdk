package com.globalpayments.api.entities.enums;

public enum TransactionSortProperty {
    TIME_CREATED("TIME_CREATED"),
    STATUS("STATUS"),
    TYPE("TYPE"),
    DEPOSIT_ID("DEPOSIT_ID"),
    ID("ID");

    private final String value;

    TransactionSortProperty(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static TransactionSortProperty fromValue(String value) {
        for (TransactionSortProperty item : values()) {
            if (item.value.equals(value)) {
                return item;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }
}
