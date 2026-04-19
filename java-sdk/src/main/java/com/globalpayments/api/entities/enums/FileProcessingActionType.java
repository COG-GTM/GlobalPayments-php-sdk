package com.globalpayments.api.entities.enums;

public enum FileProcessingActionType {
    CREATE_UPLOAD_URL(1),
    GET_DETAILS(2);

    private final int value;

    FileProcessingActionType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static FileProcessingActionType fromValue(int value) {
        for (FileProcessingActionType item : values()) {
            if (item.value == value) {
                return item;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }
}
