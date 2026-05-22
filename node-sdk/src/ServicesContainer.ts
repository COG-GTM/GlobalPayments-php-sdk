import { ConfigurationException } from "./Entities/Exceptions/ConfigurationException";

export class ConfiguredServices {
  public gatewayConnector: any = null;
  public recurringConnector: any = null;
  public reportingService: any = null;
  public deviceInterface: any = null;
  public deviceController: any = null;
  public tableServiceConnector: any = null;
  public payrollConnector: any = null;
  public secure3dProviders: Record<string, any> = {};
  public fraudService: any = null;
  public billPayProvider: any = null;
  public openBankingProvider: any = null;
  public payFacProvider: any = null;
  public fileProcessingService: any = null;
}

export class ServicesContainer {
  private static _instance: ServicesContainer | null = null;
  private configurations: Map<string, ConfiguredServices> = new Map();

  private constructor() {}

  static instance(): ServicesContainer {
    if (!ServicesContainer._instance) {
      ServicesContainer._instance = new ServicesContainer();
    }
    return ServicesContainer._instance;
  }

  static configure(config: any, configName = "default"): void {
    config.validate();
    const cs = new ConfiguredServices();
    ServicesContainer.instance().addConfiguration(configName, cs);
  }

  addConfiguration(configName: string, config: ConfiguredServices): void {
    this.configurations.set(configName, config);
  }

  getClient(configName = "default"): ConfiguredServices {
    const config = this.configurations.get(configName);
    if (!config) {
      throw new ConfigurationException(
        `Services container not configured for config name: ${configName}`,
      );
    }
    return config;
  }

  static reset(): void {
    ServicesContainer._instance = null;
  }
}
