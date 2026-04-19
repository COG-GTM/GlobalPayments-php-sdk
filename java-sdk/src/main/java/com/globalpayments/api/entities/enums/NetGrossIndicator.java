package com.globalpayments.api.entities.enums;

public enum NetGrossIndicator {
    NET("Net"),
    GROSS("Gross");

    private final String value;

    NetGrossIndicator(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static NetGrossIndicator fromValue(String value) {
        for (NetGrossIndicator item : values()) {
            if (item.value.equals(value)) {
                return item;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }
}
