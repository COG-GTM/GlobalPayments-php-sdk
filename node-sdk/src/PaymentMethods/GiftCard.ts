import { PaymentMethodType } from "../Entities/Enums/PaymentMethodType";
import { TransactionType } from "../Entities/Enums/TransactionType";
import { IPaymentMethod } from "./Interfaces/IPaymentMethod";

export class GiftCard implements IPaymentMethod {
  public paymentMethodType: PaymentMethodType = PaymentMethodType.GIFT;
  public alias: string | null = null;
  public cardType = "Gift";
  public encryptionData: any = null;
  public number: string | null = null;
  public pin: string | null = null;
  public token: string | null = null;
  public trackData: string | null = null;
  public value: string | null = null;
  public valueType: string | null = null;

  static create(alias: string): any {
    return {
      transactionType: TransactionType.ALIAS,
      paymentMethod: (() => {
        const card = new GiftCard();
        card.alias = alias;
        return card;
      })(),
    };
  }

  addValue(amount?: number | string | null): any {
    return {
      transactionType: TransactionType.ADD_VALUE,
      paymentMethod: this,
      amount,
    };
  }

  activate(amount?: number | string | null): any {
    return {
      transactionType: TransactionType.ACTIVATE,
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

  charge(amount?: number | string | null): any {
    return {
      transactionType: TransactionType.SALE,
      paymentMethod: this,
      amount,
    };
  }

  deactivate(): any {
    return {
      transactionType: TransactionType.DEACTIVATE,
      paymentMethod: this,
    };
  }

  refund(amount?: number | string | null): any {
    return {
      transactionType: TransactionType.REFUND,
      paymentMethod: this,
      amount,
    };
  }

  replace(newCard?: GiftCard): any {
    return {
      transactionType: TransactionType.REPLACE,
      paymentMethod: this,
      replacementCard: newCard,
    };
  }

  reverse(amount?: number | string | null): any {
    return {
      transactionType: TransactionType.REVERSAL,
      paymentMethod: this,
      amount,
    };
  }

  rewards(amount?: number | string | null): any {
    return {
      transactionType: TransactionType.REWARD,
      paymentMethod: this,
      amount,
    };
  }
}
