package com.globalpayments.api.entities.enums;

public enum EcommerceIndicator {
    ECOMMERCE_INDICATOR_1("1"),
    ECOMMERCE_INDICATOR_2("2"),
    ECOMMERCE_INDICATOR_3("3"),
    ECOMMERCE_INDICATOR_5("5"),
    ECOMMERCE_INDICATOR_7("7");

    private final String value;

    EcommerceIndicator(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static EcommerceIndicator fromValue(String value) {
        for (EcommerceIndicator item : values()) {
            if (item.value.equals(value)) {
                return item;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }
}
