import { Environment } from "../Entities/Enums/Environment";
import { ConfigurationException } from "../Entities/Exceptions/ConfigurationException";

export abstract class Configuration {
  public timeout = 65000;
  public environment: Environment = Environment.TEST;
  public requestLogger: any = null;
  public webProxy: any = null;
  public dynamicHeaders: Record<string, string> = {};

  abstract validate(): void;

  protected validateNotNull(value: any, fieldName: string): void {
    if (value === null || value === undefined) {
      throw new ConfigurationException(`${fieldName} is not set`);
    }
  }
}
