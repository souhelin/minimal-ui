# Minimal UI

Um design system monocromático, minimalista e responsivo, inspirado nos princípios do Material Design 3 Expressive, com foco em simplicidade, consistência, acessibilidade e usabilidade.

## Prévia

**[Abrir a interface publicada](https://souhelin.github.io/minimal-ui/)**

A demonstração usa a implementação real em web/, com seletor de tema claro, escuro e automático. Os controles, diálogos, menus, abas, validação, paginação e feedback possuem interações demonstrativas.

## Como usar no seu projeto

Consulte o **[Guia de uso — Minimal UI](GUIA-DE-USO.md)** para aprender a copiar e integrar os componentes Web ou Android, aplicar os temas, reutilizar os tokens e adaptar as interações ao seu aplicativo.

## Estrutura

- web/: demonstração web em HTML, CSS e JavaScript, sem framework.
- web/components.js: interações da demonstração.
- android/: tema e componentes Jetpack Compose / Material 3.
- ANDROID.md: guia de integração Android.
- GUIA-DE-USO.md: instruções práticas para usuários aplicarem o Minimal em seus projetos.
- TOKENS.md: tokens primitivos, semânticos, elevação, forma e movimento.
- ROADMAP.md: escopo e estado de implementação.
- AGENTS.md: regras obrigatórias para agentes e contribuidores.
- .github/workflows/pages.yml: publicação automática da pasta web/ no GitHub Pages.

## Executar localmente

Abra web/index.html em um navegador moderno. O tema acompanha a preferência do sistema por padrão; o seletor permite escolher Sistema, Claro ou Escuro.

## Publicar no GitHub Pages

1. Abra Settings → Pages no repositório.
2. Em Build and deployment → Source, selecione GitHub Actions.
3. O workflow publica automaticamente o conteúdo de web/ sempre que houver push para main.

## Android

Consulte ANDROID.md para dependências, tema, componentes Compose, acessibilidade e convenções. Copie android/src/main/java/org/minimalui para o módulo Android consumidor e ajuste o package. Este repositório não é um aplicativo Android completo nem uma biblioteca publicada no Maven.

## Créditos

Consulte [CREDITOS.md](CREDITOS.md) para ver as referências de design, tecnologias utilizadas e atribuições de terceiros.

## Licença

MIT. Consulte LICENSE.
