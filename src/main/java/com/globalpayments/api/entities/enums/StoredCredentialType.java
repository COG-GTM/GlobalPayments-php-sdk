package com.globalpayments.api.entities.enums;

public enum StoredCredentialType {
    ONEOFF("oneoff"),
    INSTALLMENT("installment"),
    RECURRING("recurring"),
    UNSCHEDULED("UNSCHEDULED"),
    SUBSCRIPTION("SUBSCRIPTION"),
    MAINTAIN_PAYMENT_METHOD("MAINTAIN_PAYMENT_METHOD"),
    MAINTAIN_PAYMENT_VERIFICATION("MAINTAIN_PAYMENT_VERIFICATION"),
    ADD_PAYMENT_METHOD("ADD_PAYMENT_METHOD"),
    SPLIT_OR_DELAYED_SHIPMENT("SPLIT_OR_DELAYED_SHIPMENT"),
    TOP_UP("TOP_UP"),
    MAIL_ORDER("MAIL_ORDER"),
    TELEPHONE_ORDER("TELEPHONE_ORDER"),
    WHITELIST_STATUS_CHECK("WHITELIST_STATUS_CHECK"),
    OTHER_PAYMENT("OTHER_PAYMENT"),
    BILLING_AGREEMENT("BILLING_AGREEMENT");

    private final String value;

    StoredCredentialType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static StoredCredentialType fromValue(String value) {
        for (StoredCredentialType item : values()) {
            if (item.value.equals(value)) {
                return item;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }
}
