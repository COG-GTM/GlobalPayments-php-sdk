import { CardUtils } from "../../../src/Utils/CardUtils";

describe("CardUtils", () => {
  describe("getCardType", () => {
    it("should identify Visa cards", () => {
      expect(CardUtils.getCardType("4111111111111111")).toBe("Visa");
      expect(CardUtils.getCardType("4263970000005262")).toBe("Visa");
    });

    it("should identify MasterCard cards", () => {
      expect(CardUtils.getCardType("5425230000004415")).toBe("MasterCard");
      expect(CardUtils.getCardType("2223000010005780")).toBe("MasterCard");
    });

    it("should identify Amex cards", () => {
      expect(CardUtils.getCardType("374101000000608")).toBe("Amex");
    });

    it("should identify Discover cards", () => {
      expect(CardUtils.getCardType("6011000000000087")).toBe("Discover");
    });

    it("should identify JCB cards", () => {
      expect(CardUtils.getCardType("3566000000000000")).toBe("JCB");
    });

    it("should return null for empty string", () => {
      expect(CardUtils.getCardType("")).toBeNull();
    });
  });

  describe("isValidLuhn", () => {
    it("should validate correct card numbers", () => {
      expect(CardUtils.isValidLuhn("4111111111111111")).toBe(true);
      expect(CardUtils.isValidLuhn("5425230000004415")).toBe(true);
    });

    it("should reject invalid card numbers", () => {
      expect(CardUtils.isValidLuhn("1234567890123456")).toBe(false);
    });

    it("should reject empty string", () => {
      expect(CardUtils.isValidLuhn("")).toBe(false);
    });

    it("should handle spaces", () => {
      expect(CardUtils.isValidLuhn("4111 1111 1111 1111")).toBe(true);
    });
  });
});
