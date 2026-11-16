(() => {
  const root = document.documentElement;
  const key = "minimal-ui-theme";
  const allowed = new Set(["system", "light", "dark"]);
  function applyTheme(value) {
    const theme = allowed.has(value) ? value : "system";
    if (theme === "system") root.removeAttribute("data-theme");
    else root.setAttribute("data-theme", theme);
    try { localStorage.setItem(key, theme); } catch (_) {}
    document.querySelectorAll("[data-theme-picker]").forEach(el => { el.value = theme; });
  }
  let initial = "system";
  try { initial = localStorage.getItem(key) || "system"; } catch (_) {}
  applyTheme(initial);
  document.addEventListener("change", event => {
    if (event.target.matches("[data-theme-picker]")) applyTheme(event.target.value);
  });
})();
