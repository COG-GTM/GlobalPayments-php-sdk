package com.globalpayments.api.entities.enums;

public enum StatusChangeReason {
    ACTIVE("ACTIVE"),
    CLOSED_BY_MERCHANT("CLOSED_BY_MERCHANT"),
    CLOSED_BY_RISK("CLOSED_BY_RISK"),
    APPLICATION_DENIED("APPLICATION_DENIED"),
    PENDING_REVIEW("PENDING_REVIEW"),
    PENDING_MERCHANT_CONSENT("PENDING_MERCHANT_CONSENT"),
    PENDING_IDENTITY_VALIDATION("PENDING_IDENTITY_VALIDATION"),
    PENDING_IDENTITY_VALIDATION_AND_PAYMENT("PENDING_IDENTITY_VALIDATION_AND_PAYMENT"),
    PENDING_PAYMENT("PENDING_PAYMENT"),
    UNKNOWN_STATUS("UNKNOWN_STATUS"),
    REMOVE_PARTNERSHIP("REMOVE_PARTNERSHIP");

    private final String value;

    StatusChangeReason(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static StatusChangeReason fromValue(String value) {
        for (StatusChangeReason item : values()) {
            if (item.value.equals(value)) {
                return item;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }
}
