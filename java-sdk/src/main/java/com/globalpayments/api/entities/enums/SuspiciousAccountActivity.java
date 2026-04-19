package com.globalpayments.api.entities.enums;

public enum SuspiciousAccountActivity {
    SUSPICIOUS_ACTIVITY("SUSPICIOUS_ACTIVITY"),
    NO_SUSPICIOUS_ACTIVITY("NO_SUSPICIOUS_ACTIVITY");

    private final String value;

    SuspiciousAccountActivity(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static SuspiciousAccountActivity fromValue(String value) {
        for (SuspiciousAccountActivity item : values()) {
            if (item.value.equals(value)) {
                return item;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }
}
