package com.globalpayments.api.entities.enums;

public enum HppVersion {
    VERSION_1("1"),
    VERSION_2("2");

    private final String value;

    HppVersion(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static HppVersion fromValue(String value) {
        for (HppVersion item : values()) {
            if (item.value.equals(value)) {
                return item;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }
}
