import { CreditCardData } from "../../../src/PaymentMethods/CreditCardData";
import { PaymentMethodType } from "../../../src/Entities/Enums/PaymentMethodType";
import { CvnPresenceIndicator } from "../../../src/Entities/Enums/CvnPresenceIndicator";

describe("CreditCardData", () => {
  it("should have correct payment method type", () => {
    const card = new CreditCardData();
    expect(card.paymentMethodType).toBe(PaymentMethodType.CREDIT);
  });

  it("should default cardPresent to false", () => {
    const card = new CreditCardData();
    expect(card.cardPresent).toBe(false);
  });

  it("should default readerPresent to false", () => {
    const card = new CreditCardData();
    expect(card.readerPresent).toBe(false);
  });

  it("should default CVN presence to NOT_REQUESTED", () => {
    const card = new CreditCardData();
    expect(card.cvnPresenceIndicator).toBe(CvnPresenceIndicator.NOT_REQUESTED);
  });

  describe("getShortExpiry", () => {
    it("should return MMYY format", () => {
      const card = new CreditCardData();
      card.expMonth = "12";
      card.expYear = "2025";
      expect(card.getShortExpiry()).toBe("1225");
    });

    it("should pad single digit month", () => {
      const card = new CreditCardData();
      card.expMonth = "1";
      card.expYear = "2025";
      expect(card.getShortExpiry()).toBe("0125");
    });

    it("should return null when month is missing", () => {
      const card = new CreditCardData();
      card.expYear = "2025";
      expect(card.getShortExpiry()).toBeNull();
    });

    it("should return null when year is missing", () => {
      const card = new CreditCardData();
      card.expMonth = "12";
      expect(card.getShortExpiry()).toBeNull();
    });
  });

  describe("getCardType", () => {
    it("should detect Visa", () => {
      const card = new CreditCardData();
      card.number = "4111111111111111";
      expect(card.getCardType()).toBe("Visa");
    });

    it("should detect MasterCard", () => {
      const card = new CreditCardData();
      card.number = "5425230000004415";
      expect(card.getCardType()).toBe("MasterCard");
    });

    it("should detect Amex", () => {
      const card = new CreditCardData();
      card.number = "374101000000608";
      expect(card.getCardType()).toBe("Amex");
    });

    it("should detect Discover", () => {
      const card = new CreditCardData();
      card.number = "6011000000000087";
      expect(card.getCardType()).toBe("Discover");
    });

    it("should return null for empty number", () => {
      const card = new CreditCardData();
      expect(card.getCardType()).toBeNull();
    });
  });

  describe("hasInAppPaymentData", () => {
    it("should return false when no token or mobileType", () => {
      const card = new CreditCardData();
      expect(card.hasInAppPaymentData()).toBe(false);
    });

    it("should return true when both token and mobileType set", () => {
      const card = new CreditCardData();
      card.token = "test-token";
      card.mobileType = "APPLEPAY";
      expect(card.hasInAppPaymentData()).toBe(true);
    });
  });
});
