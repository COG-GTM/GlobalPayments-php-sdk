package com.globalpayments.api.entities.enums;

public enum AliasAction {
    CREATE("CREATE"),
    ADD("ADD"),
    DELETE("DELETE");

    private final String value;

    AliasAction(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static AliasAction fromValue(String value) {
        for (AliasAction item : values()) {
            if (item.value.equals(value)) {
                return item;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }
}
