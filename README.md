# Minimal UI

Um design system monocromático, minimalista e responsivo, inspirado nos princípios do Material Design 3 Expressive, com foco em simplicidade, consistência, acessibilidade e usabilidade.

## Prévia

**[Abrir a interface publicada](https://souhelin.github.io/minimal-ui/)**

A demonstração usa a implementação real em web/, com seletor de tema claro, escuro e automático. Os controles, diálogos, menus, abas, validação, paginação e feedback possuem interações demonstrativas.

## Como usar no seu projeto

Consulte o **[Guia de uso — Minimal UI](GUIA-DE-USO.md)** para aprender a copiar e integrar os componentes Web ou Android, aplicar os temas, reutilizar os tokens e adaptar as interações ao seu aplicativo.

## Estrutura

- `web/`: demonstração Web em HTML, CSS e JavaScript, sem framework.
- `android/`: biblioteca Android Gradle com Kotlin, Jetpack Compose e Material 3.
- `tests/web/`: testes de integridade estrutural e contraste dos tokens.
- `ANDROID.md`: requisitos, compilação, testes e integração Android.
- `GUIA-DE-USO.md`: instruções práticas para usuários aplicarem o Minimal em seus projetos.
- `TOKENS.md`: tokens primitivos, semânticos, elevação, forma e movimento.
- `ROADMAP.md`: escopo e estado de implementação.
- `PERFORMANCE.md`: estratégia, orçamento de arquivos e auditoria Lighthouse.
- `AGENTS.md`: regras obrigatórias para agentes e contribuidores.
- `.github/workflows/pages.yml`: publicação automática da pasta `web/` no GitHub Pages.
- `.github/workflows/quality.yml`: verificações, testes e orçamento de arquivos Web.
- `.github/workflows/performance.yml`: auditoria Lighthouse CI da demonstração Web.
- `.github/workflows/android.yml`: build, lint e testes instrumentados da biblioteca Android.

## Executar a Web localmente

Abra `web/index.html` em um navegador moderno. O tema acompanha a preferência do sistema por padrão; o seletor permite escolher Sistema, Claro ou Escuro.

Para executar as verificações Web, use Node.js 22 ou superior:

~~~bash
npm run check:web
npm run check:budget
npm run test:web
~~~

Consulte [PERFORMANCE.md](PERFORMANCE.md) para executar o Lighthouse localmente e entender os limites de tamanho dos arquivos.

## Compilar e testar Android

Requisitos: JDK 17, Android SDK Platform 35 e Gradle 8.9.

~~~bash
gradle -p android assembleDebug
gradle -p android lintDebug
gradle -p android testDebugUnitTest
gradle -p android compileDebugAndroidTestKotlin
~~~

Para testes instrumentados, conecte um emulador e execute `gradle -p android connectedDebugAndroidTest`. Consulte [ANDROID.md](ANDROID.md) para integrar a biblioteca a outro projeto.

## Publicar no GitHub Pages

1. Abra Settings → Pages no repositório.
2. Em Build and deployment → Source, selecione GitHub Actions.
3. O workflow publica automaticamente o conteúdo de `web/` sempre que houver push para `main`.

## Créditos

Consulte [CREDITOS.md](CREDITOS.md) para ver as referências de design, tecnologias utilizadas e atribuições de terceiros.

## Licença

MIT. Consulte [LICENSE](LICENSE).
