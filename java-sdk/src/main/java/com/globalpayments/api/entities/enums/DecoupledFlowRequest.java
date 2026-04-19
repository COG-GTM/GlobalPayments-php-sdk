package com.globalpayments.api.entities.enums;

public enum DecoupledFlowRequest {
    DECOUPLED_PREFERRED("DECOUPLED_PREFERRED"),
    DO_NOT_USE_DECOUPLED("DO_NOT_USE_DECOUPLED");

    private final String value;

    DecoupledFlowRequest(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static DecoupledFlowRequest fromValue(String value) {
        for (DecoupledFlowRequest item : values()) {
            if (item.value.equals(value)) {
                return item;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }
}
