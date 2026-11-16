# Guia Android — Minimal UI

O pacote Android é uma base de componentes para Jetpack Compose + Material 3, não um aplicativo completo nem uma biblioteca publicada.

## Instalação

Adicione dependências compatíveis com o Compose BOM usado pelo aplicativo:

~~~kotlin
implementation(platform("androidx.compose:compose-bom:<versao-estavel>"))
implementation("androidx.compose.material3:material3")
implementation("androidx.activity:activity-compose")
implementation("androidx.compose.foundation:foundation")
~~~

Copie android/src/main/java/org/minimalui para o módulo e ajuste o package se necessário.

## Tema claro, escuro e sistema

~~~kotlin
@Composable
fun App() {
    val dark = isSystemInDarkTheme()
    MinimalTheme(darkTheme = dark) {
        // Conteúdo do aplicativo
    }
}
~~~

Para escolha manual, passe o valor do estado da aplicação ao tema em vez de sempre usar a preferência do sistema.

## Mapeamento de componentes Material 3

| Necessidade | Componente Compose |
|---|---|
| Dialog | AlertDialog ou Dialog |
| Bottom sheet | ModalBottomSheet |
| Menus | DropdownMenu / DropdownMenuItem |
| Tabs | TabRow / Tab |
| Navegação inferior | NavigationBar / NavigationBarItem |
| Radio buttons | RadioButton |
| Sliders | Slider |
| Tooltips | TooltipBox / PlainTooltip |
| Snackbars | SnackbarHost / SnackbarHostState |
| Alertas | AlertDialog |
| Listas | LazyColumn / LazyRow |
| Paginação | Estado de página; Paging 3 para dados remotos |
| Loading | CircularProgressIndicator |
| Skeleton | Superfícies com shimmer e respeito à redução de movimento |

MinimalComponents.kt contém botões primário e contornado, campo de texto e cartão. Use estado controlado (value + onValueChange) e mantenha estado de negócio fora dos componentes visuais.

## Acessibilidade

- Forneça contentDescription para ícones que comunicam ações.
- Use Modifier.semantics quando a semântica padrão não for suficiente.
- Garanta navegação por teclado e TalkBack.
- Não comunique erro apenas por cor.
- Teste escalas de fonte e áreas de toque.
- Prefira componentes Material 3, que já oferecem semântica e comportamento de interação.

## Convenções

- Componentes públicos usam prefixo Minimal.
- Composables em PascalCase; parâmetros em camelCase.
- Estado controlado pelo chamador sempre que possível.
- Não mantenha estado de negócio dentro de componentes visuais.

## Limite de compilação

Este repositório não contém settings.gradle, build.gradle ou wrapper Gradle. Portanto, não é possível declarar um build Android independente como validado aqui. A compilação deve ser verificada em um aplicativo consumidor com versões de dependências definidas.
