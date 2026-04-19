package com.globalpayments.api.entities.enums;

public enum DisputeSortProperty {
    ID("id"),
    ARN("arn"),
    BRAND("brand"),
    STATUS("status"),
    STAGE("stage"),
    FROM_STAGE_TIME_CREATED("from_stage_time_created"),
    TO_STAGE_TIME_CREATED("to_stage_time_created"),
    ADJUSTMENT_FUNDING("adjustment_funding"),
    FROM_ADJUSTMENT_TIME_CREATED("from_adjustment_time_created"),
    TO_ADJUSTMENT_TIME_CREATED("to_adjustment_time_created");

    private final String value;

    DisputeSortProperty(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static DisputeSortProperty fromValue(String value) {
        for (DisputeSortProperty item : values()) {
            if (item.value.equals(value)) {
                return item;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }
}
