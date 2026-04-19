package com.globalpayments.api.entities.enums;

public enum FraudFilterResult {
    HOLD("HOLD"),
    PASS("PASS"),
    BLOCK("BLOCK"),
    NOT_EXECUTED("NOT_EXECUTED"),
    ERROR("ERROR"),
    RELEASE_SUCCESSFUL("RELEASE_SUCCESSFUL"),
    HOLD_SUCCESSFUL("HOLD_SUCCESSFUL");

    private final String value;

    FraudFilterResult(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static FraudFilterResult fromValue(String value) {
        for (FraudFilterResult item : values()) {
            if (item.value.equals(value)) {
                return item;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }
}
