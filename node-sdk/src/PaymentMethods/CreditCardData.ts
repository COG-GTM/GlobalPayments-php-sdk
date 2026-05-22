import { CvnPresenceIndicator } from "../Entities/Enums/CvnPresenceIndicator";
import { Credit } from "./Credit";
import { ICardData } from "./Interfaces/ICardData";

export class CreditCardData extends Credit implements ICardData {
  public number: string | null = null;
  public expMonth: string | null = null;
  public expYear: string | number | null = null;
  public cvn: string | number | null = null;
  public cvnPresenceIndicator: CvnPresenceIndicator = CvnPresenceIndicator.NOT_REQUESTED;
  public cardHolderName: string | null = null;
  public cardPresent = false;
  public readerPresent = false;
  public cardBrandTransactionId: string | null = null;

  getShortExpiry(): string | null {
    if (this.expMonth != null && this.expYear != null) {
      const month = String(this.expMonth).padStart(2, "0");
      const year = String(this.expYear).padStart(4, "0").slice(2, 4);
      return `${month}${year}`;
    }
    return null;
  }

  getCardType(): string | null {
    if (!this.number) return null;
    // Basic card type detection from BIN
    const num = this.number.replace(/\s/g, "");
    if (/^4/.test(num)) return "Visa";
    if (/^5[1-5]/.test(num) || /^2[2-7]/.test(num)) return "MasterCard";
    if (/^3[47]/.test(num)) return "Amex";
    if (/^6(?:011|5)/.test(num)) return "Discover";
    if (/^35/.test(num)) return "JCB";
    if (/^3(?:0[0-5]|[68])/.test(num)) return "DinersClub";
    return "Unknown";
  }

  hasInAppPaymentData(): boolean {
    return !!this.token && !!this.mobileType;
  }
}
