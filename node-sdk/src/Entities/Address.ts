import { ApiException } from "./Exceptions/ApiException";

export class Address {
  public type: string | null = null;
  public streetAddress1: string | null = null;
  public streetAddress2: string | null = null;
  public streetAddress3: string | null = null;
  public city: string | null = null;
  public province: string | null = null;
  public state: string | null = null;
  public postalCode: string | null = null;
  public phone: string | null = null;
  public country: string | null = null;
  public countryCode: string | null = null;

  private static readonly MAX_LENGTH: Record<string, number> = {
    PhoneNumber: 20,
    ZipCode: 9,
  };

  getProvince(): string | null {
    return this.province ?? this.state;
  }

  static cleanPhoneNumber(number: string): string {
    return number.trim().replace(/\D+/g, "");
  }

  static cleanZipCode(zip: string): string {
    return zip.trim().replace(/[^0-9A-Za-z]/g, "");
  }

  static checkPhoneNumber(phoneNumber: string): string {
    const cleaned = Address.cleanPhoneNumber(phoneNumber);
    if (cleaned && cleaned.length > Address.MAX_LENGTH.PhoneNumber) {
      throw new ApiException("phone number can not be empty or invalid");
    }
    return cleaned;
  }

  static checkZipCode(zipCode: string): string {
    const cleaned = Address.cleanZipCode(zipCode);
    if (cleaned && cleaned.length > Address.MAX_LENGTH.ZipCode) {
      throw new ApiException("zip code can not be empty or invalid");
    }
    return cleaned;
  }
}
