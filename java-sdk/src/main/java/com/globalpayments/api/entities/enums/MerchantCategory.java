package com.globalpayments.api.entities.enums;

public enum MerchantCategory {
    HOTEL("HOTEL"),
    AIRLINE("AIRLINE"),
    RETAIL("RETAIL"),
    TOP_UP("TOP_UP"),
    PLAYER("PLAYER"),
    CD_KEY("CD_KEY"),
    OTHER("OTHER");

    private final String value;

    MerchantCategory(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static MerchantCategory fromValue(String value) {
        for (MerchantCategory item : values()) {
            if (item.value.equals(value)) {
                return item;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }
}
