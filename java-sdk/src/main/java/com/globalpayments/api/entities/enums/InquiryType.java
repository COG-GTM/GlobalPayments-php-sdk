package com.globalpayments.api.entities.enums;

public enum InquiryType {
    STANDARD("STANDARD"),
    FOODSTAMP("FOODSTAMP"),
    CASH("CASH"),
    POINTS("POINTS");

    private final String value;

    InquiryType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static InquiryType fromValue(String value) {
        for (InquiryType item : values()) {
            if (item.value.equals(value)) {
                return item;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }
}
