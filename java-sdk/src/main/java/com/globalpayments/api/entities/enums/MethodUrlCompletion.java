package com.globalpayments.api.entities.enums;

public enum MethodUrlCompletion {
    YES("YES"),
    NO("NO"),
    UNAVAILABLE("UNAVAILABLE");

    private final String value;

    MethodUrlCompletion(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static MethodUrlCompletion fromValue(String value) {
        for (MethodUrlCompletion item : values()) {
            if (item.value.equals(value)) {
                return item;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }
}
