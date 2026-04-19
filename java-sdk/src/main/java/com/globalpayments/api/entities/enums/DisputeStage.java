package com.globalpayments.api.entities.enums;

public enum DisputeStage {
    RETRIEVAL("RETRIEVAL"),
    CHARGEBACK("CHARGEBACK"),
    REVERSAL("REVERSAL"),
    SECOND_CHARGEBACK("SECOND_CHARGEBACK"),
    PRE_ARBITRATION("PRE_ARBITRATION"),
    ARBITRATION("ARBITRATION"),
    PRE_COMPLIANCE("PRE_COMPLIANCE"),
    COMPLIANCE("COMPLIANCE"),
    GOODFAITH("GOODFAITH");

    private final String value;

    DisputeStage(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static DisputeStage fromValue(String value) {
        for (DisputeStage item : values()) {
            if (item.value.equals(value)) {
                return item;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }
}
