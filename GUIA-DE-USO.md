# Guia de uso — Minimal UI

Este guia explica como aproveitar o Minimal UI em um site, aplicativo Web ou aplicativo Android. Ele é voltado a quem vai **usar e adaptar o design system em um projeto próprio**, não a quem vai desenvolver o repositório.

## 1. Antes de começar: entenda o que o repositório oferece

O Minimal UI fornece duas bases de implementação:

- **Web:** HTML, CSS e JavaScript sem framework, localizados em `web/`.
- **Android:** componentes em Kotlin com Jetpack Compose e Material 3, localizados em `android/src/main/java/org/minimalui/`.

A página publicada é uma demonstração/documentação interativa. Ela permite conhecer e testar os padrões, mas não é um pacote npm ou CDN.

Escolha a implementação correspondente à plataforma. Não misture classes CSS da Web com componentes Compose do Android.

## 2. Como usar no seu projeto Web

### Opção A — Usar a demonstração como ponto de partida

É a forma mais simples para começar:

1. Abra o repositório e baixe o ZIP ou faça um fork.
2. Copie a pasta `web/` para o seu projeto.
3. Abra `web/index.html` no navegador ou sirva a pasta com um servidor local.
4. Use a página como referência para localizar o componente de que precisa.
5. Remova as seções de demonstração que não serão usadas e adapte o conteúdo ao seu produto.

Mantenha os arquivos relacionados juntos:

~~~text
web/
├── index.html
├── tokens.css
├── components.css
├── patterns.css
├── theme.js
├── icons.js
└── components.js
~~~

O HTML importa esses arquivos por caminhos relativos. Se você mudar a localização deles, atualize os caminhos no `<head>` do HTML.

### Opção B — Integrar somente os estilos e componentes necessários

Se já existe um site, não substitua toda a estrutura dele. Faça a integração por etapas:

1. Copie `tokens.css` e `components.css` para a pasta de estilos do seu projeto.
2. Importe primeiro os tokens e depois os estilos dos componentes.
3. Copie para seu HTML somente a marcação do componente desejado.
4. Reutilize as classes existentes, como `button`, `button-filled`, `card`, `input`, `tabs` e `alert`.
5. Quando o componente depender de interação, adapte também o trecho correspondente de `components.js`.
6. Carregue `theme.js` se quiser utilizar o seletor e o comportamento de tema já fornecidos.

Exemplo mínimo de carregamento:

~~~html
<link rel="stylesheet" href="./styles/tokens.css">
<link rel="stylesheet" href="./styles/components.css">
<link rel="stylesheet" href="./styles/patterns.css">
<script defer src="./scripts/theme.js"></script>
<script defer src="./scripts/icons.js"></script>
<script defer src="./scripts/components.js"></script>
~~~

Ajuste os caminhos para a estrutura real do seu projeto. Não inclua `components.js` sem conferir se o HTML contém os elementos e IDs esperados por esse script: a demonstração atual usa seletores específicos, como `#dialog-open`, `#demo-form` e `#menu-trigger`. Para uma integração parcial, copie apenas os handlers necessários ou adapte-os aos elementos da sua aplicação.

### Exemplo: botão e cartão

~~~html
<article class="card">
  <h2>Configurações</h2>
  <p>Gerencie as preferências do aplicativo.</p>
  <button class="button button-filled" type="button">
    Salvar alterações
  </button>
</article>
~~~

Esse exemplo usa classes já definidas em `components.css`. O botão recebe a aparência do Minimal, mas a ação de salvar ainda precisa ser implementada pelo seu aplicativo.

### Como aplicar o tema

O Minimal Web oferece três opções:

- `system`: acompanha `prefers-color-scheme`.
- `light`: força o tema claro.
- `dark`: força o tema escuro.

Para habilitar o seletor, use um controle com o atributo `data-theme-picker`:

~~~html
<label for="theme">Tema</label>
<select id="theme" data-theme-picker>
  <option value="system">Sistema</option>
  <option value="light">Claro</option>
  <option value="dark">Escuro</option>
</select>
~~~

O arquivo `theme.js` aplica o atributo data-theme e salva as preferências localmente. A escolha de modo (system, light, dark) é independente da paleta (system, monochrome, blue, violet, green, orange, rose ou custom).

Para definir a identidade antes do carregamento do tema:
~~~html
<script>
  window.MinimalUIThemeConfig = {
    mode: "system",
    palette: "custom",
    seedColor: "#3867D6"
  };
</script>
<script defer src="./scripts/theme.js"></script>
~~~

Para alterar depois de carregar:
~~~js
MinimalUITheme.setTheme({
  mode: "dark",
  palette: "blue"
});
~~~

