package com.globalpayments.api.entities.enums;

public enum PersonFunctions {
    APPLICANT("APPLICANT"),
    BENEFICIAL_OWNER("BENEFICIAL_OWNER"),
    PAYMENT_METHOD_OWNER("PAYMENT_METHOD_OWNER");

    private final String value;

    PersonFunctions(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static PersonFunctions fromValue(String value) {
        for (PersonFunctions item : values()) {
            if (item.value.equals(value)) {
                return item;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }
}
