# Biblioteca de ícones — Minimal UI

A biblioteca Web fornece SVGs vetoriais independentes, sem fonte de ícones ou dependência externa em runtime. Os desenhos usam `currentColor`, traço arredondado e viewBox 24 × 24.

## Uso na Web

O arquivo `web/icons.js` registra a API global `MinimalIcons`.

~~~js
// Criar um ícone decorativo:
const icon = MinimalIcons.create("shield-check", { size: 24 });
container.append(icon);

// Ícone informativo com nome acessível:
const accessibleIcon = MinimalIcons.create("download", {
  size: 20,
  label: "Baixar arquivo"
});
container.append(accessibleIcon);

// Montar elementos declarativos no HTML:
MinimalIcons.mountAll(document);
~~~

~~~html
<button type="button" aria-label="Abrir favoritos">
  <span data-icon="bookmark" aria-hidden="true"></span>
  Favoritos
</button>
~~~

Opções de `create`:

| Opção | Padrão | Finalidade |
|---|---:|---|
| `size` | 24 | Largura e altura em pixels |
| `strokeWidth` | 1.8 | Espessura do traço |
| `label` | vazio | Define `role="img"` e `aria-label`; vazio torna o SVG decorativo |
| `className` | vazio | Classe CSS opcional |

`MinimalIcons.names` retorna a lista imutável de nomes registrados. Nomes desconhecidos usam o ícone `sparkles` como fallback.

## Categorias disponíveis

- **Ações e edição:** `add`, `check`, `check-circle`, `close`, `copy`, `delete`, `download`, `edit`, `filter`, `refresh`, `save`, `share`, `trash`, `upload`, `undo`, `redo`.
- **Navegação e navegador:** `arrow-left`, `arrow-right`, `arrow-up`, `arrow-down`, `chevron-down`, `chevron-left`, `chevron-right`, `chevron-up`, `external-link`, `globe`, `home`, `link`, `lock`, `map-pin`, `menu`, `more`, `refresh`, `unlock`.
- **Arquivos e conteúdo:** `archive`, `file`, `file-text`, `folder`, `folder-open`, `image`, `layers`, `paperclip`, `printer`, `tag`.
- **Comunicação e mídia:** `camera`, `headphones`, `mail`, `message`, `mic`, `notification`, `pause`, `play`, `send`, `video`, `volume-2`.
- **Sistema e dispositivos:** `accessibility`, `battery-full`, `calendar`, `cloud`, `database`, `device-mobile`, `keyboard`, `monitor`, `shield`, `shield-check`, `smartphone`, `wifi`, `wifi-off`, `zap`.
- **Produtividade e dados:** `activity`, `chart`, `clock`, `compass`, `grid`, `search`, `settings`, `sliders`, `sort`, `terminal`, `timer`.
- **Pessoas e feedback:** `eye`, `heart`, `help`, `info`, `star`, `user`, `users`, `sparkles`, `sun`, `moon`, `flag`.

A lista exata e atualizada pode sempre ser consultada em `MinimalIcons.names`.

## Android / Jetpack Compose

O wrapper `MinimalIcon` recebe um `ImageVector` fornecido pelo aplicativo consumidor. Isso mantém o módulo sem dependência obrigatória de um pacote de ícones específico:

~~~kotlin
MinimalIcon(
    imageVector = appIcons.bookmark,
    contentDescription = "Adicionar aos favoritos"
)
~~~

O aplicativo pode escolher Material Symbols, ícones próprios ou outra coleção compatível com Compose. O desenvolvedor é responsável por adicionar a dependência escolhida e fornecer descrições acessíveis.

## Acessibilidade e temas

- Ícones decorativos devem ser ocultados da árvore de acessibilidade; use `label` apenas quando o símbolo comunicar informação que não esteja presente em outro texto.
- Ações com ícones precisam de nome acessível no botão ou controle que as contém.
- Prefira `currentColor` na Web e a cor herdada do `MaterialTheme` no Android.
- Não use apenas a forma ou a cor do ícone para comunicar erro, sucesso ou estado selecionado.
- Preserve foco visível e alvos de toque adequados no controle interativo, não apenas no SVG.
- Não use o fallback como substituto para validar nomes incorretos durante o desenvolvimento.
