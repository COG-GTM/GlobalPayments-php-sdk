package com.globalpayments.api.entities.enums;

public enum AgeIndicator {
    NO_ACCOUNT("NO_ACCOUNT"),
    NO_CHANGE("NO_CHANGE"),
    THIS_TRANSACTION("THIS_TRANSACTION"),
    LESS_THAN_THIRTY_DAYS("LESS_THAN_THIRTY_DAYS"),
    THIRTY_TO_SIXTY_DAYS("THIRTY_TO_SIXTY_DAYS"),
    MORE_THAN_SIXTY_DAYS("MORE_THAN_SIXTY_DAYS");

    private final String value;

    AgeIndicator(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static AgeIndicator fromValue(String value) {
        for (AgeIndicator item : values()) {
            if (item.value.equals(value)) {
                return item;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }
}
