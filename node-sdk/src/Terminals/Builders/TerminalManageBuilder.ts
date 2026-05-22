export class TerminalManageBuilder extends TerminalBuilder {
  public amount?: any;
  public currency?: any;
  public gratuity?: any;
  public transactionId?: any;
  public terminalRefNumber?: any;
  public taxType: string | null = null;
  public preAuthAmount?: any;
  public taxExempt: string | null = null;
  public orderId: string | null = null;
  public taxAmount: string | null = null;
  public lodgingData?: any;
}
