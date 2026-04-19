package com.globalpayments.api.entities.enums;

public enum UcafIndicator {
    NOT_SUPPORTED("0"),
    MERCHANT_ONLY("1"),
    FULLY_AUTHENTICATED("2"),
    ISSUER_RISK_BASED("5"),
    MERCHANT_RISK_BASED("6"),
    PARTIAL_SHIPMENT_INCREMENTAL("7");

    private final String value;

    UcafIndicator(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static UcafIndicator fromValue(String value) {
        for (UcafIndicator item : values()) {
            if (item.value.equals(value)) {
                return item;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }
}
