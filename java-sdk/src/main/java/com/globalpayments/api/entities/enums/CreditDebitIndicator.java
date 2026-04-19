package com.globalpayments.api.entities.enums;

public enum CreditDebitIndicator {
    DEBIT("Debit"),
    CREDIT("Credit");

    private final String value;

    CreditDebitIndicator(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static CreditDebitIndicator fromValue(String value) {
        for (CreditDebitIndicator item : values()) {
            if (item.value.equals(value)) {
                return item;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }
}
