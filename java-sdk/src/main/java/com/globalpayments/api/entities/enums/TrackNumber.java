package com.globalpayments.api.entities.enums;

public enum TrackNumber {
    UNKNOWN("Unknown"),
    TRACK_ONE("TrackOne"),
    TRACK_TWO("TrackTwo"),
    BOTH_ONE_AND_TWO("BothOneAndTwo");

    private final String value;

    TrackNumber(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static TrackNumber fromValue(String value) {
        for (TrackNumber item : values()) {
            if (item.value.equals(value)) {
                return item;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }
}
