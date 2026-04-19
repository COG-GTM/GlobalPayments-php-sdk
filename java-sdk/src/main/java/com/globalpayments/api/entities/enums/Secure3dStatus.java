package com.globalpayments.api.entities.enums;

public enum Secure3dStatus {
    SUCCESS_AUTHENTICATED("SUCCESS_AUTHENTICATED"),
    SUCCESS_ATTEMPT_MADE("SUCCESS_ATTEMPT_MADE"),
    NOT_AUTHENTICATED("NOT_AUTHENTICATED"),
    FAILED("FAILED"),
    NOT_ENROLLED("NOT_ENROLLED"),
    AVAILABLE("AVAILABLE"),
    ENROLLED("ENROLLED"),
    CHALLENGE_REQUIRED("CHALLENGE_REQUIRED");

    private final String value;

    Secure3dStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Secure3dStatus fromValue(String value) {
        for (Secure3dStatus item : values()) {
            if (item.value.equals(value)) {
                return item;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }
}
