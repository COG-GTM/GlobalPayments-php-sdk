package com.globalpayments.api.entities.enums;

public enum TransactionStatus {
    INITIATED("INITIATED"),
    AUTHENTICATED("AUTHENTICATED"),
    PENDING("PENDING"),
    DECLINED("DECLINED"),
    PREAUTHORIZED("PREAUTHORIZED"),
    CAPTURED("CAPTURED"),
    BATCHED("BATCHED"),
    REVERSED("REVERSED"),
    FUNDED("FUNDED"),
    REJECTED("REJECTED");

    private final String value;

    TransactionStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static TransactionStatus fromValue(String value) {
        for (TransactionStatus item : values()) {
            if (item.value.equals(value)) {
                return item;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }
}
