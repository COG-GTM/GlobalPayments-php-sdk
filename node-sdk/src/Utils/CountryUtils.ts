export interface CountryInfo {
  name: string;
  alpha2: string;
  alpha3: string;
  numeric: string;
}

const COUNTRY_MAP: Record<string, CountryInfo> = {
  US: { name: "United States", alpha2: "US", alpha3: "USA", numeric: "840" },
  GB: { name: "United Kingdom", alpha2: "GB", alpha3: "GBR", numeric: "826" },
  CA: { name: "Canada", alpha2: "CA", alpha3: "CAN", numeric: "124" },
  IE: { name: "Ireland", alpha2: "IE", alpha3: "IRL", numeric: "372" },
  DE: { name: "Germany", alpha2: "DE", alpha3: "DEU", numeric: "276" },
  FR: { name: "France", alpha2: "FR", alpha3: "FRA", numeric: "250" },
  ES: { name: "Spain", alpha2: "ES", alpha3: "ESP", numeric: "724" },
  IT: { name: "Italy", alpha2: "IT", alpha3: "ITA", numeric: "380" },
  NL: { name: "Netherlands", alpha2: "NL", alpha3: "NLD", numeric: "528" },
  AU: { name: "Australia", alpha2: "AU", alpha3: "AUS", numeric: "036" },
  JP: { name: "Japan", alpha2: "JP", alpha3: "JPN", numeric: "392" },
  CN: { name: "China", alpha2: "CN", alpha3: "CHN", numeric: "156" },
  BR: { name: "Brazil", alpha2: "BR", alpha3: "BRA", numeric: "076" },
  MX: { name: "Mexico", alpha2: "MX", alpha3: "MEX", numeric: "484" },
  IN: { name: "India", alpha2: "IN", alpha3: "IND", numeric: "356" },
};

export class CountryUtils {
  static getCountryInfo(value: string): CountryInfo | null {
    if (!value) return null;

    const upper = value.toUpperCase().trim();

    // Try alpha2 first
    if (COUNTRY_MAP[upper]) return COUNTRY_MAP[upper];

    // Try by name
    for (const info of Object.values(COUNTRY_MAP)) {
      if (info.name.toUpperCase() === upper) return info;
      if (info.alpha3 === upper) return info;
      if (info.numeric === upper) return info;
    }

    return null;
  }

  static isCountry(address: { countryCode?: string | null }, countryCode: string): boolean {
    return address.countryCode?.toUpperCase() === countryCode.toUpperCase();
  }

  static getCountryCodeByCountry(country: string): string | null {
    const info = CountryUtils.getCountryInfo(country);
    return info?.alpha2 ?? null;
  }

  static getCountryByCode(code: string): string | null {
    const info = CountryUtils.getCountryInfo(code);
    return info?.name ?? null;
  }
}
