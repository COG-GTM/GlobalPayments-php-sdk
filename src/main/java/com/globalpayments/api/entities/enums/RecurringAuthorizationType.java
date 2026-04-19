package com.globalpayments.api.entities.enums;

public enum RecurringAuthorizationType {
    UNASSIGNED("Unassigned"),
    SIGNED_CONTRACT_INPLACE("SignedContractInPlace"),
    NEED_TO_PRINT_CONTRACT("NeedToPrintContract"),
    RECORDED_CALL_INPLACE("RecordedCallInPlace");

    private final String value;

    RecurringAuthorizationType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static RecurringAuthorizationType fromValue(String value) {
        for (RecurringAuthorizationType item : values()) {
            if (item.value.equals(value)) {
                return item;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }
}
