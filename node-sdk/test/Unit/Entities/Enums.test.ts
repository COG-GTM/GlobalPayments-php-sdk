import {
  TransactionType,
  PaymentMethodType,
  Environment,
  TransactionModifier,
  Channel,
} from "../../../src/Entities/Enums";

describe("Enums", () => {
  describe("TransactionType", () => {
    it("should have correct bit-flag values", () => {
      expect(TransactionType.DECLINE).toBe(1);
      expect(TransactionType.VERIFY).toBe(2);
      expect(TransactionType.CAPTURE).toBe(4);
      expect(TransactionType.AUTH).toBe(8);
      expect(TransactionType.REFUND).toBe(16);
      expect(TransactionType.REVERSAL).toBe(32);
      expect(TransactionType.SALE).toBe(64);
      expect(TransactionType.VOID).toBe(256);
      expect(TransactionType.BATCH_CLOSE).toBe(65536);
    });
  });

  describe("PaymentMethodType", () => {
    it("should have correct sequential values", () => {
      expect(PaymentMethodType.REFERENCE).toBe(0);
      expect(PaymentMethodType.CREDIT).toBe(1);
      expect(PaymentMethodType.DEBIT).toBe(2);
      expect(PaymentMethodType.EBT).toBe(3);
      expect(PaymentMethodType.ACH).toBe(5);
      expect(PaymentMethodType.GIFT).toBe(6);
    });
  });

  describe("Environment", () => {
    it("should have string values", () => {
      expect(Environment.TEST).toBe("TEST");
      expect(Environment.PRODUCTION).toBe("PRODUCTION");
    });
  });
});
