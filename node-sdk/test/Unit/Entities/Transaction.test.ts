import { Transaction } from "../../../src/Entities/Transaction";
import { PaymentMethodType } from "../../../src/Entities/Enums/PaymentMethodType";

describe("Transaction", () => {
  describe("fromId", () => {
    it("should create a transaction with the given transaction ID", () => {
      const txn = Transaction.fromId("txn_123");
      expect(txn.transactionId).toBe("txn_123");
    });

    it("should default payment method type to CREDIT", () => {
      const txn = Transaction.fromId("txn_123");
      expect(txn.paymentMethodType).toBe(PaymentMethodType.CREDIT);
    });

    it("should set order ID when provided", () => {
      const txn = Transaction.fromId("txn_123", "order_456");
      expect(txn.transactionId).toBe("txn_123");
      expect(txn.orderId).toBe("order_456");
    });

    it("should accept explicit payment method type", () => {
      const txn = Transaction.fromId("txn_123", null, PaymentMethodType.DEBIT);
      expect(txn.paymentMethodType).toBe(PaymentMethodType.DEBIT);
    });
  });

  describe("fromClientTransactionId", () => {
    it("should create a transaction with client transaction ID", () => {
      const txn = Transaction.fromClientTransactionId("client_123");
      expect(txn.clientTransactionId).toBe("client_123");
    });
  });

  describe("getters", () => {
    it("should return null for authorizationCode when no reference", () => {
      const txn = new Transaction();
      expect(txn.authorizationCode).toBeNull();
    });

    it("should return authorization code from reference", () => {
      const txn = new Transaction();
      txn.transactionReference = { authCode: "AUTH123" };
      expect(txn.authorizationCode).toBe("AUTH123");
    });

    it("should return CREDIT as default payment method type", () => {
      const txn = new Transaction();
      expect(txn.paymentMethodType).toBe(PaymentMethodType.CREDIT);
    });
  });
});
