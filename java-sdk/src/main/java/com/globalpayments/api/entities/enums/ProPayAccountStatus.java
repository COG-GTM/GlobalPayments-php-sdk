package com.globalpayments.api.entities.enums;

public enum ProPayAccountStatus {
    READY_TO_PROCESS("ReadyToProcess"),
    FRAUD_ACCOUNT("FraudAccount"),
    RISK_WISE_DECLINED("RiskwiseDeclined"),
    HOLD("Hold"),
    CANCELED("Canceled"),
    FRAUD_VICTIM("FraudVictim"),
    CLOSED_EULA("ClosedEULA"),
    CLOSED_EXCESSIVE_CHARGEBACK("ClosedExcessiveChargeback");

    private final String value;

    ProPayAccountStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static ProPayAccountStatus fromValue(String value) {
        for (ProPayAccountStatus item : values()) {
            if (item.value.equals(value)) {
                return item;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }
}
