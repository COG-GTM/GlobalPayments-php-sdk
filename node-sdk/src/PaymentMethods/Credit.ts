import { PaymentMethodType } from "../Entities/Enums/PaymentMethodType";
import { TransactionType } from "../Entities/Enums/TransactionType";
import { IPaymentMethod } from "./Interfaces/IPaymentMethod";

export abstract class Credit implements IPaymentMethod {
  public bankName: string | null = null;
  public cardType: string | null = null;
  public cryptogram: string | null = null;
  public eci: string | null = null;
  public encryptionData: any = null;
  public entryMethod: any = null;
  public isFleet = false;
  public mobileType: string | null = null;
  public paymentMethodType: PaymentMethodType = PaymentMethodType.CREDIT;
  public paymentSource: any = null;
  public threeDSecure: any = null;
  public token: string | null = null;

  authorize(amount?: number | string | null, _isEstimated = false): any {
    return {
      transactionType: TransactionType.AUTH,
      paymentMethod: this,
      amount: amount ?? this.threeDSecure?.getAmount?.() ?? null,
      currency: this.threeDSecure?.getCurrency?.() ?? null,
      orderId: this.threeDSecure?.getOrderId?.() ?? null,
    };
  }

  charge(amount?: number | string | null): any {
    return {
      transactionType: TransactionType.SALE,
      paymentMethod: this,
      amount: amount ?? this.threeDSecure?.getAmount?.() ?? null,
      currency: this.threeDSecure?.getCurrency?.() ?? null,
      orderId: this.threeDSecure?.getOrderId?.() ?? null,
    };
  }

  addValue(amount?: number | string | null): any {
    return {
      transactionType: TransactionType.ADD_VALUE,
      paymentMethod: this,
      amount,
    };
  }

  balanceInquiry(inquiry?: any): any {
    return {
      transactionType: TransactionType.BALANCE,
      paymentMethod: this,
      balanceInquiryType: inquiry,
    };
  }

  refund(amount?: number | string | null): any {
    return {
      transactionType: TransactionType.REFUND,
      paymentMethod: this,
      amount,
    };
  }

  reverse(amount?: number | string | null): any {
    return {
      transactionType: TransactionType.REVERSAL,
      paymentMethod: this,
      amount,
    };
  }

  verify(): any {
    return {
      transactionType: TransactionType.VERIFY,
      paymentMethod: this,
    };
  }

  tokenize(_configName = "default", _paymentMethodUsageMode?: string): any {
    return {
      transactionType: TransactionType.TOKENIZE,
      paymentMethod: this,
    };
  }

  detokenize(): any {
    return {
      transactionType: TransactionType.DETOKENIZE,
      paymentMethod: this,
    };
  }
}
