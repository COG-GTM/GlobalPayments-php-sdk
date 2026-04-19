package com.globalpayments.api.entities.enums;

public enum PayByLinkSortProperty {
    TIME_CREATED("TIME_CREATED");

    private final String value;

    PayByLinkSortProperty(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static PayByLinkSortProperty fromValue(String value) {
        for (PayByLinkSortProperty item : values()) {
            if (item.value.equals(value)) {
                return item;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }
}
