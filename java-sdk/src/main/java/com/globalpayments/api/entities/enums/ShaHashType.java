package com.globalpayments.api.entities.enums;

public enum ShaHashType {
    SHA1("SHA1"),
    SHA256("SHA256"),
    SHA512("SHA512");

    private final String value;

    ShaHashType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static ShaHashType fromValue(String value) {
        for (ShaHashType item : values()) {
            if (item.value.equals(value)) {
                return item;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }
}
