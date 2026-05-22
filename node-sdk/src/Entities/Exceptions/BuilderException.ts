import { ApiException } from "./ApiException";

export class BuilderException extends ApiException {
  constructor(message: string, innerException?: Error) {
    super(message, innerException);
    this.name = "BuilderException";
  }
}
