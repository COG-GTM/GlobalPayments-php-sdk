package com.globalpayments.api.entities.enums;

public enum Region {
    US("US"),
    CA("CA"),
    AU("AU"),
    NZ("NZ"),
    UK("UK"),
    EU("EU"),
    MX("MX"),
    CL("CL");

    private final String value;

    Region(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Region fromValue(String value) {
        for (Region item : values()) {
            if (item.value.equals(value)) {
                return item;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }
}
