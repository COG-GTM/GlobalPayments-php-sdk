import { Transaction } from "../../Entities/Transaction";

export interface IPaymentGateway {
  supportsHostedPayments: boolean;
  supportsOpenBanking: boolean;

  processAuthorization(builder: any): Promise<Transaction>;
  manageTransaction(builder: any): Promise<Transaction>;
  serializeRequest(builder: any): string;
}
