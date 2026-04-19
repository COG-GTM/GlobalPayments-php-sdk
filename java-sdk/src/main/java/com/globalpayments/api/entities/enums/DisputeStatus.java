package com.globalpayments.api.entities.enums;

public enum DisputeStatus {
    UNDER_REVIEW("UNDER_REVIEW"),
    WITH_MERCHANT("WITH_MERCHANT"),
    CLOSED("CLOSED"),
    SETTLE_DISPUTE_FUNDED("FUNDED"),
    SETTLE_DISPUTE_DELAYED("DELAYED");

    private final String value;

    DisputeStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static DisputeStatus fromValue(String value) {
        for (DisputeStatus item : values()) {
            if (item.value.equals(value)) {
                return item;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }
}
