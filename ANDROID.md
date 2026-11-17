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

## Componentes disponíveis

- Básicos: `MinimalPrimaryButton`, `MinimalOutlinedButton`, `MinimalTextField` e `MinimalCard`.
- Avançados: `MinimalDialog`, `MinimalBottomSheet`, `MinimalDropdownMenu`, `MinimalTabs`, `MinimalNavigationBar`, `MinimalRadioOption`, `MinimalSlider`, `MinimalTooltip`, `MinimalSnackbarHost`, `MinimalLoading`, `MinimalSkeleton`, `MinimalList` e `MinimalPagination`.

### Campo de texto

`MinimalTextField` mantém o estado controlado pelo chamador e oferece configuração de disponibilidade, erro e linha única:

~~~kotlin
MinimalTextField(
    value = name,
    onValueChange = { name = it },
    label = "Nome",
    supportingText = if (hasError) "Confira o nome informado." else null,
    isError = hasError,
    enabled = !isSaving,
    singleLine = true
)
~~~

### Opção de rádio

A linha completa de `MinimalRadioOption` é selecionável. Use-a dentro de um grupo de opções e mantenha a seleção no estado do chamador.

### Dialog

O callback `onConfirm` não fecha o diálogo automaticamente. O chamador deve atualizar o estado que controla a presença do composable, permitindo executar a ação e decidir quando dispensá-lo.

### Skeleton

`MinimalSkeleton` é atualmente um placeholder estático. Não anuncia que existe animação shimmer. O shimmer permanece pendente até ser implementado com respeito às preferências de redução de movimento.

## Acessibilidade

- Forneça contentDescription para ícones que comunicam ações.
- Use Modifier.semantics quando a semântica padrão não for suficiente.
- Garanta navegação por teclado e TalkBack.
- Não comunique erro apenas por cor.
- Teste escalas de fonte e áreas de toque.
- Prefira componentes Material 3, que já oferecem semântica e comportamento de interação.
- Verifique se o foco e o estado selecionado são anunciados corretamente nos componentes personalizados.

## Convenções

- Componentes públicos usam prefixo Minimal.
- Composables em PascalCase; parâmetros em camelCase.
- Estado controlado pelo chamador sempre que possível.
- Não mantenha estado de negócio dentro de componentes visuais.

## Limite de compilação

Este repositório ainda não contém `settings.gradle`, `build.gradle` ou wrapper Gradle. Portanto, a compilação Android não está validada neste repositório. A compilação deve ser verificada em um aplicativo consumidor com versões de dependências definidas. A criação de um módulo Gradle independente continua pendente no roadmap.
