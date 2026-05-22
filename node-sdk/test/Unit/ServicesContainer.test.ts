import { ServicesContainer, ConfiguredServices } from "../../src/ServicesContainer";
import { ConfigurationException } from "../../src/Entities/Exceptions/ConfigurationException";

describe("ServicesContainer", () => {
  beforeEach(() => {
    ServicesContainer.reset();
  });

  it("should return the same instance (singleton)", () => {
    const instance1 = ServicesContainer.instance();
    const instance2 = ServicesContainer.instance();
    expect(instance1).toBe(instance2);
  });

  it("should throw when accessing unconfigured client", () => {
    expect(() => ServicesContainer.instance().getClient()).toThrow(ConfigurationException);
  });

  it("should store and retrieve configured services", () => {
    const cs = new ConfiguredServices();
    ServicesContainer.instance().addConfiguration("test", cs);
    expect(ServicesContainer.instance().getClient("test")).toBe(cs);
  });

  it("should reset the instance", () => {
    const cs = new ConfiguredServices();
    ServicesContainer.instance().addConfiguration("test", cs);
    ServicesContainer.reset();
    expect(() => ServicesContainer.instance().getClient("test")).toThrow(ConfigurationException);
  });
});
