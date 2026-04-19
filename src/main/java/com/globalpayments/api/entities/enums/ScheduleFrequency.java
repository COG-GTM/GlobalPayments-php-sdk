package com.globalpayments.api.entities.enums;

public enum ScheduleFrequency {
    WEEKLY("Weekly"),
    BI_WEEKLY("Bi-Weekly"),
    SEMI_MONTHLY("Semi-Monthly"),
    MONTHLY("Monthly"),
    BI_MONTHLY("Bi-Monthly"),
    QUARTERLY("Quarterly"),
    SEMI_ANNUALLY("Semi-Annually"),
    ANNUALLY("Annually");

    private final String value;

    ScheduleFrequency(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static ScheduleFrequency fromValue(String value) {
        for (ScheduleFrequency item : values()) {
            if (item.value.equals(value)) {
                return item;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }
}
