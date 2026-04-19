package com.globalpayments.api.entities.enums;

public enum StoredCredentialInitiator {
    CARDHOLDER("cardholder"),
    MERCHANT("merchant"),
    SCHEDULED("scheduled"),
    PAYER("PAYER");

    private final String value;

    StoredCredentialInitiator(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static StoredCredentialInitiator fromValue(String value) {
        for (StoredCredentialInitiator item : values()) {
            if (item.value.equals(value)) {
                return item;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }
}
