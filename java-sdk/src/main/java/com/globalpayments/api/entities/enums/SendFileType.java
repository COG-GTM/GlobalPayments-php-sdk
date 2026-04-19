package com.globalpayments.api.entities.enums;

public enum SendFileType {
    IDLELOGO("IDLELOGO.JPG"),
    BANNER("BANNER.JPG");

    private final String value;

    SendFileType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static SendFileType fromValue(String value) {
        for (SendFileType item : values()) {
            if (item.value.equals(value)) {
                return item;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }
}
