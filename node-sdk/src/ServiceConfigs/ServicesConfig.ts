import { Configuration } from "./Configuration";

export class ServicesConfig extends Configuration {
  public gatewayConfig: any = null;
  public deviceConfig: any = null;
  public tableServiceConfig: any = null;
  public payrollConfig: any = null;

  validate(): void {
    if (this.gatewayConfig) {
      this.gatewayConfig.validate();
    }
  }
}
