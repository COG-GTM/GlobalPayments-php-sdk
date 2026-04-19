package com.globalpayments.api.entities.enums;

public enum StoredCredentialReason {
    INCREMENTAL("INCREMENTAL"),
    RESUBMISSION("RESUBMISSION"),
    REAUTHORIZATION("REAUTHORIZATION"),
    DELAYED("DELAYED"),
    NO_SHOW("NO_SHOW");

    private final String value;

    StoredCredentialReason(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static StoredCredentialReason fromValue(String value) {
        for (StoredCredentialReason item : values()) {
            if (item.value.equals(value)) {
                return item;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }
}
