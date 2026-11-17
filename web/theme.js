(() => {
  const root = document.documentElement;
  const modeKey = "minimal-ui-theme";
  const paletteKey = "minimal-ui-palette";
  const seedKey = "minimal-ui-seed";
  const presets = {
    monochrome: "#555555", blue: "#3867D6", violet: "#7257C8",
    green: "#267A59", orange: "#B85D22", rose: "#B34469"
  };
  const modes = new Set(["system", "light", "dark"]);
  const palettes = new Set(["system", ...Object.keys(presets), "custom"]);
  const media = window.matchMedia("(prefers-color-scheme: dark)");
  const clamp = (n, min, max) => Math.min(max, Math.max(min, n));
  function rgb(value) {
    const hex = String(value || "").match(/^#?([\da-f]{6})$/i);
    if (hex) return [0, 2, 4].map(i => parseInt(hex[1].slice(i, i + 2), 16));
    const parts = String(value || "").match(/rgba?\(([\d.]+)[,\s]+([\d.]+)[,\s]+([\d.]+)/i);
    return parts ? parts.slice(1, 4).map(Number) : null;
  }
  function hsl(color) {
    const c = color.map(v => v / 255), max = Math.max(...c), min = Math.min(...c);
    const delta = max - min, light = (max + min) / 2;
    let hue = 0;
    if (delta) {
      if (max === c[0]) hue = ((c[1] - c[2]) / delta) % 6;
      else if (max === c[1]) hue = (c[2] - c[0]) / delta + 2;
      else hue = (c[0] - c[1]) / delta + 4;
    }
    const saturation = delta ? delta / (1 - Math.abs(2 * light - 1)) : 0;
    return [((hue * 60) + 360) % 360, saturation * 100, light * 100];
  }
  function tone(h, s, l) { return "hsl(" + Math.round(h) + " " + Math.round(s) + "% " + Math.round(l) + "%)"; }
  function ink(h, s, l) {
    const a = s / 100 * Math.min(l / 100, 1 - l / 100);
    const channel = n => {
      const k = (n + h / 30) % 12;
      const value = l / 100 - a * Math.max(-1, Math.min(k - 3, 9 - k, 1));
      return value <= 0.04045 ? value / 12.92 : Math.pow((value + 0.055) / 1.055, 2.4);
    };
    const luminance = .2126 * channel(0) + .7152 * channel(8) + .0722 * channel(4);
    return luminance > .179 ? "#000000" : "#ffffff";
  }
  function systemAccent() {
    if (!CSS.supports("color", "AccentColor")) return null;
    const probe = document.createElement("span");
    probe.style.color = "AccentColor";
    probe.hidden = true;
    root.append(probe);
    const value = getComputedStyle(probe).color;
    probe.remove();
    return rgb(value);
  }
  function applyPalette(name, customSeed, dark) {
    const seed = name === "custom" ? rgb(customSeed) :
      name === "system" ? (systemAccent() || rgb("#555555")) : rgb(presets[name] || presets.monochrome);
    const [h, rawS] = hsl(seed || rgb("#555555"));
    const s = name === "monochrome" || rawS < 12 ? 0 : clamp(rawS, 34, 88);
    const surfaceS = Math.min(12, s * 0.13);
    const primary = tone(h, s, dark ? (s ? 78 : 92) : (s ? 42 : 16));
    const variables = {
      "--primary": primary, "--on-primary": dark ? tone(h, 18, 18) : ink(h, s, s ? 42 : 16),
      "--primary-container": tone(h, Math.max(8, s * .55), dark ? 30 : 91),
      "--on-primary-container": dark ? tone(h, 20, 94) : tone(h, Math.max(8, s * .7), 19),
      "--secondary": tone(h + 18, s * .65, dark ? 76 : 39),
      "--on-secondary": dark ? "#111111" : ink(h + 18, s * .65, 39),
      "--secondary-container": tone(h + 18, s * .38, dark ? 27 : 92),
      "--on-secondary-container": dark ? "#f5f2fa" : "#201d28",
      "--tertiary": tone(h + 54, s * .75, dark ? 78 : 36),
      "--on-tertiary": dark ? "#171018" : ink(h + 54, s * .75, 36),
      "--tertiary-container": tone(h + 54, s * .42, dark ? 29 : 92),
      "--on-tertiary-container": dark ? "#fff0f8" : "#281321",
      "--bg": tone(h, surfaceS, dark ? 7 : 99),
      "--surface": tone(h, surfaceS, dark ? 10 : 99),
      "--surface-elevated": tone(h, surfaceS, dark ? 15 : 100),
      "--surface-container": tone(h, surfaceS, dark ? 13 : 96),
      "--surface-container-high": tone(h, surfaceS, dark ? 18 : 93),
      "--text": tone(h, Math.min(7, surfaceS), dark ? 95 : 12),
      "--text-secondary": tone(h, Math.min(8, surfaceS), dark ? 77 : 38),
      "--border": tone(h, Math.min(10, surfaceS), dark ? 34 : 80),
      "--divider": tone(h, Math.min(10, surfaceS), dark ? 24 : 90),
      "--focus": tone(h, s, dark ? 82 : 34),
      "--outline": tone(h, s * .35, dark ? 62 : 47),
      "--outline-variant": tone(h, surfaceS, dark ? 33 : 82),
      "--disabled": tone(h, 2, dark ? 48 : 57),
      "--inverse-surface": tone(h, surfaceS, dark ? 94 : 20),
      "--inverse-text": dark ? "#18151c" : "#ffffff",
      "--skeleton": tone(h, surfaceS, dark ? 19 : 91),
      "--skeleton-highlight": tone(h, surfaceS, dark ? 25 : 96)
    };
    Object.entries(variables).forEach(([key, value]) => root.style.setProperty(key, value));
    root.dataset.colorScheme = name;
  }
  function read(key, fallback) {
    try { return localStorage.getItem(key) || fallback; } catch (_) { return fallback; }
  }
  function apply(settings, persist = true) {
    const mode = modes.has(settings.mode) ? settings.mode : "system";
    const palette = palettes.has(settings.palette) ? settings.palette : "system";
    const seed = settings.seedColor || read(seedKey, "#3867D6");
    const dark = mode === "dark" || (mode === "system" && media.matches);
    if (mode === "system") root.removeAttribute("data-theme");
    else root.dataset.theme = mode;
    root.style.colorScheme = dark ? "dark" : "light";
    applyPalette(palette, seed, dark);
    document.querySelectorAll("[data-theme-picker]").forEach(el => { el.value = mode; });
    document.querySelectorAll("[data-color-picker]").forEach(el => { el.value = palette; });
    document.querySelectorAll("[data-seed-picker]").forEach(el => { el.value = seed; });
    if (persist) {
      try {
        localStorage.setItem(modeKey, mode);
        localStorage.setItem(paletteKey, palette);
        localStorage.setItem(seedKey, seed);
      } catch (_) {}
    }
    current = { mode, palette, seedColor: seed };
  }
  let current = {
    mode: read(modeKey, "system"),
    palette: read(paletteKey, "system"),
    seedColor: read(seedKey, "#3867D6")
  };
  const config = window.MinimalUIThemeConfig || {};
  current = { ...current, ...config };
  apply(current, false);
  document.addEventListener("change", event => {
    const target = event.target;
    if (target.matches("[data-theme-picker]")) apply({ ...current, mode: target.value });
    if (target.matches("[data-color-picker]")) apply({ ...current, palette: target.value });
  });
  document.addEventListener("input", event => {
    if (event.target.matches("[data-seed-picker]")) apply({ ...current, palette: "custom", seedColor: event.target.value });
  });
  media.addEventListener("change", () => { if (current.mode === "system") apply(current, false); });
  window.MinimalUITheme = {
    setTheme: options => apply({ ...current, ...options }),
    getTheme: () => ({ ...current }),
    presets: Object.freeze({ ...presets })
  };
})();