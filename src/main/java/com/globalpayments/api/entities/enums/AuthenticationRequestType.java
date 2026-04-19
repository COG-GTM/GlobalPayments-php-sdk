package com.globalpayments.api.entities.enums;

public enum AuthenticationRequestType {
    PAYMENT_TRANSACTION("PAYMENT_TRANSACTION"),
    RECURRING_TRANSACTION("RECURRING_TRANSACTION"),
    INSTALMENT_TRANSACTION("INSTALMENT_TRANSACTION"),
    ADD_CARD("ADD_CARD"),
    MAINTAIN_CARD("MAINTAIN_CARD"),
    CARDHOLDER_VERIFICATION("CARDHOLDER_VERIFICATION");

    private final String value;

    AuthenticationRequestType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static AuthenticationRequestType fromValue(String value) {
        for (AuthenticationRequestType item : values()) {
            if (item.value.equals(value)) {
                return item;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }
}
