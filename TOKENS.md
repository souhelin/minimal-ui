# Design Tokens — Minimal UI

O Minimal usa duas camadas: tokens primitivos definem valores-base; tokens semânticos descrevem a função de cada cor. Componentes devem consumir tokens semânticos, não repetir valores hexadecimais.

## Paletas e origem da cor

O sistema oferece três modos de aparência (Sistema, Claro e Escuro) e paletas independentes do modo:

- **Sistema:** Web tenta usar a cor CSS AccentColor quando o navegador expõe a cor de destaque do sistema. No Android 12 ou superior, Compose pode usar a paleta dinâmica baseada no papel de parede.
- **Monocromático:** preserva a identidade neutra original do Minimal.
- **Predefinidas:** azul, violeta, verde, laranja e rosa.
- **Personalizada:** o desenvolvedor fornece uma cor-semente e o sistema deriva os papéis semânticos.

Uma página Web não recebe acesso universal às cores extraídas do papel de parede Android. Quando o navegador não expõe AccentColor, a paleta Sistema recua para uma base neutra. Para obter a paleta dinâmica do papel de parede, use a implementação Android.

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

## Papéis semânticos de cor

| Papel | Uso |
|---|---|
| bg | Fundo principal |
| surface / surface-elevated | Superfícies, cartões, menus e diálogos |
| surface-container / surface-container-high | Agrupamento e níveis de superfície |
| text / text-secondary | Texto principal e auxiliar |
| primary / on-primary | Ação principal e seu conteúdo |
| primary-container / on-primary-container | Ações tonais e seleção |
| secondary / on-secondary | Ações de suporte |
| secondary-container / on-secondary-container | Superfícies secundárias |
| tertiary / on-tertiary | Destaques complementares |
| tertiary-container / on-tertiary-container | Realces complementares |
| outline / outline-variant | Bordas, divisores e contornos |
| focus | Foco visível |
| disabled | Conteúdo indisponível |
| danger | Erros e validação |
| inverse-surface / inverse-text | Snackbar e feedback invertido |

Use o par on-* correspondente sobre cada papel de superfície ou ação. Não presuma que texto branco funciona em toda cor primária: paletas personalizadas devem calcular um foreground legível.

## Elevação, bordas e opacidade

- Elevation 0: sem sombra.
- Elevation 1: cartões interativos.
- Elevation 2: menus, snackbar e popovers.
- Elevation 3: dialogs e sheets.
- Bordas: 1 px usando outline/outline-variant; separadores usam divider.
- Disabled: opacidade 0.42; overlay modal: 0.48.
- Estados hover e pressed devem preservar contraste e legibilidade.

## Correspondência entre Web e Android

Os nomes semânticos têm equivalentes entre CSS Custom Properties e MaterialTheme.colorScheme. No Android, MinimalTheme resolve as cores nesta ordem:

1. colorScheme: esquema completo fornecido pelo aplicativo.
2. seedColor: paleta derivada de uma cor-semente do desenvolvedor.
3. dynamicColor = true no Android 12+: cores dinâmicas do sistema/papel de parede.
4. Paleta neutra do Minimal como fallback.

Na Web, theme.js deriva os tokens com base em AccentColor quando disponível e permite cor-semente personalizada. A extração direta da paleta do wallpaper não está disponível de forma universal em navegadores.

## Ícones e padrões de produto

- web/icons.js entrega ícones SVG com traço arredondado, nomes semânticos, currentColor e suporte a rótulos acessíveis.
- MinimalIcon no Android recebe um ImageVector, permitindo usar o conjunto vetorial preferido pelo aplicativo sem adicionar uma dependência de ícones pesada ao Minimal.
- MinimalAiComposer e MinimalBrowserAddressBar fornecem peças de interface; integração de modelo, navegação, histórico, abas e rede continuam sendo responsabilidade do aplicativo consumidor.

## Light, Dark e Sistema

- Light e Dark mantêm os mesmos nomes semânticos; apenas os valores mudam.
- Sistema acompanha prefers-color-scheme na Web e isSystemInDarkTheme() no Compose.
- Tema explícito tem precedência sobre a preferência do sistema.
- A escolha da paleta é independente da escolha Light/Dark.
- Não codifique cores diretamente nos componentes.

## Regras de uso

1. Componentes consomem tokens semânticos.
2. Tokens primitivos compõem escalas e tokens semânticos.
3. Não invente valores sem verificar a escala existente.
4. Estados não podem depender exclusivamente de cor.
5. Foco deve permanecer visível; respeite prefers-reduced-motion.
6. Alvos de toque recomendados: pelo menos 48 × 48 px.
7. Verifique WCAG AA: 4.5:1 para texto normal e 3:1 para texto grande e elementos gráficos relevantes.
8. Teste cores claras, escuras, predefinidas e personalizadas; confirme foregrounds, campos, bordas, ícones e estados disabled.


## Minimal Writing System — tipografia

O MWS adiciona papéis tipográficos semânticos sem criar uma segunda escala de espaçamento, cor ou tema.

### Tokens Web

Os tokens `--mws-type-*`, `--mws-weight-*`, `--mws-line-height-*`, `--mws-font-sans`, `--mws-font-mono` e `--mws-reading-width` são definidos em `web/tokens.css`. Os tamanhos usam `rem` para respeitar o zoom do navegador e a escala base de fonte.

### Papéis disponíveis na Web

As classes `.mws-display`, `.mws-headline`, `.mws-title`, `.mws-subtitle`, `.mws-body-large`, `.mws-body`, `.mws-body-small`, `.mws-label`, `.mws-prose` e `.mws-mono` são utilitários opcionais. Elas não alteram conteúdo, semântica HTML ou tom editorial automaticamente.

### Android

`MinimalTypography` fornece a escala padrão do Compose em `sp`, aplicada por `MinimalTheme`. O consumidor pode fornecer uma instância própria de Material 3 `Typography` pelo parâmetro `typography`, preservando a personalização da família tipográfica e dos estilos. `sp` respeita a escala de fonte do sistema.

A escala é uma referência semântica; componentes podem continuar usando estilos específicos quando a hierarquia e a função do componente exigirem.
