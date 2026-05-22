import { ApiException } from "./ApiException";

export class NotImplementedException extends ApiException {
  constructor(message?: string) {
    super(message ?? "Not implemented");
    this.name = "NotImplementedException";
  }
}
