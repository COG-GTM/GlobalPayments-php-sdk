export class ApiException extends Error {
  public innerException?: Error;

  constructor(message: string, innerException?: Error) {
    super(message);
    this.name = "ApiException";
    this.innerException = innerException;
    if (innerException) {
      this.stack = `${this.stack}\nCaused by: ${innerException.stack}`;
    }
  }
}
