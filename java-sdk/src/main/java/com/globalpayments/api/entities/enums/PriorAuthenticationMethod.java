package com.globalpayments.api.entities.enums;

public enum PriorAuthenticationMethod {
    FRICTIONLESS_AUTHENTICATION("FRICTIONLESS_AUTHENTICATION"),
    CHALLENGE_OCCURRED("CHALLENGE_OCCURRED"),
    AVS_VERIFIED("AVS_VERIFIED"),
    OTHER_ISSUER_METHOD("OTHER_ISSUER_METHOD");

    private final String value;

    PriorAuthenticationMethod(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static PriorAuthenticationMethod fromValue(String value) {
        for (PriorAuthenticationMethod item : values()) {
            if (item.value.equals(value)) {
                return item;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }
}
