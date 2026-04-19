package com.globalpayments.api.entities.enums;

public enum Channel {
    CardNotPresent("CNP"),
    CardPresent("CP");

    private final String value;

    Channel(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Channel fromValue(String value) {
        for (Channel item : values()) {
            if (item.value.equals(value)) {
                return item;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }
}
