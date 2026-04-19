package com.globalpayments.api.entities.enums;

public enum PaymentMethodFunction {
    PRIMARY_PAYOUT("PRIMARY_PAYOUT"),
    SECONDARY_PAYOUT("SECONDARY_PAYOUT"),
    ACCOUNT_ACTIVATION_FEE("ACCOUNT_ACTIVATION_FEE");

    private final String value;

    PaymentMethodFunction(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static PaymentMethodFunction fromValue(String value) {
        for (PaymentMethodFunction item : values()) {
            if (item.value.equals(value)) {
                return item;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }
}
