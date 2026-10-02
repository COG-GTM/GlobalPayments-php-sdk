"""Contract self-tests: every active vector file is well-formed and internally consistent (python3 -m unittest)."""
import json
import pathlib
import unittest

ROOT = pathlib.Path(__file__).resolve().parents[1]


def reference_mask(pan: str, prefix: int, suffix: int, ch: str) -> str:
    return pan[:prefix] + ch * (len(pan) - prefix - suffix) + pan[-suffix:]


class PanMaskingVectors(unittest.TestCase):
    def files(self):
        return sorted((ROOT / "vectors").glob("pan-masking.*.json"))

    def test_at_least_one_active_contract(self):
        active = [f for f in self.files() if json.loads(f.read_text())["status"] == "active"]
        self.assertEqual(len(active), 1, "exactly one active pan-masking contract")

    def test_vectors_match_reference_mask(self):
        for f in self.files():
            c = json.loads(f.read_text())
            for v in c["vectors"]:
                with self.subTest(file=f.name, pan=v["pan"]):
                    self.assertEqual(v["masked"], reference_mask(v["pan"], c["visible_prefix"], c["visible_suffix"], c["mask_char"]))

    def test_never_more_than_bin_plus_last_four(self):
        # PCI DSS v4.0.1 Req. 3.4.1: BIN + last four is the ceiling. 8 is the longest BIN (ISO/IEC 7812 8-digit BINs).
        for f in self.files():
            c = json.loads(f.read_text())
            self.assertLessEqual(c["visible_prefix"], 8, f.name)
            self.assertLessEqual(c["visible_suffix"], 4, f.name)


if __name__ == "__main__":
    unittest.main()
