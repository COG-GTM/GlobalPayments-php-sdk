package com.globalpayments.api.entities.enums;

public enum SecCode {
    PPD("PPD"),
    CCD("CCD"),
    POP("POP"),
    WEB("WEB"),
    TEL("TEL"),
    EBRONZE("EBRONZE");

    private final String value;

    SecCode(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static SecCode fromValue(String value) {
        for (SecCode item : values()) {
            if (item.value.equals(value)) {
                return item;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }
}
