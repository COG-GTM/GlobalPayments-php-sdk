package com.globalpayments.api.entities.enums;

public enum MerchantInitiatedRequestType {
    RECURRING_TRANSACTION("RECURRING_TRANSACTION"),
    INSTALLMENT_TRANSACTION("INSTALLMENT_TRANSACTION"),
    ADD_CARD("ADD_CARD"),
    MAINTAIN_CARD_INFORMATION("MAINTAIN_CARD_INFORMATION"),
    ACCOUNT_VERIFICATION("ACCOUNT_VERIFICATION"),
    SPLIT_OR_DELAYED_SHIPMENT("SPLIT_OR_DELAYED_SHIPMENT"),
    TOP_UP("TOP_UP"),
    MAIL_ORDER("MAIL_ORDER"),
    TELEPHONE_ORDER("TELEPHONE_ORDER"),
    WHITELIST_STATUS_CHECK("WHITELIST_STATUS_CHECK"),
    OTHER_PAYMENT("OTHER_PAYMENT"),
    BILLING_AGREEMENT("BILLING_AGREEMENT");

    private final String value;

    MerchantInitiatedRequestType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static MerchantInitiatedRequestType fromValue(String value) {
        for (MerchantInitiatedRequestType item : values()) {
            if (item.value.equals(value)) {
                return item;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }
}
