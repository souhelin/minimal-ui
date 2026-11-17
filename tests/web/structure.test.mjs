import assert from "node:assert/strict";
import { existsSync, readFileSync } from "node:fs";
import test from "node:test";

const html = readFileSync(new URL("../../web/index.html", import.meta.url), "utf8");

test("Web entry point references existing local assets", () => {
  const references = [...html.matchAll(/(?:src|href)="([^"]+)"/g)].map(([, value]) => value);
  const localAssets = references.filter(value =>
    !value.startsWith("#") &&
    !value.startsWith("//") &&
    !/^[a-z][a-z\d+.-]*:/i.test(value)
  );
  for (const asset of localAssets) {
    assert.ok(existsSync(new URL(`../../web/${asset}`, import.meta.url)), `Missing asset: ${asset}`);
  }
});

test("HTML IDs are unique", () => {
  const ids = [...html.matchAll(/\bid="([^"]+)"/g)].map(([, id]) => id);
  assert.equal(ids.length, new Set(ids).size, "Duplicate id attribute found");
});

test("ARIA and label references point to existing elements", () => {
  const ids = new Set([...html.matchAll(/\bid="([^"]+)"/g)].map(([, id]) => id));
  const references = [
    ...[...html.matchAll(/\b(?:aria-controls|aria-labelledby|for)="([^"]+)"/g)]
      .flatMap(([, value]) => value.split(/\s+/))
  ];
  for (const reference of references) {
    assert.ok(ids.has(reference), `Reference points to missing id: ${reference}`);
  }
});

test("internal navigation anchors point to existing sections", () => {
  const ids = new Set([...html.matchAll(/\bid="([^"]+)"/g)].map(([, id]) => id));
  const anchors = [...html.matchAll(/href="#([^"]+)"/g)].map(([, id]) => id);
  for (const anchor of anchors) {
    assert.ok(ids.has(anchor), `Anchor points to missing section: #${anchor}`);
  }
});
