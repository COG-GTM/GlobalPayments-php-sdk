package com.globalpayments.api.entities.enums;

public enum CardType {
    VISA("VISA"),
    MASTERCARD("MASTERCARD"),
    DISCOVER("DISCOVER"),
    AMEX("AMEX"),
    JCB("JCB"),
    DINERS("DINERS");

    private final String value;

    CardType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static CardType fromValue(String value) {
        for (CardType item : values()) {
            if (item.value.equals(value)) {
                return item;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }
}
