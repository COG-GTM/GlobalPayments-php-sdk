package com.globalpayments.api.entities.enums;

public enum OperatingEnvironment {
    NO_TERMINAL("NO_TERMINAL"),
    ON_MERCHANT_PREMISES_ATTENDED("ON_MERCHANT_PREMISES_ATTENDED"),
    ON_MERCHANT_PREMISES_UNATTENDED("ON_MERCHANT_PREMISES_UNATTENDED"),
    OFF_MERCHANT_PREMISES_ATTENDED("OFF_MERCHANT_PREMISES_ATTENDED"),
    OFF_MERCHANT_PREMISES_UNATTENDED("OFF_MERCHANT_PREMISES_UNATTENDED"),
    ON_CUSTOMER_PREMISES_UNATTENDED("ON_CUSTOMER_PREMISES_UNATTENDED"),
    OFF_MERCHANT_PREMISES_MPOS("OFF_MERCHANT_PREMISES_MPOS"),
    ON_MERCHANT_PREMISES_MPOS("ON_MERCHANT_PREMISES_MPOS"),
    OFF_MERCHANT_PREMISES_CUSTOMER_POS("OFF_MERCHANT_PREMISES_CUSTOMER_POS"),
    ON_MERCHANT_PREMISES_CUSTOMER_POS("ON_MERCHANT_PREMISES_CUSTOMER_POS"),
    OFF_CUSTOMER_PREMISES_UNATTENDED("OFF_CUSTOMER_PREMISES_UNATTENDED"),
    UNKNOWN("UNKNOWN"),
    ELECTRONIC_DELIVERY_AMEX("ELECTRONIC_DELIVERY_AMEX"),
    PHYSICAL_DELIVERY_AMEX("PHYSICAL_DELIVERY_AMEX");

    private final String value;

    OperatingEnvironment(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static OperatingEnvironment fromValue(String value) {
        for (OperatingEnvironment item : values()) {
            if (item.value.equals(value)) {
                return item;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }
}
