import { GenerationUtils } from "../../../src/Utils/GenerationUtils";

describe("GenerationUtils", () => {
  describe("generateOrderId", () => {
    it("should generate a non-empty string", () => {
      const orderId = GenerationUtils.generateOrderId();
      expect(orderId).toBeTruthy();
      expect(typeof orderId).toBe("string");
    });

    it("should generate unique IDs", () => {
      const id1 = GenerationUtils.generateOrderId();
      const id2 = GenerationUtils.generateOrderId();
      expect(id1).not.toBe(id2);
    });
  });

  describe("generateTimestamp", () => {
    it("should generate a timestamp string", () => {
      const ts = GenerationUtils.generateTimestamp();
      expect(ts).toMatch(/^\d{14}$/);
    });
  });

  describe("generateHash", () => {
    it("should generate a sha1 hash", () => {
      const hash = GenerationUtils.generateHash("test");
      expect(hash).toBe("a94a8fe5ccb19ba61c4c0873d391e987982fbbd3");
    });

    it("should generate a sha256 hash", () => {
      const hash = GenerationUtils.generateHash("test", "sha256");
      expect(hash).toBe("9f86d081884c7d659a2feaa0c55ad015a3bf4f1b2b0b822cd15d6c15b0f00a08");
    });
  });

  describe("generateUUID", () => {
    it("should generate a valid UUID", () => {
      const uuid = GenerationUtils.generateUUID();
      expect(uuid).toMatch(/^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$/);
    });
  });
});
