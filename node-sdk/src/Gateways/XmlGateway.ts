import { Gateway, GatewayResponse } from "./Gateway";
import { GatewayException } from "../Entities/Exceptions/GatewayException";

export class XmlGateway extends Gateway {
  async doTransaction(
    request: string,
    endpoint = "",
  ): Promise<string> {
    const response = await this.sendRequest("POST", endpoint, request, undefined, "text/xml");

    if (response.statusCode !== 200) {
      throw new GatewayException(
        `Unexpected HTTP status code [${response.statusCode}]`,
        String(response.statusCode),
        response.rawResponse,
      );
    }

    return response.rawResponse;
  }
}
