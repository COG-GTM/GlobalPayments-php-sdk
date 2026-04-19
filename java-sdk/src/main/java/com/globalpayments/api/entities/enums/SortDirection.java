package com.globalpayments.api.entities.enums;

public enum SortDirection {
    ASC("ASC"),
    DESC("DESC");

    private final String value;

    SortDirection(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static SortDirection fromValue(String value) {
        for (SortDirection item : values()) {
            if (item.value.equals(value)) {
                return item;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }
}
