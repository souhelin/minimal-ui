# Minimal UI

Um design system monocromático, minimalista e responsivo, inspirado nos princípios do Material Design 3 Expressive, com foco em simplicidade, consistência, acessibilidade e usabilidade.

## Prévia

**[Abrir a interface publicada](https://souhelin.github.io/minimal-ui/)**

A demonstração usa a implementação real em `web/`, com seletor de tema claro, escuro e automático.

## Estrutura

- `web/`: demonstração web em HTML, CSS e JavaScript, sem framework.
- `android/`: tema e componentes de exemplo para Jetpack Compose / Material 3.
- `TOKENS.md`: cores, espaçamentos, formas, movimento e regras de acessibilidade.
- `.github/workflows/pages.yml`: publicação automática da pasta `web/` no GitHub Pages.

## Executar localmente

Abra `web/index.html` em um navegador. O tema acompanha a preferência do sistema por padrão; o seletor permite escolher Sistema, Claro ou Escuro.

## Publicar no GitHub Pages

1. Abra **Settings → Pages** no repositório.
2. Em **Build and deployment → Source**, selecione **GitHub Actions**.
3. O workflow publica automaticamente o conteúdo de `web/` sempre que houver um push para `main`. Também é possível executá-lo manualmente pela aba **Actions**.

## Android

Copie os arquivos Kotlin para o módulo do aplicativo e ajuste o package name. Dependências necessárias no módulo:

```kotlin
implementation("androidx.compose.material3:material3")
implementation("androidx.activity:activity-compose")
```

Use uma versão estável do Compose BOM compatível com o projeto. Este repositório é uma base reutilizável, não um aplicativo Android completo nem uma biblioteca publicada.

## Licença

MIT. Consulte [LICENSE](LICENSE).
