package com.globalpayments.api.entities.enums;

public enum PaymentEntryMode {
    MOTO("MOTO"),
    ECOM("ECOM"),
    IN_APP("IN_APP"),
    CHIP("CHIP"),
    SWIPE("SWIPE"),
    MANUAL("MANUAL"),
    CONTACTLESS_CHIP("CONTACTLESS_CHIP"),
    CONTACTLESS_SWIPE("CONTACTLESS_SWIPE"),
    PHONE("PHONE"),
    MAIL("MAIL");

    private final String value;

    PaymentEntryMode(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static PaymentEntryMode fromValue(String value) {
        for (PaymentEntryMode item : values()) {
            if (item.value.equals(value)) {
                return item;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }
}
