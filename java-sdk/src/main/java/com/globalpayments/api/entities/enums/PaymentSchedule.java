package com.globalpayments.api.entities.enums;

public enum PaymentSchedule {
    DYNAMIC("Dynamic"),
    FIRST_DAY_OF_THE_MONTH("FirstDayOfTheMonth"),
    LAST_DAY_OF_THE_MONTH("LastDayOfTheMonth");

    private final String value;

    PaymentSchedule(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static PaymentSchedule fromValue(String value) {
        for (PaymentSchedule item : values()) {
            if (item.value.equals(value)) {
                return item;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }
}
