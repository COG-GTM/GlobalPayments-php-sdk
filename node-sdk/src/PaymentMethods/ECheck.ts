import { PaymentMethodType } from "../Entities/Enums/PaymentMethodType";
import { TransactionType } from "../Entities/Enums/TransactionType";
import { IPaymentMethod } from "./Interfaces/IPaymentMethod";

export class ECheck implements IPaymentMethod {
  public paymentMethodType: PaymentMethodType = PaymentMethodType.ACH;
  public accountNumber: string | null = null;
  public accountType: string | null = null;
  public achVerify = false;
  public birthYear: number | null = null;
  public checkHolderName: string | null = null;
  public checkNumber: string | null = null;
  public checkType: string | null = null;
  public checkVerify = false;
  public driversLicenseNumber: string | null = null;
  public driversLicenseState: string | null = null;
  public entryMode: string | null = null;
  public micrNumber: string | null = null;
  public phoneNumber: string | null = null;
  public routingNumber: string | null = null;
  public secCode: string | null = null;
  public ssnLast4: string | null = null;
  public token: string | null = null;
  public checkReference: string | null = null;
  public merchantNotes: string | null = null;
  public bankName: string | null = null;
  public bankAddress: any = null;
  public branchTransitNumber: string | null = null;
  public financialInstitutionNumber: string | null = null;

  charge(amount?: number | string | null): any {
    return {
      transactionType: TransactionType.SALE,
      paymentMethod: this,
      amount,
    };
  }

  refund(amount?: number | string | null): any {
    return {
      transactionType: TransactionType.REFUND,
      paymentMethod: this,
      amount,
    };
  }
}
