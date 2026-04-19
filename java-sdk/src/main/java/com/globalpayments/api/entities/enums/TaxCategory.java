package com.globalpayments.api.entities.enums;

public enum TaxCategory {
    SERVICE("SERVICE"),
    DUTY("DUTY"),
    VAT("VAT"),
    ALTERNATE("ALTERNATE"),
    NATIONAL("NATIONAL"),
    TAX_EXEMPT("TAX_EXEMPT");

    private final String value;

    TaxCategory(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static TaxCategory fromValue(String value) {
        for (TaxCategory item : values()) {
            if (item.value.equals(value)) {
                return item;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }
}
