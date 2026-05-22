import { Gateway, GatewayResponse } from "./Gateway";
import { GatewayException } from "../Entities/Exceptions/GatewayException";

export class RestGateway extends Gateway {
  async doTransaction(
    method: string,
    endpoint: string,
    data?: string | null,
    queryParams?: Record<string, string>,
    contentType = "application/json",
  ): Promise<string> {
    const response = await this.sendRequest(method, endpoint, data, queryParams, contentType);

    if (response.statusCode !== 200 && response.statusCode !== 204) {
      const parsed = this.parseResponse(response.rawResponse);
      throw new GatewayException(
        `Status code: ${response.statusCode} - ${parsed}`,
        String(response.statusCode),
        response.rawResponse,
      );
    }

    return response.rawResponse;
  }

  protected parseResponse(rawResponse: string): string {
    try {
      const parsed = JSON.parse(rawResponse);
      return parsed.error_description || parsed.message || rawResponse;
    } catch {
      return rawResponse;
    }
  }
}
