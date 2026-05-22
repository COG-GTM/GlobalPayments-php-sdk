import { Address } from "../../../src/Entities/Address";

describe("Address", () => {
  describe("cleanPhoneNumber", () => {
    it("should remove non-digit characters", () => {
      expect(Address.cleanPhoneNumber("(555) 123-4567")).toBe("5551234567");
    });

    it("should handle already clean numbers", () => {
      expect(Address.cleanPhoneNumber("5551234567")).toBe("5551234567");
    });
  });

  describe("cleanZipCode", () => {
    it("should remove special characters", () => {
      expect(Address.cleanZipCode("12345-6789")).toBe("123456789");
    });

    it("should allow alphanumeric", () => {
      expect(Address.cleanZipCode("SW1A 1AA")).toBe("SW1A1AA");
    });
  });

  describe("checkPhoneNumber", () => {
    it("should accept valid phone numbers", () => {
      expect(Address.checkPhoneNumber("5551234567")).toBe("5551234567");
    });

    it("should throw for phone numbers exceeding max length", () => {
      expect(() => Address.checkPhoneNumber("123456789012345678901")).toThrow();
    });
  });

  describe("checkZipCode", () => {
    it("should accept valid zip codes", () => {
      expect(Address.checkZipCode("12345")).toBe("12345");
    });

    it("should throw for zip codes exceeding max length", () => {
      expect(() => Address.checkZipCode("1234567890")).toThrow();
    });
  });

  describe("getProvince", () => {
    it("should return province if set", () => {
      const addr = new Address();
      addr.province = "Ontario";
      expect(addr.getProvince()).toBe("Ontario");
    });

    it("should return state if province is not set", () => {
      const addr = new Address();
      addr.state = "California";
      expect(addr.getProvince()).toBe("California");
    });
  });
});
