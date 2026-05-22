import { ApiException } from "./ApiException";

export class ConfigurationException extends ApiException {
  constructor(message: string, innerException?: Error) {
    super(message, innerException);
    this.name = "ConfigurationException";
  }
}
