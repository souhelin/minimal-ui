import assert from "node:assert/strict";
import { readFileSync } from "node:fs";
import test from "node:test";

const tokens = readFileSync(new URL("../../web/tokens.css", import.meta.url), "utf8");
const typography = readFileSync(new URL("../../web/typography.css", import.meta.url), "utf8");
const specimen = readFileSync(new URL("../../web/typography.html", import.meta.url), "utf8");
const specification = readFileSync(new URL("../../TYPOGRAPHY.md", import.meta.url), "utf8");

test("Minimal Typography family tokens provide local fallbacks", () => {
  for (const token of ["--mui-font-sans", "--mui-font-display", "--mui-font-rounded", "--mui-font-mono"]) {
    assert.ok(tokens.includes(token), `Missing token ${token}`);
  }
  assert.match(tokens, /--mui-font-sans:[^;]*system-ui/);
  assert.match(tokens, /--mui-font-mono:[^;]*monospace/);
});

test("typography specimen uses semantic landmarks and the shared token stylesheet", () => {
  assert.match(specimen, /<html lang="pt-BR">/);
  assert.match(specimen, /<main class="typography-page">/);
  assert.match(specimen, /href="tokens.css"/);
  assert.match(specimen, /href="typography.css"/);
  assert.match(specimen, /aria-labelledby=/);
});

test("font faces are not declared before original font binaries exist", () => {
  assert.doesNotMatch(typography, /^\s*@font-face/m);
  assert.match(specification, /Não há arquivos de fonte originais/);
  assert.match(specification, /Não entrega ainda uma fonte original compilada/);
});
