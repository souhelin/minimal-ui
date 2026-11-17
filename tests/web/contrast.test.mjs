import assert from "node:assert/strict";
import { readFileSync } from "node:fs";
import test from "node:test";
import vm from "node:vm";

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


// Execute the real theme generator with a small DOM shim and verify generated colors,
// rather than testing a second copy of its color algorithm.
function createThemeRuntime() {
  const values = {};
  const root = {
    dataset: {},
    style: {
      colorScheme: "light",
      setProperty(name, value) { values[name] = value; }
    },
    removeAttribute(name) { delete this.dataset[name]; }
  };
  const document = {
    documentElement: root,
    addEventListener() {},
    querySelectorAll() { return []; }
  };
  const window = {
    matchMedia() { return { matches: false, addEventListener() {} }; }
  };
  const sandbox = {
    document, window,
    CSS: { supports() { return false; } },
    localStorage: { getItem() { return null; }, setItem() {} },
    getComputedStyle() { return { color: "rgb(0, 0, 0)" }; }
  };
  vm.runInNewContext(
    readFileSync(new URL("../../web/theme.js", import.meta.url), "utf8"),
    sandbox
  );
  return { api: window.MinimalUITheme, values };
}

function parseGeneratedColor(value) {
  const hex = value.match(/^#([\da-f]{6})$/i);
  if (hex) {
    const channels = [0, 2, 4].map(i => parseInt(hex[1].slice(i, i + 2), 16) / 255);
    return channels.map(channel => channel <= 0.04045
      ? channel / 12.92
      : ((channel + 0.055) / 1.055) ** 2.4);
  }
  const hsl = value.match(/^hsl\(([\d.-]+) ([\d.-]+)% ([\d.-]+)%\)$/i);
  assert.ok(hsl, `Unsupported generated color: ${value}`);
  const [h, s, l] = [Number(hsl[1]) / 360, Number(hsl[2]) / 100, Number(hsl[3]) / 100];
  const hueToRgb = (p, q, t) => {
    let x = t;
    if (x < 0) x += 1;
    if (x > 1) x -= 1;
    if (x < 1 / 6) return p + (q - p) * 6 * x;
    if (x < 1 / 2) return q;
    if (x < 2 / 3) return p + (q - p) * (2 / 3 - x) * 6;
    return p;
  };
  let rgb;
  if (s === 0) rgb = [l, l, l];
  else {
    const q = l < 0.5 ? l * (1 + s) : l + s - l * s;
    const p = 2 * l - q;
    rgb = [hueToRgb(p, q, h + 1 / 3), hueToRgb(p, q, h), hueToRgb(p, q, h - 1 / 3)];
  }
  return rgb.map(channel => channel <= 0.04045
    ? channel / 12.92
    : ((channel + 0.055) / 1.055) ** 2.4);
}

function generatedContrast(values, foreground, background) {
  const first = luminance(parseGeneratedColor(values[`--${foreground}`]));
  const second = luminance(parseGeneratedColor(values[`--${background}`]));
  return (Math.max(first, second) + 0.05) / (Math.min(first, second) + 0.05);
}

test("generated Web palettes meet WCAG AA for text roles in light and dark modes", () => {
  const seeds = ["#000000", "#ffffff", "#3867D6", "#7257C8", "#267A59", "#B85D22", "#B34469", "#808080", "#00FFFF"];
  const palettes = ["monochrome", "blue", "violet", "green", "orange", "rose", "custom"];
  const pairs = [
    ["on-primary", "primary", 4.5],
    ["on-primary-container", "primary-container", 4.5],
    ["on-secondary", "secondary", 4.5],
    ["on-secondary-container", "secondary-container", 4.5],
    ["on-tertiary", "tertiary", 4.5],
    ["on-tertiary-container", "tertiary-container", 4.5],
    ["text", "bg", 4.5],
    ["text-secondary", "bg", 4.5],
    ["text", "surface", 4.5],
    ["text-secondary", "surface", 4.5],
    ["outline", "bg", 3],
    ["focus", "bg", 3]
  ];
  const runtime = createThemeRuntime();
  for (const mode of ["light", "dark"]) {
    for (const palette of palettes) {
      for (const seedColor of (palette === "custom" ? seeds : ["#3867D6"])) {
        runtime.api.setTheme({ mode, palette, seedColor });
        for (const [foreground, background, minimum] of pairs) {
          const ratio = generatedContrast(runtime.values, foreground, background);
          assert.ok(
            ratio >= minimum,
            `${mode}/${palette}/${seedColor}: --${foreground} on --${background} is ${ratio.toFixed(2)}:1; expected ${minimum}:1`
          );
        }
      }
    }
  }
});
