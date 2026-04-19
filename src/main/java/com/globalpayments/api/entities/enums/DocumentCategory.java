package com.globalpayments.api.entities.enums;

public enum DocumentCategory {
    IDENTITY_VERIFICATION("IDENTITY_VERIFICATION"),
    RISK_REVIEW("RISK_REVIEW"),
    UNDERWRITING("UNDERWRITING");

    private final String value;

    DocumentCategory(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static DocumentCategory fromValue(String value) {
        for (DocumentCategory item : values()) {
            if (item.value.equals(value)) {
                return item;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }
}
