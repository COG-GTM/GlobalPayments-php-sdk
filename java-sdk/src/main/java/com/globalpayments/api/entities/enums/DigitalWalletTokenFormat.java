package com.globalpayments.api.entities.enums;

public enum DigitalWalletTokenFormat {
    CARD_NUMBER("CARD_NUMBER"),
    CARD_TOKEN("CARD_TOKEN");

    private final String value;

    DigitalWalletTokenFormat(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static DigitalWalletTokenFormat fromValue(String value) {
        for (DigitalWalletTokenFormat item : values()) {
            if (item.value.equals(value)) {
                return item;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }
}
