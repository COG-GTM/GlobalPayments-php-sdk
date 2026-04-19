package com.globalpayments.api.entities.enums;

public enum ColorDepth {
    ONE_BIT("ONE_BIT"),
    TWO_BITS("TWO_BITS"),
    FOUR_BITS("FOUR_BITS"),
    EIGHT_BITS("EIGHT_BITS"),
    FIFTEEN_BITS("FIFTEEN_BITS"),
    SIXTEEN_BITS("SIXTEEN_BITS"),
    TWENTY_FOUR_BITS("TWENTY_FOUR_BITS"),
    THIRTY_TWO_BITS("THIRTY_TWO_BITS"),
    FORTY_EIGHT_BITS("FORTY_EIGHT_BITS");

    private final String value;

    ColorDepth(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static ColorDepth fromValue(String value) {
        for (ColorDepth item : values()) {
            if (item.value.equals(value)) {
                return item;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }
}
