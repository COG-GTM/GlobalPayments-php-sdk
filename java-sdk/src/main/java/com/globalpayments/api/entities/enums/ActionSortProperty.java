package com.globalpayments.api.entities.enums;

public enum ActionSortProperty {
    TIME_CREATED("TIME_CREATED");

    private final String value;

    ActionSortProperty(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static ActionSortProperty fromValue(String value) {
        for (ActionSortProperty item : values()) {
            if (item.value.equals(value)) {
                return item;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }
}
