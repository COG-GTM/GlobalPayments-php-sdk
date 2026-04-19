package com.globalpayments.api.entities.enums;

public enum OrderTransactionType {
    GOODS_SERVICE_PURCHASE("GOODS_SERVICE_PURCHASE"),
    CHECK_ACCEPTANCE("CHECK_ACCEPTANCE"),
    ACCOUNT_FUNDING("ACCOUNT_FUNDING"),
    QUASI_CASH_TRANSACTION("QUASI_CASH_TRANSACTION"),
    PREPAID_ACTIVATION_AND_LOAD("PREPAID_ACTIVATION_AND_LOAD");

    private final String value;

    OrderTransactionType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static OrderTransactionType fromValue(String value) {
        for (OrderTransactionType item : values()) {
            if (item.value.equals(value)) {
                return item;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }
}
