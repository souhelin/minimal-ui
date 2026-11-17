import assert from "node:assert/strict";
import { readFileSync } from "node:fs";
import test from "node:test";

const css = readFileSync(new URL("../../web/tokens.css", import.meta.url), "utf8");

function readBlock(selector) {
  const start = css.indexOf(selector);
  assert.notEqual(start, -1, `Missing CSS block: ${selector}`);
  const open = css.indexOf("{", start);
  let depth = 0;
  for (let index = open; index < css.length; index += 1) {
    if (css[index] === "{") depth += 1;
    if (css[index] === "}" && --depth === 0) return css.slice(open + 1, index);
  }
  throw new Error(`Unclosed CSS block: ${selector}`);
}

function parseVariables(block) {
  return Object.fromEntries([...block.matchAll(/--([\w-]+)\s*:\s*([^;]+);/g)]
    .map(([, name, value]) => [name, value.trim()]));
}

function resolveColor(variables, name, visited = new Set()) {
  assert.ok(!visited.has(name), `Circular CSS variable: --${name}`);
  visited.add(name);
  const value = variables[name];
  assert.ok(value, `Missing CSS variable: --${name}`);
  const reference = value.match(/^var\(--([\w-]+)\)$/);
  if (reference) return resolveColor(variables, reference[1], visited);
  const hex = value.replace("#", "");
  assert.match(hex, /^(?:[\da-f]{3}|[\da-f]{6})$/i, `Unsupported color token: ${name}=${value}`);
  const normalized = hex.length === 3 ? [...hex].map(char => char + char).join("") : hex;
  return [0, 2, 4].map(index => Number.parseInt(normalized.slice(index, index + 2), 16) / 255);
}

function luminance(rgb) {
  const linear = rgb.map(channel => channel <= 0.04045
    ? channel / 12.92
    : ((channel + 0.055) / 1.055) ** 2.4);
  return 0.2126 * linear[0] + 0.7152 * linear[1] + 0.0722 * linear[2];
}

function contrast(variables, foreground, background) {
  const first = luminance(resolveColor(variables, foreground));
  const second = luminance(resolveColor(variables, background));
  return (Math.max(first, second) + 0.05) / (Math.min(first, second) + 0.05);
}

const themes = [
  ["light", parseVariables(readBlock(":root{"))],
  ["dark", {
    ...parseVariables(readBlock(":root{")),
    ...parseVariables(readBlock(":root[data-theme=dark]"))
  }]
];

for (const [theme, variables] of themes) {
  test(`${theme} theme meets WCAG AA for semantic text and status tokens`, () => {
    const pairs = [
      ["text", "bg", 4.5],
      ["text", "surface", 4.5],
      ["text-secondary", "bg", 4.5],
      ["text-secondary", "surface", 4.5],
      ["inverse-text", "inverse-surface", 4.5],
      ["danger", "bg", 4.5],
      ["focus", "bg", 3],
      ["on-primary", "primary", 4.5],
      ["on-primary-container", "primary-container", 4.5],
      ["on-secondary", "secondary", 4.5],
      ["on-secondary-container", "secondary-container", 4.5],
      ["on-tertiary", "tertiary", 4.5],
      ["on-tertiary-container", "tertiary-container", 4.5],
      ["outline", "bg", 3]
    ];
    for (const [foreground, background, minimum] of pairs) {
      const ratio = contrast(variables, foreground, background);
      assert.ok(
        ratio >= minimum,
        `${theme}: --${foreground} on --${background} is ${ratio.toFixed(2)}:1; expected at least ${minimum}:1`
      );
    }
  });
}
