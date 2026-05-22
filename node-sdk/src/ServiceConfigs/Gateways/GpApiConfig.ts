import { Configuration } from "../Configuration";
import { Environment } from "../../Entities/Enums/Environment";
import { ServiceEndpoints } from "../../Entities/Enums/ServiceEndpoints";
import { Channel } from "../../Entities/Enums/Channel";
import { ShaHashType } from "../../Entities/Enums/ShaHashType";
import { ConfigurationException } from "../../Entities/Exceptions/ConfigurationException";

export class GpApiConfig extends Configuration {
  public appId: string | null = null;
  public appKey: string | null = null;
  public channel: Channel | null = null;
  public country: string | null = null;
  public accessTokenInfo: any = null;
  public challengeNotificationUrl: string | null = null;
  public methodNotificationUrl: string | null = null;
  public merchantContactUrl: string | null = null;
  public merchantId: string | null = null;
  public permissions: string[] = [];
  public seconds_to_expire: number | null = null;
  public interval_to_expire: string | null = null;
  public statusUrl: string | null = null;
  public shaHashType: ShaHashType | null = null;

  constructor() {
    super();
  }

  validate(): void {
    if (!this.appId) {
      throw new ConfigurationException("appId is required for GP-API");
    }
    if (!this.appKey) {
      throw new ConfigurationException("appKey is required for GP-API");
    }
  }

  getServiceUrl(): string {
    if (this.environment === Environment.PRODUCTION) {
      return ServiceEndpoints.GP_API_PRODUCTION;
    }
    return ServiceEndpoints.GP_API_TEST;
  }
}
