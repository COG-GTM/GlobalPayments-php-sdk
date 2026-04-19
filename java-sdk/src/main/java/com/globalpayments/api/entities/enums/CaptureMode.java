package com.globalpayments.api.entities.enums;

public enum CaptureMode {
    AUTO("AUTO"),
    LATER("LATER"),
    MULTIPLE("MULTIPLE");

    private final String value;

    CaptureMode(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static CaptureMode fromValue(String value) {
        for (CaptureMode item : values()) {
            if (item.value.equals(value)) {
                return item;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }
}
