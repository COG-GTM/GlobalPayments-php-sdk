package com.globalpayments.api.entities.enums;

public enum BankPaymentType {
    FASTERPAYMENTS("FASTERPAYMENTS"),
    SEPA("SEPA");

    private final String value;

    BankPaymentType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static BankPaymentType fromValue(String value) {
        for (BankPaymentType item : values()) {
            if (item.value.equals(value)) {
                return item;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }
}
