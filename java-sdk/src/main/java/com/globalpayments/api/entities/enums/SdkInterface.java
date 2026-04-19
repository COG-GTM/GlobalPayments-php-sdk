package com.globalpayments.api.entities.enums;

public enum SdkInterface {
    NATIVE("NATIVE"),
    BROWSER("BROWSER"),
    BOTH("BOTH");

    private final String value;

    SdkInterface(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static SdkInterface fromValue(String value) {
        for (SdkInterface item : values()) {
            if (item.value.equals(value)) {
                return item;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }
}
