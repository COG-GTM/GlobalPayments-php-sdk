package com.globalpayments.api.entities.enums;

public enum RiskAssessmentStatus {
    ACCEPTED("ACCEPTED"),
    REJECTED("REJECTED"),
    CHALLENGE("CHALLENGE"),
    PENDING_REVIEW("PENDING_REVIEW");

    private final String value;

    RiskAssessmentStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static RiskAssessmentStatus fromValue(String value) {
        for (RiskAssessmentStatus item : values()) {
            if (item.value.equals(value)) {
                return item;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }
}