A Web usa AccentColor se o navegador disponibilizar essa cor do sistema. Navegadores não expõem universalmente os tons extraídos do papel de parede Android; para wallpaper dinâmico, use o módulo Android. A cor personalizada é um fallback explícito para a identidade do produto.

### Como personalizar sem descaracterizar

- Use as variáveis CSS existentes em `tokens.css`, como `--bg`, `--surface`, `--text`, `--border`, `--space-4` e `--radius-lg`.
- Prefira os tokens semânticos a valores hexadecimais fixos.
- Para mudar a identidade global, ajuste os tokens; não sobrescreva dezenas de componentes individualmente.
- Preserve os estados `:hover`, `:active`, `:focus-visible` e `:disabled`.
- Verifique os temas claro e escuro depois de cada personalização.

Consulte [TOKENS.md](TOKENS.md) para ver as escalas e regras completas.

### Como usar os ícones e padrões Web

Carregue icons.js antes do script da página. O conjunto disponibiliza SVGs com traço arredondado e sem fonte ou dependência externa:
~~~js
const icon = MinimalIcons.create("sparkles", {
  size: 24,
  label: "Assistente"
});
document.querySelector("#assistant-icon").replaceChildren(icon);
~~~

Para usar os padrões visuais de IA e navegador, importe também `patterns.css` e adapte a marcação de referência em `web/index.html`. Esses exemplos são somente apresentação: não enviam prompts, geram respostas, abrem URLs, criam abas nem mantêm histórico. O aplicativo consumidor implementa estado, callbacks, modelo de IA, backend, validação, segurança, engine de navegação, rede, abas, favoritos e histórico. Não inclua `components.js` da página demonstrativa esperando obter essas funcionalidades; ele não implementa lógica para esses padrões.

## 3. Como usar no seu aplicativo Android

O código Android é uma biblioteca Gradle independente baseada em Jetpack Compose e Material 3. Ela pode ser compilada e incluída como módulo em um aplicativo existente; não é um APK nem um aplicativo final. Consulte [ANDROID.md](ANDROID.md) para requisitos, comandos de build, testes e integração.

### Preparar o projeto

1. Abra um projeto Android que já use Jetpack Compose.
2. Inclua a pasta `android/` como módulo Gradle no `settings.gradle.kts` do projeto consumidor.
3. Adicione `implementation(project(":minimal-ui"))` ao bloco `dependencies` do módulo do aplicativo.
4. Sincronize o Gradle e utilize os componentes do pacote `org.minimalui.components`.
5. Implemente no aplicativo consumidor o estado, a navegação e as ações de negócio. Se optar por copiar os fontes em vez de incluir o módulo, alinhe as dependências Compose ao BOM do aplicativo.

Dependências de referência, a serem alinhadas com as versões do seu projeto:

~~~kotlin
implementation(platform("androidx.compose:compose-bom:<versao-estavel>"))
implementation("androidx.compose.material3:material3")
implementation("androidx.activity:activity-compose")
implementation("androidx.compose.foundation:foundation")
~~~

Não copie versões aleatórias de dependências: use uma versão estável e compatível com o restante do projeto.

### Aplicar o tema

O tema está em `android/src/main/java/org/minimalui/theme/MinimalTheme.kt`. Envolva o conteúdo principal do app com `MinimalTheme`:

~~~kotlin
@Composable
fun App() {
    val darkTheme = isSystemInDarkTheme()

    MinimalTheme(darkTheme = darkTheme) {
        AppContent()
    }
}
~~~

Adicione o import de `androidx.compose.foundation.isSystemInDarkTheme`. Se o aplicativo tiver uma preferência própria de aparência, use o estado dessa preferência no parâmetro darkTheme. Para ativar cores do papel de parede no Android 12+, deixe dynamicColor = true (padrão); para definir uma cor própria use MinimalTheme(seedColor = Color(0xFF3867D6), dynamicColor = false). Para controle total, passe um ColorScheme próprio em colorScheme.

### Componentes disponíveis no código Android

Os componentes ficam no pacote `org.minimalui.components`:

| Componente | Uso |
|---|---|
| `MinimalPrimaryButton` | Ação principal |
| `MinimalOutlinedButton` | Ação secundária contornada |
| `MinimalTextField` | Campo de texto controlado |
| `MinimalCard` | Cartão de conteúdo |
| `MinimalDialog` | Diálogo de confirmação |
| `MinimalBottomSheet` | Painel inferior modal |
| `MinimalDropdownMenu` | Menu suspenso |
| `MinimalTabs` | Abas |
| `MinimalNavigationBar` | Navegação inferior |
| `MinimalRadioOption` | Opção de seleção |
| `MinimalSlider` | Controle deslizante |
| `MinimalTooltip` | Ajuda contextual |
| `MinimalSnackbarHost` | Área de mensagens snackbar |
| `MinimalLoading` | Indicador de carregamento |
| `MinimalSkeleton` | Placeholder de carregamento |
| `MinimalList` | Lista com LazyColumn |
| `MinimalPagination` | Controles de paginação |
| `MinimalAiComposer` | Componente visual controlado para composição de prompt; `onSend` é callback do consumidor |
| `MinimalBrowserAddressBar` | Componente visual controlado; `onSubmit` e `onReload` são callbacks do consumidor |
| `MinimalIcon` | Renderização de qualquer ImageVector |

