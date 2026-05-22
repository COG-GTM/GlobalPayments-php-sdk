import {
  ApiException,
  GatewayException,
  BuilderException,
  ConfigurationException,
  UnsupportedTransactionException,
  ArgumentException,
  MessageException,
  NotImplementedException,
} from "../../../src/Entities/Exceptions";

describe("Exceptions", () => {
  describe("ApiException", () => {
    it("should extend Error", () => {
      const ex = new ApiException("test error");
      expect(ex).toBeInstanceOf(Error);
      expect(ex.message).toBe("test error");
      expect(ex.name).toBe("ApiException");
    });

    it("should chain inner exceptions", () => {
      const inner = new Error("inner error");
      const ex = new ApiException("outer error", inner);
      expect(ex.innerException).toBe(inner);
    });
  });

  describe("GatewayException", () => {
    it("should include response code and message", () => {
      const ex = new GatewayException("gateway error", "500", "Internal Server Error");
      expect(ex).toBeInstanceOf(ApiException);
      expect(ex.responseCode).toBe("500");
      expect(ex.responseMessage).toBe("Internal Server Error");
    });
  });

  describe("BuilderException", () => {
    it("should extend ApiException", () => {
      const ex = new BuilderException("builder error");
      expect(ex).toBeInstanceOf(ApiException);
      expect(ex.name).toBe("BuilderException");
    });
  });

  describe("ConfigurationException", () => {
    it("should extend ApiException", () => {
      const ex = new ConfigurationException("config error");
      expect(ex).toBeInstanceOf(ApiException);
      expect(ex.name).toBe("ConfigurationException");
    });
  });

  describe("UnsupportedTransactionException", () => {
    it("should have default message", () => {
      const ex = new UnsupportedTransactionException();
      expect(ex.message).toBe("Unsupported transaction");
    });
  });

  describe("NotImplementedException", () => {
    it("should have default message", () => {
      const ex = new NotImplementedException();
      expect(ex.message).toBe("Not implemented");
    });
  });

  describe("ArgumentException", () => {
    it("should extend ApiException", () => {
      const ex = new ArgumentException("bad argument");
      expect(ex).toBeInstanceOf(ApiException);
    });
  });

  describe("MessageException", () => {
    it("should extend ApiException", () => {
      const ex = new MessageException("message error");
      expect(ex).toBeInstanceOf(ApiException);
    });
  });
});
