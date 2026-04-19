package com.globalpayments.api.entities.enums;

public enum ExemptStatus {
    LOW_VALUE("LOW_VALUE"),
    TRANSACTION_RISK_ANALYSIS("TRANSACTION_RISK_ANALYSIS"),
    TRUSTED_MERCHANT("TRUSTED_MERCHANT"),
    SECURE_CORPORATE_PAYMENT("SECURE_CORPORATE_PAYMENT"),
    SCA_DELEGATION("SCA_DELEGATION");

    private final String value;

    ExemptStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static ExemptStatus fromValue(String value) {
        for (ExemptStatus item : values()) {
            if (item.value.equals(value)) {
                return item;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }
}
