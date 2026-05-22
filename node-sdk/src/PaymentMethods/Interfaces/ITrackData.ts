import { IPaymentMethod } from "./IPaymentMethod";

export interface ITrackData extends IPaymentMethod {
  value?: string | null;
  entryMethod?: any;
  discretionaryData?: string | null;
  pan?: string | null;
  expiry?: string | null;
}
