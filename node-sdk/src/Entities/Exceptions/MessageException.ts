import { ApiException } from "./ApiException";

export class MessageException extends ApiException {
  constructor(message: string, innerException?: Error) {
    super(message, innerException);
    this.name = "MessageException";
  }
}
