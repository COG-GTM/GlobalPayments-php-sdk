package com.globalpayments.api.entities.enums;

public enum UserLevelRelationship {
    SELF("SELF"),
    ACCOUNTS("ACCOUNTS");

    private final String value;

    UserLevelRelationship(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static UserLevelRelationship fromValue(String value) {
        for (UserLevelRelationship item : values()) {
            if (item.value.equals(value)) {
                return item;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }
}
