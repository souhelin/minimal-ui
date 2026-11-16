# Design Tokens — Minimal UI

O Minimal usa duas camadas: tokens primitivos definem valores-base; tokens semânticos descrevem a função desses valores. Componentes devem consumir tokens semânticos, não repetir cores arbitrárias.

## Tokens primitivos

| Token | Light | Dark |
|---|---|---|
| neutral-0 | #FFFFFF | #000000 |
| neutral-50 | #F7F7F7 | #111111 |
| neutral-100 | #EAEAEA | #181818 |
| neutral-200 | #D8D8D8 | #242424 |
| neutral-300 | #A0A0A0 | #303030 |
| neutral-600 | #606060 | #A0A0A0 |
| neutral-900 | #111111 | #FFFFFF |

Espaçamento: 4, 8, 12, 16, 20, 24, 32, 40, 48 e 64 px.
Forma: 8, 12, 16, 24 e 999 px.
Movimento: 100, 200 e 300 ms; curva cubic-bezier(.2, 0, 0, 1).

## Tokens semânticos

| Token | Uso |
|---|---|
| background | Fundo principal |
| surface | Cartões e superfícies |
| surface-elevated | Menus, diálogos e superfícies elevadas |
| text-primary / text-secondary | Texto principal e auxiliar |
| border / divider | Contornos e separadores |
| focus | Indicador de foco |
| disabled | Conteúdo indisponível |
| danger | Erros e validação |
| inverse-surface / inverse-text | Snackbar e feedback invertido |

## Elevação, bordas e opacidade

- Elevation 0: sem sombra.
- Elevation 1: cartões interativos.
- Elevation 2: menus, snackbar e popovers.
- Elevation 3: dialogs e sheets.
- Bordas: 1 px usando border; separadores usam divider.
- Disabled: opacidade 0.42; overlay modal: 0.48.
- Estados hover e pressed devem preservar contraste e legibilidade.

## Light, Dark e Sistema

- Light e Dark usam os mesmos nomes semânticos; apenas os valores mudam.
- Sistema acompanha prefers-color-scheme.
- Tema explícito tem precedência sobre a preferência do sistema.
- Não codifique cores de tema diretamente nos componentes.

## Regras de uso

1. Componentes consomem tokens semânticos.
2. Tokens primitivos compõem escalas e tokens semânticos.
3. Não invente valores sem verificar a escala existente.
4. Estados não podem depender exclusivamente de cor.
5. Foco deve permanecer visível; respeite prefers-reduced-motion.
6. Alvos de toque recomendados: pelo menos 48 × 48 px.
7. Verifique WCAG AA: 4.5:1 para texto normal e 3:1 para texto grande e elementos gráficos relevantes.
