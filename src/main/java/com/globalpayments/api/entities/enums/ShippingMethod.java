package com.globalpayments.api.entities.enums;

public enum ShippingMethod {
    BILLING_ADDRESS("BILLING_ADDRESS"),
    VERIFIED_ADDRESS("ANOTHER_VERIFIED_ADDRESS"),
    UNVERIFIED_ADDRESS("UNVERIFIED_ADDRESS"),
    SHIP_TO_STORE("SHIP_TO_STORE"),
    DIGITAL_GOODS("DIGITAL_GOODS"),
    TRAVEL_AND_EVENT_TICKETS("TRAVEL_AND_EVENT_TICKETS"),
    OTHER("OTHER");

    private final String value;

    ShippingMethod(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static ShippingMethod fromValue(String value) {
        for (ShippingMethod item : values()) {
            if (item.value.equals(value)) {
                return item;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }
}
