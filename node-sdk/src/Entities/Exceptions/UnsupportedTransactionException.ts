import { ApiException } from "./ApiException";

export class UnsupportedTransactionException extends ApiException {
  constructor(message?: string) {
    super(message ?? "Unsupported transaction");
    this.name = "UnsupportedTransactionException";
  }
}
