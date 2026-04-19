package com.globalpayments.api.entities.enums;

public class TransactionModifier {

    public static final long NONE = 0L;
    public static final long INCREMENTAL = 1L;
    public static final long ADDITIONAL = 2L;
    public static final long OFFLINE = 3L;
    public static final long LEVEL_II = 4L;
    public static final long FRAUD_DECLINE = 5L;
    public static final long CHIP_DECLINE = 6L;
    public static final long CASH_BACK = 7L;
    public static final long VOUCHER = 8L;
    public static final long RECURRING = 9L;
    public static final long HOSTEDREQUEST = 10L;
    public static final long ENCRYPTED_MOBILE = 11L;
    public static final long SECURE3D = 12L;
    public static final long ALTERNATIVE_PAYMENT_METHOD = 12L;
    public static final long LEVEL_III = 13L;
    public static final long DECRYPTED_MOBILE = 14L;
    public static final long BANK_PAYMENT = 15L;
    public static final long MERCHANT = 16L;
    public static final long BAY_NOW_PAY_LATER = 17L;
    public static final long DELETE_PRE_AUTH = 18L;
    public static final long UPDATE_TAX_DETAILS = 19L;
    public static final long UPDATE_LODGING_DETAILS = 20L;
    public static final long START_TRANSACTION = 21L;
    public static final long CONTINUE_EMV_TRANSACTION = 22L;
    public static final long COMPLETE_TRANSACTION = 23L;
    public static final long PROCESS_TRANSACTION = 24L;
    public static final long CONTINUE_CARD_TRANSACTION = 25L;

    private TransactionModifier() {
    }
}
