package com.globalpayments.api.entities.enums;

public enum HPPStorageModes {
    PROMPT("PROMPT"),
    ON_SUCCESS("ON_SUCCESS"),
    ALWAYS("ALWAYS");

    private final String value;

    HPPStorageModes(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static HPPStorageModes fromValue(String value) {
        for (HPPStorageModes item : values()) {
            if (item.value.equals(value)) {
                return item;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }
}
