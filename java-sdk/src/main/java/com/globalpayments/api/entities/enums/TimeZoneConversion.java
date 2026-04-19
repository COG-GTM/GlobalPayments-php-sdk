package com.globalpayments.api.entities.enums;

public enum TimeZoneConversion {
    UTC("UTC"),
    MERCHANT("Merchant"),
    DATACENTER("Datacenter");

    private final String value;

    TimeZoneConversion(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static TimeZoneConversion fromValue(String value) {
        for (TimeZoneConversion item : values()) {
            if (item.value.equals(value)) {
                return item;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }
}
