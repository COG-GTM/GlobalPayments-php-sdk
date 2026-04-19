package com.globalpayments.api.entities.enums;

public enum ExemptionReason {
    APPLY_EXEMPTION("APPLY_EXEMPTION"),
    EOS_CONTINUE("CONTINUE"),
    FORCE_SECURE("FORCE_SECURE"),
    BLOCK("BLOCK");

    private final String value;

    ExemptionReason(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static ExemptionReason fromValue(String value) {
        for (ExemptionReason item : values()) {
            if (item.value.equals(value)) {
                return item;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }
}
