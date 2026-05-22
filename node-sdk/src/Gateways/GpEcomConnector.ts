export class GpEcomConnector extends XmlGateway {
  public supportsHostedPayments: any = true;
  public supportsRetrieval: any = true;
  public supportsUpdatePaymentDetails: any = true;
  public hostedPaymentConfig?: any;
}
