# Biblioteca Android — Minimal UI

O Minimal UI possui uma biblioteca Android independente baseada em Kotlin, Jetpack Compose e Material 3. O módulo fica nesta pasta (`android/`) e pode ser compilado separadamente da demonstração Web.

## Requisitos

- JDK 17.
- Android SDK Platform 35.
- Gradle 8.9 ou compatível.
- Android Studio recente com suporte a Kotlin 2.0 e Compose.

O projeto usa Android Gradle Plugin 8.7.3, Kotlin 2.0.21 e Compose BOM 2024.12.01. As versões estão centralizadas em `android/build.gradle.kts`.

## Compilar e testar

Na raiz do repositório:

~~~bash
gradle -p android assembleDebug
gradle -p android lintDebug
gradle -p android testDebugUnitTest
gradle -p android compileDebugAndroidTestKotlin
~~~

Para executar os testes instrumentados Compose em um emulador conectado:

~~~bash
gradle -p android connectedDebugAndroidTest
~~~

O workflow `.github/workflows/android.yml` executa build, lint, compilação dos testes e testes instrumentados em emulador no GitHub Actions.

## Integrar em outro aplicativo

É possível incluir a pasta `android/` como módulo Gradle no projeto consumidor. No `settings.gradle.kts` do aplicativo, configure o caminho local do módulo:

~~~kotlin
include(":minimal-ui")
project(":minimal-ui").projectDir = file("../minimal-ui/android")
~~~

No módulo do aplicativo:

~~~kotlin
dependencies {
    implementation(project(":minimal-ui"))
}
~~~

Ajuste o caminho relativo conforme a localização do repositório no seu projeto. O módulo expõe o namespace `org.minimalui` e o pacote de componentes `org.minimalui.components`.

## Temas automáticos, dinâmicos e personalizados

O MinimalTheme detecta Light/Dark do sistema por padrão e usa cores dinâmicas do papel de parede no Android 12+ quando disponíveis.

Tema automático com wallpaper:
~~~kotlin
@Composable
fun App() {
    MinimalTheme {
        AppContent()
    }
}
~~~

Paleta controlada pelo desenvolvedor (exemplo azul):
~~~kotlin
MinimalTheme(
    seedColor = Color(0xFF3867D6),
    dynamicColor = false
) {
    AppContent()
}
~~~

Desativar cores dinâmicas e manter a paleta neutra:
~~~kotlin
MinimalTheme(dynamicColor = false) {
    AppContent()
}
~~~

Para controle completo, passe um ColorScheme próprio em colorScheme. A precedência é colorScheme, depois seedColor, depois cores dinâmicas do Android e, por fim, os tokens neutros do Minimal. A preferência manual claro/escuro pode ser passada por darkTheme.

Adicione o import androidx.compose.ui.graphics.Color para os exemplos de paleta personalizada.

## Tipografia — Minimal Writing System

`MinimalTheme` aplica `MinimalTypography` por padrão. A escala usa unidades `sp`, portanto acompanha a preferência de tamanho de fonte do Android.

~~~kotlin
import org.minimalui.theme.MinimalTheme
import org.minimalui.theme.MinimalTypography

@Composable
fun App() {
    MinimalTheme {
        Text(
            text = "Título da seção",
            style = MaterialTheme.typography.titleLarge
        )
    }
}
~~~

Para personalizar a tipografia, passe uma instância Material 3 `Typography` pelo parâmetro `typography` de `MinimalTheme`. O padrão usa a família tipográfica do sistema; o MWS não distribui nem força uma fonte.

~~~kotlin
MinimalTheme(typography = myTypography) {
    AppContent()
}
~~~

## Componentes

- Básicos: `MinimalPrimaryButton`, `MinimalOutlinedButton`, `MinimalTextField` e `MinimalCard`.
- Avançados: `MinimalDialog`, `MinimalBottomSheet`, `MinimalDropdownMenu`, `MinimalTabs`, `MinimalNavigationBar`, `MinimalRadioOption`, `MinimalSlider`, `MinimalTooltip`, `MinimalSnackbarHost`, `MinimalLoading`, `MinimalSkeleton`, `MinimalList` e `MinimalPagination`.
- Padrões de produto: `MinimalAiComposer`, `MinimalBrowserAddressBar` e `MinimalIcon`.
- Navegador Android: 15 componentes visuais em `MinimalBrowserComponents.kt`, incluindo endereço, segurança, sugestões, atalhos, abas, histórico, favoritos, downloads, leitor, busca na página, compartilhamento e permissões.

### Campo de texto

O estado permanece controlado pelo aplicativo consumidor:

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

A linha inteira de `MinimalRadioOption` é selecionável. Mantenha a seleção no estado do chamador e use um grupo de opções com rótulos claros.

### Dialog

O callback `onConfirm` não fecha o diálogo automaticamente. O chamador controla a presença do composable, executa a ação e decide quando dispensá-lo.

### Skeleton

`MinimalSkeleton` oferece shimmer opcional. O efeito é desativado quando as animações do sistema Android estão desabilitadas ou quando `shimmer = false`:

~~~kotlin
MinimalSkeleton(lines = 3, shimmer = true)
~~~

### Padrões de IA, navegador e ícones

MinimalAiComposer é o campo de composição para uma tela de assistente. O aplicativo controla o texto e conecta onSend ao seu serviço de IA:

~~~kotlin
MinimalAiComposer(
    value = prompt,
    onValueChange = { prompt = it },
    onSend = { sendPrompt(prompt) }
)
~~~

MinimalBrowserAddressBar fornece o campo de endereço e callbacks para submissão e recarregamento. O aplicativo consumidor implementa validação de URL, histórico, abas e navegação:

~~~kotlin
MinimalBrowserAddressBar(
    value = address,
    onValueChange = { address = it },
    onSubmit = { url -> navigateTo(url) },
    onReload = { reloadPage() }
)
~~~

O kit de navegador expõe `MinimalBrowserStatusBar`, `MinimalBrowserAddressField`, `MinimalBrowserSecurityIndicator`, `MinimalBrowserSuggestion`, `MinimalBrowserShortcut`, `MinimalBrowserTabCounter`, `MinimalBrowserTabCard`, `MinimalBrowserNavigationBar`, `MinimalBrowserBookmarkItem`, `MinimalBrowserHistoryItem`, `MinimalBrowserDownloadItem`, `MinimalBrowserReaderControls`, `MinimalBrowserFindBar`, `MinimalBrowserShareActions` e `MinimalBrowserPermissionPrompt`. Todos recebem estado e callbacks do aplicativo consumidor; eles não executam navegação, rede ou permissões por conta própria.

MinimalIcon aceita qualquer ImageVector e herda a cor do conteúdo quando tint é omitido. Isso permite adotar um conjunto de ícones vetoriais escolhido pelo aplicativo sem adicionar uma dependência grande ao módulo Minimal.

## Acessibilidade

- Prefira os componentes Material 3 e suas semânticas nativas.
- Forneça rótulos descritivos para ações e ícones informativos.
- Teste navegação por teclado, TalkBack e escalas de fonte.
- Não comunique erro somente por cor.
- Verifique foco, estados selecionados, alvos de toque e contraste nos temas claro e escuro.
- Respeite a configuração do sistema que desabilita animações.

## Limites

O módulo é uma biblioteca de componentes, não um aplicativo final nem um APK. Não inclui navegação de produto, backend, autenticação, persistência ou regras de negócio. Esses elementos pertencem ao aplicativo que consome o design system.
