package com.globalpayments.api.entities.enums;

public enum CommercialIndicator {
    LEVEL_II("Level_II"),
    LEVEL_III("Level_III");

    private final String value;

    CommercialIndicator(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static CommercialIndicator fromValue(String value) {
        for (CommercialIndicator item : values()) {
            if (item.value.equals(value)) {
                return item;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }
}
