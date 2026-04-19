package com.globalpayments.api.entities.enums;

public enum ReasonCode {
    FRAUD("FRAUD"),
    FALSE_POSITIVE("FALSEPOSITIVE"),
    OUT_OF_STOCK("OUTOFSTOCK"),
    IN_STOCK("INSTOCK"),
    OTHER("OTHER"),
    NOT_GIVEN("NOTGIVEN");

    private final String value;

    ReasonCode(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static ReasonCode fromValue(String value) {
        for (ReasonCode item : values()) {
            if (item.value.equals(value)) {
                return item;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }
}
