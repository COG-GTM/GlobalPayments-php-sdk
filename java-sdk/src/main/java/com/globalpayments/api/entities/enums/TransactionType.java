package com.globalpayments.api.entities.enums;

public class TransactionType {

    public static final long DECLINE = 1L;
    public static final long VERIFY = 2L;
    public static final long CAPTURE = 4L;
    public static final long AUTH = 8L;
    public static final long REFUND = 16L;
    public static final long REVERSAL = 32L;
    public static final long SALE = 64L;
    public static final long EDIT = 128L;
    public static final long VOID = 256L;
    public static final long ADD_VALUE = 512L;
    public static final long BALANCE = 1024L;
    public static final long ACTIVATE = 2048L;
    public static final long ALIAS = 4096L;
    public static final long REPLACE = 8192L;
    public static final long REWARD = 16384L;
    public static final long DEACTIVATE = 32768L;
    public static final long BATCH_CLOSE = 65536L;
    public static final long CREATE = 131072L;
    public static final long DELETE = 262144L;
    public static final long FETCH = 524288L;
    public static final long SEARCH = 1048576L;
    public static final long HOLD = 2097152L;
    public static final long RELEASE = 4194304L;
    public static final long DCC_RATE_LOOKUP = 8388608L;
    public static final long VERIFY_ENROLLED = 16777216L;
    public static final long VERIFY_SIGNATURE = 33554432L;
    public static final long TOKEN_DELETE = 67108864L;
    public static final long RISK_ASSESS = 134217728L;
    public static final long INITIATE_AUTHENTICATION = 268435456L;
    public static final long DATA_COLLECT = 536870912L;
    public static final long PRE_AUTH_COMPLETION = 1073741824L;
    public static final long TOKEN_UPDATE = 2147483648L;
    public static final long BENEFIT_WITHDRAWAL = 4294967296L;
    public static final long TOKENIZE = 8589934592L;
    public static final long CREATE_ACCOUNT = 17179869184L;
    public static final long RESET_PASSWORD = 34359738368L;
    public static final long RENEW_ACCOUNT = 68719476736L;
    public static final long UPDATE_OWNERSHIP_DETAILS = 137438953472L;
    public static final long UPLOAD_CHARGEBACK_DOCUMENT = 274877906944L;
    public static final long UPLOAD_DOCUMENT = 549755813888L;
    public static final long OBTAIN_SSO_KEY = 1099511627776L;
    public static final long UPDATE_BANK_ACCOUNT_OWNERSHIP = 2199023255552L;
    public static final long ADD_FUNDS = 4398046511104L;
    public static final long SWEEP_FUNDS = 8796093022208L;
    public static final long ADD_CARD_FLASH_FUNDS = 17592186044416L;
    public static final long PUSH_MONEY_FLASH_FUNDS = 35184372088832L;
    public static final long DISBURSE_FUNDS = 70368744177664L;
    public static final long SPEND_BACK = 140737488355328L;
    public static final long REVERSE_SPLITPAY = 281474976710656L;
    public static final long SPLIT_FUNDS = 562949953421312L;
    public static final long GET_ACCOUNT_DETAILS = 1125899906842624L;
    public static final long GET_ACCOUNT_BALANCE = 2251799813685248L;
    public static final long DETOKENIZE = 4503599627370496L;
    public static final long DISPUTE_ACCEPTANCE = 9007199254740992L;
    public static final long DISPUTE_CHALLENGE = 18014398509481984L;
    public static final long REAUTH = 36028797018963968L;
    public static final long CONFIRM = 72057594037927936L;
    public static final long PAYBYLINK_UPDATE = 144115188075855872L;
    public static final long TRANSFER_FUNDS = 288230376151711744L;
    public static final long DEVICE_ORDER = 576460752303423488L;
    public static final long GET_TOKEN_INFO = 1152921504606846976L; // 1 << 60
    public static final long HOSTED_PAYMENT_PAGE = 2305843009213693952L; // 1 << 61

    private TransactionType() {
    }
}
