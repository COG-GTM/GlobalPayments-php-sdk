import * as crypto from "crypto";

export class GenerationUtils {
  static generateOrderId(): string {
    return crypto.randomUUID().replace(/-/g, "");
  }

  static generateTimestamp(): string {
    const now = new Date();
    const year = now.getUTCFullYear();
    const month = String(now.getUTCMonth() + 1).padStart(2, "0");
    const day = String(now.getUTCDate()).padStart(2, "0");
    const hours = String(now.getUTCHours()).padStart(2, "0");
    const minutes = String(now.getUTCMinutes()).padStart(2, "0");
    const seconds = String(now.getUTCSeconds()).padStart(2, "0");
    return `${year}${month}${day}${hours}${minutes}${seconds}`;
  }

  static generateHash(
    toHash: string,
    hashType: "sha1" | "sha256" | "sha512" = "sha1",
  ): string {
    return crypto.createHash(hashType).update(toHash).digest("hex");
  }

  static generateUUID(): string {
    return crypto.randomUUID();
  }

  static generateNewId(): string {
    return crypto.randomUUID().replace(/-/g, "").substring(0, 20);
  }
}
