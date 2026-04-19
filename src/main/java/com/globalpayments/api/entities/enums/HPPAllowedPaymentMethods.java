package com.globalpayments.api.entities.enums;

public enum HPPAllowedPaymentMethods {
    CARD("CARD"),
    BANK_PAYMENT("BANK_PAYMENT"),
    BLIK("BLIK"),
    PAYU("PAYU");

    private final String value;

    HPPAllowedPaymentMethods(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static HPPAllowedPaymentMethods fromValue(String value) {
        for (HPPAllowedPaymentMethods item : values()) {
            if (item.value.equals(value)) {
                return item;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }
}
