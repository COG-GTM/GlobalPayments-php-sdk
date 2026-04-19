package com.globalpayments.api.entities.enums;

public enum StoredCredentialSequence {
    FIRST("first"),
    SUBSEQUENT("subsequent"),
    LAST("last");

    private final String value;

    StoredCredentialSequence(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static StoredCredentialSequence fromValue(String value) {
        for (StoredCredentialSequence item : values()) {
            if (item.value.equals(value)) {
                return item;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }
}
