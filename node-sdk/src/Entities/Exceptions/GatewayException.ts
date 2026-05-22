import { ApiException } from "./ApiException";

export class GatewayException extends ApiException {
  public responseCode: string | null;
  public responseMessage: string | null;

  constructor(
    message: string,
    responseCode: string | null = null,
    responseMessage: string | null = null,
    innerException?: Error,
  ) {
    super(message, innerException);
    this.name = "GatewayException";
    this.responseCode = responseCode;
    this.responseMessage = responseMessage;
  }
}
