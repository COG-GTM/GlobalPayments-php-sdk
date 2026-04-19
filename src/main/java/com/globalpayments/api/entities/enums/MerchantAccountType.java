package com.globalpayments.api.entities.enums;

public enum MerchantAccountType {
    TRANSACTION_PROCESSING("TRANSACTION_PROCESSING"),
    DATA_SERVICES("DATA_SERVICES"),
    DISPUTE_MANAGEMENT("DISPUTE_MANAGEMENT"),
    MERCHANT_MANAGEMENT("MERCHANT_MANAGEMENT"),
    TOKENIZATION("TOKENIZATION"),
    FUND_MANAGEMENT("FUND_MANAGEMENT");

    private final String value;

    MerchantAccountType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static MerchantAccountType fromValue(String value) {
        for (MerchantAccountType item : values()) {
            if (item.value.equals(value)) {
                return item;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }
}
