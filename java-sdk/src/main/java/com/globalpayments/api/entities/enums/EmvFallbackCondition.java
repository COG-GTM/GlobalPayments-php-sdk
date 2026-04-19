package com.globalpayments.api.entities.enums;

public enum EmvFallbackCondition {
    CHIP_READ_FAILURE("ChipReadFailure"),
    NO_CANDIDATE_LIST("NoCandidateList");

    private final String value;

    EmvFallbackCondition(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static EmvFallbackCondition fromValue(String value) {
        for (EmvFallbackCondition item : values()) {
            if (item.value.equals(value)) {
                return item;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }
}
