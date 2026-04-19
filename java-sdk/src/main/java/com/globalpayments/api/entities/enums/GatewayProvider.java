package com.globalpayments.api.entities.enums;

public enum GatewayProvider {
    PORTICO("PORTICO"),
    GP_ECOM("GP_ECOM"),
    GENIUS("GENIUS"),
    TRANSIT("TRANSIT"),
    GP_API("GP-API"),
    TRANSACTION_API("TRANSACTION-API"),
    UPA("UPA");

    private final String value;

    GatewayProvider(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static GatewayProvider fromValue(String value) {
        for (GatewayProvider item : values()) {
            if (item.value.equals(value)) {
                return item;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }
}
