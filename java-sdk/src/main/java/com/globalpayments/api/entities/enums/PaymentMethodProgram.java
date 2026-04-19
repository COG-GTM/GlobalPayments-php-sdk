package com.globalpayments.api.entities.enums;

public enum PaymentMethodProgram {
    ASSURED_RESERVATION("ASSURED_RESERVATION"),
    CARD_DEPOSIT("CARD_DEPOSIT"),
    PURCHASE("PURCHASE"),
    OTHER("OTHER");

    private final String value;

    PaymentMethodProgram(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static PaymentMethodProgram fromValue(String value) {
        for (PaymentMethodProgram item : values()) {
            if (item.value.equals(value)) {
                return item;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }
}
