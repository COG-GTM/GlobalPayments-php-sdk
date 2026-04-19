package com.globalpayments.api.entities.enums;

public enum Secure3dVersion {
    NONE("NONE"),
    ONE("ONE"),
    TWO("TWO"),
    ANY("ANY");

    private final String value;

    Secure3dVersion(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Secure3dVersion fromValue(String value) {
        for (Secure3dVersion item : values()) {
            if (item.value.equals(value)) {
                return item;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }
}
