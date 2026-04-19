package com.globalpayments.api.entities.enums;

public enum TaxType {
    NOT_USED("NOTUSED"),
    SALES_TAX("SALESTAX"),
    TAX_EXEMPT("TAXEXEMPT");

    private final String value;

    TaxType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static TaxType fromValue(String value) {
        for (TaxType item : values()) {
            if (item.value.equals(value)) {
                return item;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }
}
