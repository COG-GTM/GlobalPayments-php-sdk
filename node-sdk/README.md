# GlobalPayments Node.js/TypeScript SDK

TypeScript migration of the [GlobalPayments PHP SDK](https://github.com/globalpayments/php-sdk). This SDK provides a unified interface for payment processing across multiple gateways.

## Supported Gateways

- **GP-API** (Global Payments API) - Modern REST gateway
- **GP-Ecom** (Realex Payments) - XML gateway
- **Portico** (Heartland) - SOAP/XML gateway
- **TransIT** - REST gateway
- **BillPay** - Bill payment gateway

## Supported Payment Methods

- Credit/Debit Cards
- eCheck/ACH
- Gift & Loyalty Cards
- Alternative Payment Methods (PayPal, Blik, Alipay, etc.)
- Buy Now Pay Later (BNPL)
- Bank Payments / Open Banking

## Terminal Integrations

- UPA (Unified Payments Application)
- PAX
- HPA (Heartland Payment Application)
- Diamond Cloud
- Genius/Cayan

## Installation

```bash
npm install @globalpayments/node-sdk
```

## Requirements

- Node.js 18.0+
- TypeScript 5.0+ (for development)

## Quick Start

```typescript
import { CreditCardData, ServicesContainer, GpApiConfig, Environment } from '@globalpayments/node-sdk';

// Configure the SDK
const config = new GpApiConfig();
config.appId = 'your-app-id';
config.appKey = 'your-app-key';
config.environment = Environment.TEST;

ServicesContainer.configure(config);

// Create a card
const card = new CreditCardData();
card.number = '4111111111111111';
card.expMonth = '12';
card.expYear = '2025';
card.cvn = '123';

// Process a charge
const response = await card.charge(129.99);
```

## Project Structure

```
src/
  Builders/          - Fluent transaction builder interfaces
  Entities/          - Data models (Transaction, Address, etc.)
    Enums/           - All enumeration types (~153 enums)
    Exceptions/      - Custom exception classes
    GpApi/           - GP-API specific entities
    PayFac/          - Payment Facilitator entities
    Reporting/       - Reporting entities
  Gateways/          - Gateway connector implementations
    Interfaces/      - Gateway interface contracts
  Mapping/           - Response-to-entity mapping
  PaymentMethods/    - Payment method handlers
    Interfaces/      - Payment method contracts
  ServiceConfigs/    - Gateway configuration classes
  Services/          - High-level service facades
  Terminals/         - Terminal integration (UPA, PAX, etc.)
  Utils/             - Utility classes (crypto, XML, etc.)
```

## Development

```bash
# Install dependencies
npm install

# Run tests
npm test

# Type check
npm run typecheck

# Lint
npm run lint

# Build
npm run build
```

## Key Architecture Differences from PHP SDK

1. **Async/Await**: All `execute()` methods return `Promise<T>` instead of synchronous results
2. **HTTP Client**: Uses `axios` instead of PHP cURL
3. **XML Handling**: Uses `fast-xml-parser` / `xmlbuilder2` instead of PHP DOM
4. **Crypto**: Uses Node.js built-in `crypto` module instead of PHP OpenSSL
5. **Type Safety**: Full TypeScript strict mode with proper typing
6. **Dual Module Output**: Supports both CommonJS and ESM

## License

GPL-2.0 - See [LICENSE.md](../LICENSE.md)
