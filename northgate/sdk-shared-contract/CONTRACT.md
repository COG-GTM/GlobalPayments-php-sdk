# sdk-shared-contract

Cross-SDK contracts for Northgate Payments. Both `gateway-java-client` (`com.northgate.gateway.PanMask`) and
`gateway-php-client` (`Northgate\Gateway\Util\PanMask`) run their `PanMaskContractTest` against `vectors/*.json`;
a vector change that one SDK cannot satisfy is a release-blocking event for **both** teams.

| contract | version | status | consumers |
|---|---|---|---|
| `pan-masking` | 1 (`vectors/pan-masking.v1.json`) | active | gateway-java-client, gateway-php-client, merchant-portal (receipts), bin-lookup-service (display label) |

Rules
- Only `@northgate/sdk-shared` merges vector changes, and only with a decision record under `decisions/`.
- The SDKs load the vectors from this repo in CI (`-Dcontract.dir=…` / `NG_CONTRACT_DIR=…`) and carry a synced copy in
  their own test resources for offline builds.
- `pan-masking.visible_prefix` mirrors the production SDK convention `ProtectSensitiveData::hideValue($key, $pan, 4, 6)`
  (last 4 + first 6) in `globalpayments/php-sdk`.
