export class Card {
  public cardHolderName?: any;
  public cardNumber?: any;
  public maskedCardNumber?: any;
  public cardExpMonth?: any;
  public cardExpYear?: any;
  public token?: any;
  public maskedNumberLast4?: any;
  public brand?: any;
  public brandReference?: any;
  public bin?: any;
  public binCountry?: any;
  public accountType?: any;
  public issuer?: any;
  public cvnResponseMessage: string | null = null;
  public avsAddressResponse: string | null = null;
  public avsResponseCode: string | null = null;
  public tagResponse: string | null = null;
  public funding: string | null = null;
  public authCode: string | null = null;
}
