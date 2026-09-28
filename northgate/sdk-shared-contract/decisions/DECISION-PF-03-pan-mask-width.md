# DECISION-PF-03 — PAN display width: 6+4 shared contract vs 8-digit BIN display

- Status: **OPEN — decision required**
- Finding: PF-03 (QSA pre-assessment NG-QSA-2026Q3-PRE), PCI DSS v4.0.1 Req. 3.4.1
- Decision owners: Marta Kowalski — Product Owner, Payments (@northgate/product-payments); [QSA to confirm] — acceptability of the chosen display under Req. 3.4.1 and the documented business need
- Raised by: Devin remediation run (fixture date 2026-09-28)

## Question
What is Northgate's PAN display standard: keep the estate-wide 6+4 mask (and fix the BIN panel to it), or move the shared contract to 8+4 across both SDKs, receipts and the portal?

## What the tests show
| # | run | result |
|---|-----|--------|
| 1 | baseline — run both SDKs' PanMaskContractTest against the active contract v1 (6+4) | `Tests run: 1, Failures: 0, Errors: 0 (1 test classes)` — pass |
| 2 | baseline — gateway-php-client against v1 | `OK (2 tests, 12 assertions)` — pass |
| 3 | conflict — run both SDKs against candidate v2 (8+4) as if it were adopted | `Tests run: 1, Failures: 1, Errors: 0 (1 test classes)` — fail (expected: both SDKs must change in lockstep) |
| 4 | conflict — gateway-php-client against candidate v2 | `Tests: 2, Assertions: 4, Failures: 1.` — fail (expected) |
| 5 | bin-lookup-service tests as-is (displayLabel shows 8+4, the display the QSA observed) | `OK (2 tests, 3 assertions)` — pass — confirms the consumer depends on 8+4 |

Both SDKs fail the candidate 8+4 vectors while passing v1: whichever way the decision goes, the change is either confined
to bin-lookup-service (Option A) or must land in both SDKs, the receipts and the portal in one coordinated release (Option B).

## Option A — Keep 6+4 estate-wide; BIN panel masks to the shared contract
- Changes: bin-lookup-service only: displayLabel() delegates to the shared 6+4 mask; the 8-digit BIN stays server-side for the lookup. No SDK, contract or receipt change.
- PCI view: Shows fewer digits than the Req. 3.4.1 ceiling; no business-need documentation required for the display. [QSA to confirm]
- Business impact: Portal operators lose the 7th/8th BIN digit in the panel label (issuer data itself is unaffected — it is looked up server-side). [product owner to decide]

## Option B — Adopt 8+4 as contract v2 across the estate
- Changes: sdk-shared-contract v2 active + gateway-java-client PanMask.VISIBLE_PREFIX=8 + gateway-php-client PanMask::VISIBLE_PREFIX=8 (+ synced test vectors in both) + merchant-portal receipts + hosted-fields display mask + globalpayments/php-sdk builders currently calling hideValue(…, 4, 6). Coordinated release of both SDKs.
- PCI view: Req. 3.4.1 permits BIN + last four as the maximum; for 16-digit PANs that leaves only 4 digits masked. PCI SSC guidance expects the entity to display only what a documented business need requires — written business need + [QSA to confirm] before adoption.
- Business impact: Receipts emailed to cardholders would show 12 of 16 digits. Two SDK releases, portal release, merchant communication. [product owner to decide]

## Decision
- Chosen option: [product owner to decide]
- Documented business need (required for Option B): [product owner to decide]
- Assessor position on the chosen display: [QSA to confirm]
- Decided on / by: ________

Devin did not choose. `vectors/pan-masking.v2-candidate.json` is committed with `status: candidate` so the candidate is
reviewable; the active contract stays v1 until this record is closed.
