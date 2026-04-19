package com.globalpayments.api.entities.enums;

public enum MessageVersion {
    VERSION_210("2.1.0");

    private final String value;

    MessageVersion(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static MessageVersion fromValue(String value) {
        for (MessageVersion item : values()) {
            if (item.value.equals(value)) {
                return item;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }
}
