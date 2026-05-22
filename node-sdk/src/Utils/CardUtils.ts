export class CardUtils {
  private static readonly cardTypes: Record<string, RegExp> = {
    Visa: /^4/,
    MasterCard: /^(5[1-5]|2[2-7])/,
    Amex: /^3[47]/,
    Discover: /^6(?:011|5)/,
    JCB: /^35/,
    DinersClub: /^3(?:0[0-5]|[68])/,
  };

  static getCardType(number: string): string | null {
    if (!number) return null;
    const cleaned = number.replace(/\s/g, "");
    for (const [type, pattern] of Object.entries(CardUtils.cardTypes)) {
      if (pattern.test(cleaned)) return type;
    }
    return "Unknown";
  }

  static isValidLuhn(number: string): boolean {
    if (!number) return false;
    const cleaned = number.replace(/\s/g, "");
    let sum = 0;
    let alternate = false;
    for (let i = cleaned.length - 1; i >= 0; i--) {
      let n = parseInt(cleaned[i], 10);
      if (isNaN(n)) return false;
      if (alternate) {
        n *= 2;
        if (n > 9) n -= 9;
      }
      sum += n;
      alternate = !alternate;
    }
    return sum % 10 === 0;
  }
}
