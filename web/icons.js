(() => {
  const NS = "http://www.w3.org/2000/svg";
  const paths = {
    activity:["M3 12h4l3-8 4 16 3-8h4"], add:["M12 5v14M5 12h14"],
    "arrow-left":["M19 12H5m7 7-7-7 7-7"], "arrow-right":["M5 12h14m-7-7 7 7-7 7"],
    "arrow-up":["M12 19V5m-7 7 7-7 7 7"], "arrow-down":["M12 5v14m7-7-7 7-7-7"],
    "arrow-up-right":["M7 17 17 7M7 7h10v10"], check:["m5 12 4 4L19 6"],
    "chevron-down":["m6 9 6 6 6-6"], "chevron-left":["m15 18-6-6 6-6"],
    "chevron-right":["m9 18 6-6-6-6"], close:["m6 6 12 12M18 6 6 18"],
    copy:["M8 8h12v12H8z","M4 16V4h12"], delete:["M4 7h16M10 11v6m4-6v6M6 7l1 14h10l1-14M9 7V4h6v3"],
    download:["M12 3v12m-5-5 5 5 5-5M5 20h14"], edit:["m14 5 5 5M4 20l4-.8L19 8a2.1 2.1 0 0 0-3-3L5 16z"],
    "external-link":["M14 4h6v6m0-6L10 14","M18 13v6H4V5h6"], eye:["M2 12s3.5-7 10-7 10 7 10 7-3.5 7-10 7-10-7-10-7Z","M12 9a3 3 0 1 0 0 6 3 3 0 0 0 0-6Z"],
    file:["M6 3h8l4 4v14H6z","M14 3v5h5"], filter:["M4 5h16l-6 7v6l-4 2v-8z"],
    folder:["M3 6h7l2 2h9v11H3z"], globe:["M12 2a10 10 0 1 0 0 20 10 10 0 0 0 0-20Z","M2 12h20","M12 2a15 15 0 0 1 0 20 15 15 0 0 1 0-20"], bookmark:["M6 4h12v17l-6-4-6 4z"], clock:["M12 7v5l3 2","M22 12a10 10 0 1 1-20 0 10 10 0 0 1 20 0Z"], grid:["M4 4h6v6H4zM14 4h6v6h-6zM4 14h6v6H4zM14 14h6v6h-6z"],
    heart:["M20.8 8.8c0 5-8.8 10-8.8 10s-8.8-5-8.8-10a4.6 4.6 0 0 1 8.8-1.5 4.6 4.6 0 0 1 8.8 1.5Z"],
    help:["M9.5 9a2.7 2.7 0 1 1 4.6 1.9c-1.3 1.2-2.1 1.5-2.1 3.1M12 18h.01","M22 12a10 10 0 1 1-20 0 10 10 0 0 1 20 0Z"],
    home:["m3 10 9-7 9 7v10a1 1 0 0 1-1 1h-5v-7h-6v7H4a1 1 0 0 1-1-1z"],
    info:["M12 11v6m0-10h.01","M22 12a10 10 0 1 1-20 0 10 10 0 0 1 20 0Z"],
    link:["M10 13a5 5 0 0 0 7.1 0l3-3A5 5 0 0 0 13 2.9l-1.7 1.7","M14 11a5 5 0 0 0-7.1 0l-3 3a5 5 0 0 0 7.1 7.1l1.7-1.7"],
    lock:["M5 10h14v11H5z","M8 10V7a4 4 0 0 1 8 0v3"], mail:["M3 5h18v14H3z","m3 6 9 7 9-7"],
    menu:["M4 6h16M4 12h16M4 18h16"], message:["M4 5h16v12H9l-5 4z"],
    minus:["M5 12h14"], moon:["M20 15.5A8.5 8.5 0 0 1 8.5 4 8.5 8.5 0 1 0 20 15.5Z"],
    more:["M5 12h.01M12 12h.01M19 12h.01"], notification:["M18 9a6 6 0 0 0-12 0c0 7-3 7-3 9h18c0-2-3-2-3-9M10 21h4"],
    paperclip:["m8 12 7-7a4 4 0 0 1 6 6l-9 9a6 6 0 0 1-8-8l9-9"],
    refresh:["M20 7v5h-5M4 17v-5h5","M5 9a7 7 0 0 1 12-3l3 6M4 12l3 6a7 7 0 0 0 12-3"],
    search:["M20 20l-4.5-4.5","M17 10a7 7 0 1 1-14 0 7 7 0 0 1 14 0Z"],
    send:["m22 2-7 20-4-9-9-4z","M22 2 11 13"], settings:["M12 8a4 4 0 1 0 0 8 4 4 0 0 0 0-8Z","M19.4 15a1.7 1.7 0 0 0 .3 1.9l.1.1-1.8 3.1-.2-.1a1.7 1.7 0 0 0-1.8 0l-.2.1h-3.6l-.1-.2a1.7 1.7 0 0 0-1.4-1l-.2-.1-3.1-1.8.1-.2a1.7 1.7 0 0 0 0-1.8l-.1-.2V11l.2-.1a1.7 1.7 0 0 0 1-1.4l.1-.2 1.8-3.1.2.1a1.7 1.7 0 0 0 1.8 0l.2-.1h3.6l.1.2a1.7 1.7 0 0 0 1.4 1l.2.1 3.1 1.8-.1.2a1.7 1.7 0 0 0 0 1.8l.1.2z"],
    share:["M18 8a3 3 0 1 0-3-3 3 3 0 0 0 3 3ZM6 15a3 3 0 1 0 0-6 3 3 0 0 0 0 6Zm12 7a3 3 0 1 0-3-3 3 3 0 0 0 3 3Z","m8.6 10.7 6.8-3.4m-6.8 8 6.8 3.4"],
    shield:["M12 22s8-4 8-11V5l-8-3-8 3v6c0 7 8 11 8 11Z","m9 12 2 2 4-4"],
    sparkles:["m12 3 1.5 6.5L20 11l-6.5 1.5L12 19l-1.5-6.5L4 11l6.5-1.5z","m19 14 1 3 3 1-3 1-1 3-1-3-3-1 3-1z"],
    star:["m12 3 2.7 5.7 6.3.9-4.5 4.4 1.1 6.2-5.6-3-5.6 3 1.1-6.2L3 9.6l6.3-.9z"],
    sun:["M12 8a4 4 0 1 0 0 8 4 4 0 0 0 0-8Z","M12 2v2m0 16v2M4.9 4.9l1.4 1.4m11.4 11.4 1.4 1.4M2 12h2m16 0h2M4.9 19.1l1.4-1.4M17.7 6.3l1.4-1.4"],
    trash:["M4 7h16M10 11v6m4-6v6M6 7l1 14h10l1-14M9 7V4h6v3"], upload:["M12 16V4m-5 5 5-5 5 5M5 20h14"],
    user:["M20 21a8 8 0 0 0-16 0","M12 13a4 4 0 1 0 0-8 4 4 0 0 0 0 8Z"],
    users:["M16 21v-2a4 4 0 0 0-4-4H6a4 4 0 0 0-4 4v2","M9 11a4 4 0 1 0 0-8 4 4 0 0 0 0 8Zm7-8a4 4 0 0 1 0 8m2 4a4 4 0 0 1 4 4v2"],
    video:["M15 10l6-4v12l-6-4z","M3 6h12v12H3z"], x:["M6 6l12 12M18 6 6 18"]
  };
  function create(name, options = {}) {
    const { size = 24, strokeWidth = 1.8, label = "", className = "" } = options;
    const svg = document.createElementNS(NS, "svg");
    svg.setAttribute("viewBox", "0 0 24 24");
    svg.setAttribute("width", String(size));
    svg.setAttribute("height", String(size));
    svg.setAttribute("fill", "none");
    svg.setAttribute("stroke", "currentColor");
    svg.setAttribute("stroke-width", String(strokeWidth));
    svg.setAttribute("stroke-linecap", "round");
    svg.setAttribute("stroke-linejoin", "round");
    svg.setAttribute("focusable", "false");
    if (className) svg.setAttribute("class", className);
    if (label) { svg.setAttribute("role", "img"); svg.setAttribute("aria-label", label); }
    else svg.setAttribute("aria-hidden", "true");
    (paths[name] || paths.sparkles).forEach(d => {
      const path = document.createElementNS(NS, "path");
      path.setAttribute("d", d);
      svg.append(path);
    });
    return svg;
  }
  function mountAll(scope = document) {
    scope.querySelectorAll("[data-icon]").forEach(node => {
      const name = node.dataset.icon;
      const label = node.getAttribute("aria-label") || "";
      node.replaceChildren(create(name, { size: node.dataset.size || 20, label }));
      node.classList.add("icon-slot");
    });
  }
  window.MinimalIcons = Object.freeze({ create, mountAll, names: Object.freeze(Object.keys(paths)) });
  mountAll();
})();