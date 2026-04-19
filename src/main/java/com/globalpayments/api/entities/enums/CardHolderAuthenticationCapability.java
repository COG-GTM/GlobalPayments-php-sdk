package com.globalpayments.api.entities.enums;

public enum CardHolderAuthenticationCapability {
    NO_CAPABILITY("NO_CAPABILITY"),
    PIN_ENTRY("PIN_ENTRY"),
    SIGNATURE_ANALYSIS("SIGNATURE_ANALYSIS"),
    SIGNATURE_ANALYSIS_INOPERATIVE("SIGNATURE_ANALYSIS_INOPERATIVE"),
    MPOS_SOFTWARE_BASED_PIN_ENTRY_CAPABILITY("MPOS_SOFTWARE_BASED_PIN_ENTRY_CAPABILITY"),
    OTHER("OTHER"),
    UNKNOWN("UNKNOWN");

    private final String value;

    CardHolderAuthenticationCapability(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static CardHolderAuthenticationCapability fromValue(String value) {
        for (CardHolderAuthenticationCapability item : values()) {
            if (item.value.equals(value)) {
                return item;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }
}
