package com.globalpayments.api.entities.enums;

public enum TransactionLanguage {
    EN_US("en-US"),
    EN_CA("en-CA"),
    FR_CA("fr-CA"),
    EN_AU("en-AU"),
    EN_NZ("en-NZ"),
    EN_GB("en-GB");

    private final String value;

    TransactionLanguage(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static TransactionLanguage fromValue(String value) {
        for (TransactionLanguage item : values()) {
            if (item.value.equals(value)) {
                return item;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }
}
