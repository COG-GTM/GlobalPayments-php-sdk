package com.globalpayments.api.entities.enums;

public enum BankList {
    PKOBANKPOLSKISA("pkobankpolskisa"),
    SANTANDER("santander"),
    ING("ing"),
    BANKPEKAOSA("bankpekaosa"),
    MBANK("mbank"),
    ALIOR("alior"),
    BNPPARIBAS("bnpparibas"),
    MILLENIUM("millenium"),
    CREDITAGRICOLE("creditagricole"),
    CITI("citi"),
    INTELIGO("inteligo"),
    BANKISPOLDZIELCZE("bankispoldzielcze"),
    BOSBANK("bosbank"),
    NESTBANK("nestbank"),
    VELOBANK("velobank"),
    BANKNOWYSA("banknowysa"),
    PLUSBANK("plusbank"),
    BANKPOCZTOWY("bankpocztowy");

    private final String value;

    BankList(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static BankList fromValue(String value) {
        for (BankList item : values()) {
            if (item.value.equals(value)) {
                return item;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }
}
