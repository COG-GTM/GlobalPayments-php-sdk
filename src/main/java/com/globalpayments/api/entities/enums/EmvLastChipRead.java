package com.globalpayments.api.entities.enums;

public enum EmvLastChipRead {
    SUCCESSFUL("Successful"),
    FAILED("Failed"),
    NOT_A_CHIP_TRANSACTION("NotAChipTransaction"),
    UNKNOWN("Unknown");

    private final String value;

    EmvLastChipRead(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static EmvLastChipRead fromValue(String value) {
        for (EmvLastChipRead item : values()) {
            if (item.value.equals(value)) {
                return item;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }
}
