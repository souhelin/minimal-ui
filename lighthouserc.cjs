module.exports = {
  ci: {
    collect: {
      staticDistDir: "./web",
      numberOfRuns: 3,
    },
    assert: {
      // Start as warnings: collect a trustworthy baseline before enforcing hard gates.
      assertions: {
        "categories:performance": ["warn", { minScore: 0.8 }],
        "first-contentful-paint": ["warn", { maxNumericValue: 2000 }],
        "largest-contentful-paint": ["warn", { maxNumericValue: 3000 }],
        "total-blocking-time": ["warn", { maxNumericValue: 300 }],
        "cumulative-layout-shift": ["warn", { maxNumericValue: 0.1 }],
      },
    },
    upload: {
      // Keep reports inside the workflow artifact; do not publish them to public temporary storage.
      target: "filesystem",
      outputDir: "./.lighthouseci",
    },
  },
};
