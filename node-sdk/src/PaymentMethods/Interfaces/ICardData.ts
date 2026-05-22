import { IPaymentMethod } from "./IPaymentMethod";

export interface ICardData extends IPaymentMethod {
  number?: string | null;
  expMonth?: string | null;
  expYear?: string | number | null;
  cvn?: string | number | null;
  cardHolderName?: string | null;
  cardPresent?: boolean;
  readerPresent?: boolean;
  getShortExpiry(): string | null;
  getCardType(): string | null;
}
