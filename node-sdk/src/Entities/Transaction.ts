import { TransactionType } from "./Enums/TransactionType";
import { TransactionModifier } from "./Enums/TransactionModifier";
import { PaymentMethodType } from "./Enums/PaymentMethodType";
import { PaymentMethodUsageMode } from "./Enums/PaymentMethodUsageMode";

export class Transaction {
  public authorizedAmount: string | null = null;
  public availableBalance: string | null = null;
  public avsResponseCode: string | null = null;
  public avsResponseMessage: string | null = null;
  public balanceAmount: string | null = null;
  public batchSummary: any = null;
  public cardSecurityResponse: string | null = null;
  public cardType: string | null = null;
  public cardLast4: string | null = null;
  public cavvResponseCode: string | null = null;
  public commercialIndicator: string | null = null;
  public cvnResponseCode: string | null = null;
  public cvnResponseMessage: string | null = null;
  public emvIssuerResponse: string | null = null;
  public hostResponseDate: Date | null = null;
  public multiCapture = false;
  public multiCapturePaymentCount: number | null = null;
  public multiCaptureSequence: number | null = null;
  public originalTransactionType: string | null = null;
  public pointsBalanceAmount: string | null = null;
  public recurringDataCode: string | null = null;
  public referenceNumber: string | null = null;
  public responseCode: string | null = null;
  public responseMessage: string | null = null;
  public splitTenderBalanceDueAmt: any[] = [];
  public responseValues: Record<string, string> | null = null;
  public schemeId: string | null = null;
  public threeDSecure: any = null;
  public timestamp: string | null = null;
  public transactionDescriptor: string | null = null;
  public token: string | null = null;
  public tokenUsageMode: PaymentMethodUsageMode | null = null;
  public transactionReference: any = null;
  public giftCard: any = null;
  public dccRateData: any = null;
  public fraudFilterResponse: any = null;
  public avsAddressResponse: string | null = null;
  public customerReceipt: string | null = null;
  public merchantReceipt: string | null = null;
  public transactionKey: string | null = null;
  public cardBrandTransactionId: string | null = null;
  public payFacData: any = null;
  public cardholderName: string | null = null;
  public cardNumber: string | null = null;
  public maskedCardNumber: string | null = null;
  public cardExpMonth: string | null = null;
  public cardExpYear: string | null = null;
  public accountType: string | null = null;
  public accountNumberLast4: string | null = null;
  public fingerprint: string | null = null;
  public fingerprintIndicator: string | null = null;
  public bankPaymentResponse: any = null;
  public payByLinkResponse: any = null;
  public cardIssuerResponse: any = null;
  public payerDetails: any = null;
  public cardDetails: any = null;
  public address: any = null;
  public customerData: any = null;
  public tokenData: any = null;
  public convenienceFee: number | null = null;
  public installment: any = null;

  get authorizationCode(): string | null {
    return this.transactionReference?.authCode ?? null;
  }

  get clientTransactionId(): string | null {
    return this.transactionReference?.clientTransactionId ?? null;
  }

  get orderId(): string | null {
    return this.transactionReference?.orderId ?? null;
  }

  get paymentMethodType(): PaymentMethodType {
    return this.transactionReference?.paymentMethodType ?? PaymentMethodType.CREDIT;
  }

  get transactionId(): string | null {
    return this.transactionReference?.transactionId ?? null;
  }

  static fromId(
    transactionId: string,
    orderId: string | null = null,
    paymentMethodType: PaymentMethodType | null = null,
  ): Transaction {
    if (orderId === null && paymentMethodType === null) {
      paymentMethodType = PaymentMethodType.CREDIT;
    }

    const txn = new Transaction();
    txn.transactionReference = {
      transactionId,
      paymentMethodType,
      orderId,
    };
    return txn;
  }

  static fromClientTransactionId(
    clientTransactionId: string,
    orderId: string | null = null,
    paymentMethodType: PaymentMethodType | null = null,
  ): Transaction {
    if (orderId === null && paymentMethodType === null) {
      paymentMethodType = PaymentMethodType.CREDIT;
    }

    const txn = new Transaction();
    txn.transactionReference = {
      clientTransactionId,
      paymentMethodType,
      orderId,
    };
    return txn;
  }
}
