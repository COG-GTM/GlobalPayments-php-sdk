package com.globalpayments.api.entities.enums;

public enum CardDataSource {
    SWIPE("SWIPE"),
    NFC("NFC"),
    EMV("EMV"),
    EMV_CONTACTLESS("EMV_CONTACTLESS"),
    FALLBACK_SWIPE("FALLBACK_SWIPE"),
    BAR_CODE("BAR_CODE"),
    MANUAL("MANUAL"),
    PHONE("PHONE"),
    MAIL("MAIL"),
    INTERNET("INTERNET");

    private final String value;

    CardDataSource(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static CardDataSource fromValue(String value) {
        for (CardDataSource item : values()) {
            if (item.value.equals(value)) {
                return item;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }
}
