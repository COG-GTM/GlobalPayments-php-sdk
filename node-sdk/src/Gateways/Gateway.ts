import axios, { AxiosInstance, AxiosRequestConfig, AxiosResponse } from "axios";
import { GatewayException } from "../Entities/Exceptions/GatewayException";

export interface GatewayResponse {
  statusCode: number;
  rawResponse: string;
  header: Record<string, string>;
}

export abstract class Gateway {
  public serviceUrl: string | null = null;
  public timeout = 65000;
  public headers: Record<string, string> = {};
  public webProxy: any = null;
  public requestLogger: any = null;
  public dynamicHeaders: Record<string, string> = {};

  protected httpClient: AxiosInstance;

  constructor() {
    this.httpClient = axios.create();
  }

  protected async sendRequest(
    method: string,
    endpoint: string,
    data?: string | null,
    queryParams?: Record<string, string>,
    contentType = "application/json",
  ): Promise<GatewayResponse> {
    const url = `${this.serviceUrl}${endpoint}`;

    const config: AxiosRequestConfig = {
      method: method as AxiosRequestConfig["method"],
      url,
      timeout: this.timeout,
      headers: {
        ...this.headers,
        ...this.dynamicHeaders,
        "Content-Type": contentType,
      },
      params: queryParams,
      data: data || undefined,
      validateStatus: () => true,
      decompress: true,
    };

    if (this.webProxy) {
      config.proxy = this.webProxy;
    }

    try {
      const response: AxiosResponse = await this.httpClient.request(config);

      const gatewayResponse: GatewayResponse = {
        statusCode: response.status,
        rawResponse: typeof response.data === "string" ? response.data : JSON.stringify(response.data),
        header: response.headers as Record<string, string>,
      };

      return gatewayResponse;
    } catch (error) {
      const err = error as Error;
      throw new GatewayException(
        `Error communicating with gateway: ${err.message}`,
        null,
        null,
        err,
      );
    }
  }
}
