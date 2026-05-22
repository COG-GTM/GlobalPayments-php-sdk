import { ApiException } from "./ApiException";

export class ArgumentException extends ApiException {
  constructor(message: string, innerException?: Error) {
    super(message, innerException);
    this.name = "ArgumentException";
  }
}
