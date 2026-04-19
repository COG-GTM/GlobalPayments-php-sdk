package com.globalpayments.api.entities.enums;

public enum TerminalOutputCapability {
    NONE("NONE"),
    PRINT_ONLY("PRINT_ONLY"),
    DISPLAY_ONLY("DISPLAY_ONLY"),
    PRINT_AND_DISPLAY("PRINT_AND_DISPLAY"),
    UNKNOWN("UNKNOWN");

    private final String value;

    TerminalOutputCapability(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static TerminalOutputCapability fromValue(String value) {
        for (TerminalOutputCapability item : values()) {
            if (item.value.equals(value)) {
                return item;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }
}
