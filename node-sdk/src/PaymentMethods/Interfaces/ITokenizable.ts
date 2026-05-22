export interface ITokenizable {
  token?: string | null;
  tokenize(configName?: string, paymentMethodUsageMode?: string): any;
  detokenize(): any;
}
