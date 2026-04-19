package com.globalpayments.api.entities.enums;

public enum Target {
    GP_API("GP_API"),
    Portico("Portico"),
    Transit("Transit"),
    Realex("Realex");

    private final String value;

    Target(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Target fromValue(String value) {
        for (Target item : values()) {
            if (item.value.equals(value)) {
                return item;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }
}
