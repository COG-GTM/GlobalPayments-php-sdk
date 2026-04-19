package com.globalpayments.api.entities.enums;

public enum RemittanceReferenceType {
    TEXT("TEXT"),
    PAN("PAN");

    private final String value;

    RemittanceReferenceType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static RemittanceReferenceType fromValue(String value) {
        for (RemittanceReferenceType item : values()) {
            if (item.value.equals(value)) {
                return item;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }
}
