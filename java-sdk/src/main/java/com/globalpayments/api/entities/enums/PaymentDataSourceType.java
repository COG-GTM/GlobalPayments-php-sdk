package com.globalpayments.api.entities.enums;

public enum PaymentDataSourceType {
    AMEX_3DSECURE("AMEX 3DSecure"),
    APPLEPAY("ApplePay"),
    APPLEPAYAPP("ApplePayApp"),
    APPLEPAYWEB("ApplePayWeb"),
    GOOGLEPAYAPP("GooglePayApp"),
    GOOGLEPAYWEB("GooglePayWeb"),
    DISCOVER_3DSECURE("Discover 3DSecure"),
    MASTERCARD_3DSECURE("MasterCard 3DSecure"),
    VISA_3DSECURE("Visa 3DSecure"),
    UPEXPRESS_3DSECURE("UPExpress 3DSecure"),
    UPSECUREPLUS_3DSECURE("UPSecurePlus 3DSecure");

    private final String value;

    PaymentDataSourceType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static PaymentDataSourceType fromValue(String value) {
        for (PaymentDataSourceType item : values()) {
            if (item.value.equals(value)) {
                return item;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }
}