Exemplo de uso:

O campo também aceita `enabled`, `isError` e `singleLine` para representar estados de carregamento, validação e campos multilinha. O estado e as regras de validação continuam pertencendo ao aplicativo consumidor.

~~~kotlin
@Composable
fun SettingsScreen() {
    var name by remember { mutableStateOf("") }

    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        MinimalCard {
            Text("Perfil")
            MinimalTextField(
                value = name,
                onValueChange = { name = it },
                label = "Nome",
                enabled = true,
                isError = false,
                singleLine = true
            )
            MinimalPrimaryButton(
                text = "Salvar",
                onClick = { /* conecte à ação do seu app */ },
                enabled = name.isNotBlank()
            )
        }
    }
}
~~~

Inclua os imports de Compose e de `org.minimalui.components.*` necessários. O exemplo demonstra estado local de interface; persistência, validação de negócio e salvamento devem ser implementados no aplicativo consumidor.

Para os componentes avançados, confira os parâmetros diretamente em `MinimalAdvancedComponents.kt`. Alguns recursos podem exigir estado controlado pelo aplicativo, por exemplo `expanded`, `selectedIndex`, `currentPage` e `SnackbarHostState`.

## 4. Regras para aplicar corretamente

1. **Escolha a plataforma certa.** Web usa HTML/CSS/JS; Android usa Kotlin/Compose.
2. **Use a demonstração como referência, não como integração automática.** Copie e adapte o que precisa.
3. **Não presuma que existe instalação via npm, CDN ou Maven.** Atualmente o repositório não publica esses pacotes.
4. **Mantenha os tokens.** Evite criar cores, espaçamentos, raios e sombras arbitrários.
5. **Use estado real.** Um controle visual não implementa sozinho salvamento, autenticação, navegação, rede ou banco de dados.
6. **Adapte as ações ao seu produto.** Troque textos e dados demonstrativos por conteúdo real e conecte callbacks às funções da aplicação.
7. **Preserve acessibilidade.** Mantenha rótulos, foco visível, semântica, navegação por teclado/TalkBack e mensagens de erro compreensíveis.
8. **Teste os dois temas.** Confira contraste e legibilidade em claro e escuro.
9. **Teste telas diferentes.** Verifique celular, tablet e desktop na Web; verifique tamanhos de tela e escalas de fonte no Android.
10. **Não copie arquivos sem entender dependências.** Um componente pode depender de tokens, estilos, IDs, scripts ou estado.
11. **Não trate exemplos como backend.** Listas e paginação da demonstração usam dados locais; dados remotos precisam da integração da sua aplicação.
12. **Valide no projeto real.** O módulo Android deste repositório possui configuração Gradle própria e é validado pelo workflow de CI. Ainda assim, teste a integração no aplicativo consumidor, pois compatibilidade de versões, tema e comportamento final dependem dele.

## 5. Onde encontrar cada informação

- [README.md](README.md): visão geral, estrutura e publicação.
- [TOKENS.md](TOKENS.md): cores, espaçamento, forma, movimento e elevação.
- [ANDROID.md](ANDROID.md): dependências, tema e orientação Android.
- [ROADMAP.md](ROADMAP.md): escopo e limitações conhecidas.
- [Demonstração Web](https://souhelin.github.io/minimal-ui/): exemplos visuais e interações.
- [Código-fonte no GitHub](https://github.com/souhelin/minimal-ui): arquivos para copiar e adaptar.

## 6. Checklist antes de considerar a integração pronta

- [ ] Os arquivos e dependências necessários foram incluídos.
- [ ] Os caminhos de CSS e JavaScript estão corretos.
- [ ] Os controles executam as ações reais do aplicativo.
- [ ] O tema claro, escuro e, quando usado, sistema funciona.
- [ ] A interface funciona em diferentes tamanhos de tela.
- [ ] Teclado, TalkBack/leitor de tela, foco e rótulos foram verificados.
- [ ] Não há erros no console Web ou na compilação Android.
- [ ] Os dados de demonstração foram substituídos ou identificados corretamente.

**Resumo:** use o Minimal UI como base visual e de componentes. Copie os arquivos da plataforma correspondente, reutilize os tokens e padrões existentes e implemente no seu próprio aplicativo a lógica que pertence ao produto.
