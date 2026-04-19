package com.globalpayments.api.entities.enums;

public enum CustomerDocumentType {
    NATIONAL("NATIONAL"),
    CPF("CPF"),
    CPNJ("CPNJ"),
    CURP("CURP"),
    SSN("SSN"),
    DRIVER_LICENSE("DRIVER_LICENSE"),
    PASSPORT("PASSPORT");

    private final String value;

    CustomerDocumentType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static CustomerDocumentType fromValue(String value) {
        for (CustomerDocumentType item : values()) {
            if (item.value.equals(value)) {
                return item;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }
}
