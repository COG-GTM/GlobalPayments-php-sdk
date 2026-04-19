package com.globalpayments.api.entities.enums;

public enum PaymentMethodType {
    REFERENCE(0),
    CREDIT(1),
    DEBIT(2),
    EBT(3),
    CASH(4),
    ACH(5),
    GIFT(6),
    RECURRING(7),
    APM(8),
    BANK_PAYMENT(9),
    BNPL(10),
    ACCOUNT_FUNDS(11);

    private final int value;

    PaymentMethodType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static PaymentMethodType fromValue(int value) {
        for (PaymentMethodType item : values()) {
            if (item.value == value) {
                return item;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }
}
