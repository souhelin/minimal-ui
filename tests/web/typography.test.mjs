import assert from "node:assert/strict";
import { readFileSync } from "node:fs";
import test from "node:test";

const tokens = readFileSync(new URL("../../web/tokens.css", import.meta.url), "utf8");
const components = readFileSync(new URL("../../web/components.css", import.meta.url), "utf8");

const roles = [
  "display", "headline", "title", "subtitle",
  "body-large", "body", "body-small", "label"
];

test("MWS defines the complete semantic type scale with scalable CSS units", () => {
  for (const role of roles) {
    assert.ok(tokens.includes(`--mws-type-${role}:`), `Missing MWS type token: ${role}`);
  }
  assert.match(tokens, /--mws-type-body:\s*1rem/);
  assert.match(tokens, /--mws-line-height-body:\s*1\.55/);
  assert.match(tokens, /--mws-font-sans:\s*var\(--font-family\)/);
});

test("MWS exposes reusable semantic text classes without changing component content", () => {
  for (const role of roles) {
    assert.ok(components.includes(`.mws-${role}`), `Missing MWS class: mws-${role}`);
  }
  assert.ok(components.includes(".mws-prose"));
  assert.ok(components.includes(".mws-mono"));
});
