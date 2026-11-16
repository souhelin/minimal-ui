# Roadmap — Minimal UI

Acompanhe a implementação funcional do design system em Web e Android. Uma demonstração funcional Web e documentação Android não equivalem a uma implementação nativa Android completa.

## 1. Componentes avançados
- [x] Dialogs e modais — implementados na demonstração Web; padrão Compose documentado.
- [x] Bottom sheets — implementados na demonstração Web; padrão Compose documentado.
- [x] Menus e dropdowns — implementados na demonstração Web; padrão Compose documentado.
- [x] Tabs e navegação inferior — demonstração Web; padrão Compose documentado.
- [x] Radio buttons e sliders — controles nativos Web; padrão Compose documentado.
- [x] Tooltips, snackbars e alertas — demonstração Web; padrão Compose documentado.
- [x] Paginação e listas — paginação local Web; orientação LazyColumn/Paging 3 documentada.

## 2. Estados e interações
- [x] Hover, Focus, Pressed e Disabled — estilos Web e estados nativos.
- [x] Loading e Skeleton — demonstração Web; orientação Android documentada.
- [x] Validação de formulários — exemplo Web de nome e e-mail.
- [x] Transições e feedback visual — modal, menu, snackbar e loading.

## 3. Design tokens
- [x] Separação entre tokens primitivos e semânticos.
- [x] Integração Light, Dark e Sistema.
- [x] Tokens de elevação, bordas e opacidade.
- [x] Regras de uso dos tokens.

## 4. Layouts e templates
- [x] Dashboard de referência.
- [x] Página de configurações de referência.
- [x] Página de perfil de referência.
- [x] Formulários com validação.
- [x] Listas e tabelas de referência.
- [x] Estados vazios e telas de erro de referência.

## 5. Documentação para desenvolvedores
- [x] Guia de implementação HTML/CSS.
- [x] Guia para aplicativos Android.
- [x] Exemplos de componentes.
- [x] Convenções de nomes.
- [x] Regras de acessibilidade.

## Limitações conhecidas
- A demonstração é uma página de documentação interativa, não uma biblioteca npm publicada.
- Templates são composições de referência, não aplicações com backend.
- Android usa Jetpack Compose/Material 3, mas não há projeto Gradle completo neste repositório; build nativo ainda precisa ser validado em app consumidor.
- Paginação é local e demonstrativa; dados remotos exigem integração própria.
