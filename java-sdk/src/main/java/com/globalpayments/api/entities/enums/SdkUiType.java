package com.globalpayments.api.entities.enums;

public enum SdkUiType {
    TEXT("TEXT"),
    SINGLE_SELECT("SINGLE_SELECT"),
    MULTI_SELECT("MULTI_SELECT"),
    OOB("OOB"),
    HTML_OTHER("HTML_OTHER");

    private final String value;

    SdkUiType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static SdkUiType fromValue(String value) {
        for (SdkUiType item : values()) {
            if (item.value.equals(value)) {
                return item;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }
}
