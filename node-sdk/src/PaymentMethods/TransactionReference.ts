import { PaymentMethodType } from "../Entities/Enums/PaymentMethodType";
import { IPaymentMethod } from "./Interfaces/IPaymentMethod";

export class TransactionReference implements IPaymentMethod {
  public paymentMethodType: PaymentMethodType = PaymentMethodType.CREDIT;
  public alternativePaymentType: string | null = null;
  public authCode: string | null = null;
  public batchNumber: string | null = null;
  public clientTransactionId: string | null = null;
  public orderId: string | null = null;
  public transactionId: string | null = null;
  public originalTransactionType: any = null;
  public originalAmount: string | null = null;
  public originalPaymentMethod: any = null;
  public partialApproval: string | null = null;
  public originalApprovedAmount: string | null = null;
  public originalProcessingCode: string | null = null;
  public originalIssuerResponse: any = null;
  public messageTypeIndicator: string | null = null;
  public ntsData: any = null;
  public systemTraceAuditNumber: string | null = null;
  public acquiringInstitutionId: string | null = null;
}
