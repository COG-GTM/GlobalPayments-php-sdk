package com.globalpayments.api.entities.enums;

public enum ChallengeWindowSize {
    WINDOWED_250X400("WINDOWED_250X400"),
    WINDOWED_390X400("WINDOWED_390X400"),
    WINDOWED_500X600("WINDOWED_500X600"),
    WINDOWED_600X400("WINDOWED_600X400"),
    FULL_SCREEN("FULL_SCREEN");

    private final String value;

    ChallengeWindowSize(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static ChallengeWindowSize fromValue(String value) {
        for (ChallengeWindowSize item : values()) {
            if (item.value.equals(value)) {
                return item;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }
}
