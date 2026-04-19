package com.globalpayments.api.entities.enums;

public enum EbtCardType {
    CASH_BENEFIT("CashBenefit"),
    FOOD_STAMP("FoodStamp");

    private final String value;

    EbtCardType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static EbtCardType fromValue(String value) {
        for (EbtCardType item : values()) {
            if (item.value.equals(value)) {
                return item;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }
}
