package com.globalpayments.api.entities.enums;

public class ReportType {

    public static final long FIND_TRANSACTIONS = 1L;
    public static final long ACTIVITY = 2L;
    public static final long FIND_BANK_PAYMENT = 4L;
    public static final long DOCUMENT_DISPUTE_DETAIL = 8L;
    public static final long TRANSACTION_DETAIL = 128L;
    public static final long FIND_DEPOSITS = 256L;
    public static final long FIND_DISPUTES = 512L;
    public static final long FIND_SETTLEMENT_DISPUTES = 1024L;
    public static final long DEPOSIT_DETAIL = 2048L;
    public static final long DISPUTE_DETAIL = 4096L;
    public static final long SETTLEMENT_DISPUTE_DETAIL = 8192L;
    public static final long FIND_SETTLEMENT_TRANSACTIONS = 16384L;
    public static final long FIND_TRANSACTIONS_PAGED = 32768L;
    public static final long FIND_SETTLEMENT_TRANSACTIONS_PAGED = 65536L;
    public static final long FIND_DEPOSITS_PAGED = 131072L;
    public static final long FIND_DISPUTES_PAGED = 262144L;
    public static final long FIND_SETTLEMENT_DISPUTES_PAGED = 524288L;
    public static final long FIND_STORED_PAYMENT_METHODS_PAGED = 1048576L;
    public static final long STORED_PAYMENT_METHOD_DETAIL = 2097152L;
    public static final long FIND_ACTIONS_PAGED = 4194304L;
    public static final long ACTION_DETAIL = 8388608L;
    public static final long PAYBYLINK_DETAIL = 16777216L;
    public static final long FIND_PAYBYLINK_PAGED = 33554432L;
    public static final long FIND_MERCHANTS_PAGED = 67108864L;
    public static final long FIND_ACCOUNTS_PAGED = 134217728L;
    public static final long FIND_ACCOUNT_DETAIL = 268435456L;
    public static final long BATCH_DETAIL = 536870912L;
    public static final long OPEN_AUTH = 1073741824L;

    private ReportType() {
    }
}
