package com.globalpayments.api.entities.enums;

public enum DepositStatus {
    FUNDED("FUNDED"),
    SPLIT_FUNDING("SPLIT_FUNDING"),
    DELAYED("DELAYED"),
    RESERVED("RESERVED"),
    IRREG("IRREG"),
    RELEASED("RELEASED");

    private final String value;

    DepositStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static DepositStatus fromValue(String value) {
        for (DepositStatus item : values()) {
            if (item.value.equals(value)) {
                return item;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }
}
