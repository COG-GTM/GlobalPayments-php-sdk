package com.globalpayments.api.entities.enums;

public enum UsableBalanceMode {
    AVAILABLE_BALANCE("AVAILABLE_BALANCE"),
    AVAILABLE_AND_PENDING_BALANCE("AVAILABLE_AND_PENDING_BALANCE");

    private final String value;

    UsableBalanceMode(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static UsableBalanceMode fromValue(String value) {
        for (UsableBalanceMode item : values()) {
            if (item.value.equals(value)) {
                return item;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }
}
