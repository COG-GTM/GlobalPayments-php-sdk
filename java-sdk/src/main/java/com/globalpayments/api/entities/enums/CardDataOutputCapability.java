package com.globalpayments.api.entities.enums;

public enum CardDataOutputCapability {
    NONE("NONE"),
    MAGNETIC_STRIPE_WRITE("MAGNETIC_STRIPE_WRITE"),
    ICC("ICC"),
    OTHER("OTHER");

    private final String value;

    CardDataOutputCapability(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static CardDataOutputCapability fromValue(String value) {
        for (CardDataOutputCapability item : values()) {
            if (item.value.equals(value)) {
                return item;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }
}
