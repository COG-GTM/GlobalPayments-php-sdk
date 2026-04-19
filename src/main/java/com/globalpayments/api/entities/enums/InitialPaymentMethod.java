package com.globalpayments.api.entities.enums;

public enum InitialPaymentMethod {
    UNASSIGNED("Unassigned"),
    CARD("Card"),
    OTHER("Other");

    private final String value;

    InitialPaymentMethod(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static InitialPaymentMethod fromValue(String value) {
        for (InitialPaymentMethod item : values()) {
            if (item.value.equals(value)) {
                return item;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }
}
