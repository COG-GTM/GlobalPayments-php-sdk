package com.globalpayments.api.entities.enums;

public enum PinCaptureCapability {
    FOUR_CHARACTERS("4"),
    FIVE_CHARACTERS("5"),
    SIX_CHARACTERS("6"),
    SEVEN_CHARACTERS("7"),
    EIGHT_CHARACTERS("8"),
    NINE_CHARACTERS("9"),
    TEN_CHARACTERS("10"),
    ELEVEN_CHARACTERS("11"),
    TWELVE_CHARACTERS("12"),
    UNKNOWN("UNKNOWN"),
    NONE("NOT_SUPPORTED");

    private final String value;

    PinCaptureCapability(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static PinCaptureCapability fromValue(String value) {
        for (PinCaptureCapability item : values()) {
            if (item.value.equals(value)) {
                return item;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }
}
