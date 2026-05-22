export interface IRecurringService {
  processRecurring(builder: any): Promise<any>;
  supportsRetrieval: boolean;
  supportsUpdatePaymentDetails: boolean;
}
