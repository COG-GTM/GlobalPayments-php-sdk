package com.globalpayments.api.entities.enums;

public enum CardDataInputCapability {
    UNKNOWN("UNKNOWN"),
    NO_TERMINAL_MANUAL("NO_TERMINAL_MANUAL"),
    MAGSTRIPE_READ_ONLY("MAGSTRIPE_READ_ONLY"),
    OCR("OCR"),
    ICC_CHIP_READ_ONLY("ICC_CHIP_READ_ONLY"),
    KEYED_ENTRY_ONLY("KEYED_ENTRY_ONLY"),
    MAGSTRIPE_CONTACTLESS_ONLY("MAGSTRIPE_CONTACTLESS_ONLY"),
    MAGSTRIPE_KEYED_ENTRY_ONLY("MAGSTRIPE_KEYED_ENTRY_ONLY"),
    MAGSTRIPE_ICC_KEYED_ENTRY_ONLY("MAGSTRIPE_ICC_KEYED_ENTRY_ONLY"),
    MAGSTRIPE_ICC_ONLY("MAGSTRIPE_ICC_ONLY"),
    ICC_KEYED_ENTRY_ONLY("ICC_KEYED_ENTRY_ONLY"),
    ICC_CHIP_CONTACT_CONTACTLESS("ICC_CHIP_CONTACT_CONTACTLESS"),
    ICC_CONTACTLESS_ONLY("ICC_CONTACTLESS_ONLY"),
    OTHER_CAPABILITY_FOR_MASTERCARD("OTHER_CAPABILITY_FOR_MASTERCARD"),
    MAGSTRIPE_SIGNATURE_FOR_AMEX_ONLY("MAGSTRIPE_SIGNATURE_FOR_AMEX_ONLY");

    private final String value;

    CardDataInputCapability(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static CardDataInputCapability fromValue(String value) {
        for (CardDataInputCapability item : values()) {
            if (item.value.equals(value)) {
                return item;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }
}
