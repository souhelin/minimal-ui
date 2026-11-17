import { readFile } from "node:fs/promises";

const budgets = [
  { path: "web/components.css", maxBytes: 20 * 1024 },
  { path: "web/tokens.css", maxBytes: 6 * 1024 },
  { path: "web/components.js", maxBytes: 12 * 1024 },
  { path: "web/theme.js", maxBytes: 8 * 1024 },
  { path: "web/icons.js", maxBytes: 8 * 1024 },
  { path: "web/patterns.css", maxBytes: 8 * 1024 },
  { path: "web/typography.css", maxBytes: 6 * 1024 },
];

let totalBytes = 0;
let failed = false;

for (const budget of budgets) {
  try {
    const content = await readFile(budget.path);
    const bytes = content.byteLength;
    totalBytes += bytes;
    const status = bytes <= budget.maxBytes ? "OK" : "EXCEEDED";
    console.log(status + " " + budget.path + ": " + bytes + " / " + budget.maxBytes + " bytes");
    if (bytes > budget.maxBytes) failed = true;
  } catch (error) {
    console.error("MISSING " + budget.path + ": " + error.message);
    failed = true;
  }
}

const totalBudget = 68 * 1024;
console.log("TOTAL: " + totalBytes + " / " + totalBudget + " bytes");
if (totalBytes > totalBudget) failed = true;
if (failed) {
  console.error("Web asset budget failed. Review the added CSS/JavaScript before increasing a limit.");
  process.exitCode = 1;
} else {
  console.log("Web asset budget passed.");
}
