export class BankPaymentResponse {
  public id: string | null = null;
  public redirectUrl: string | null = null;
  public paymentStatus: string | null = null;
  public type?: any;
  public tokenRequestId: string | null = null;
  public sortCode: string | null = null;
  public accountName: string | null = null;
  public accountNumber: string | null = null;
  public iban: string | null = null;
  public remittanceReferenceValue: string | null = null;
  public remittanceReferenceType: string | null = null;
  public amount: number | null = null;
  public currency: string | null = null;
  public maskedIbanLast4: string | null = null;
}
