# AGENTS.md — Regras obrigatórias do Minimal UI

Este arquivo define como qualquer IA, agente ou pessoa deve analisar, implementar, corrigir, testar e documentar mudanças neste repositório. Leia-o antes de editar qualquer arquivo. Em caso de conflito, preserve a arquitetura e o comportamento existentes e explique a incompatibilidade antes de fazer uma mudança estrutural.

## 1. Identidade e objetivo

O Minimal UI é um design system open-source monocromático, minimalista e responsivo, inspirado nos princípios do Material Design 3 Expressive. Seus princípios, em ordem de importância, são:

1. **Simplicidade:** remover ruído, não remover recursos necessários.
2. **Consistência:** manter padrões visuais e comportamentais entre componentes e plataformas.
3. **Usabilidade:** tornar ações, estados e navegação compreensíveis.
4. **Acessibilidade:** permitir uso por teclado, leitores de tela e diferentes necessidades.
5. **Funcionalidade:** todo controle apresentado deve cumprir sua finalidade.

Não copie cegamente o Material 3 nem transforme o Minimal em uma réplica. Use-o como referência, preservando a identidade monocromática própria do projeto.

## 2. Leia a documentação antes de começar

Consulte os arquivos relevantes antes de planejar a alteração:

- `README.md`: propósito, estrutura, execução e publicação.
- `TOKENS.md`: escalas, tokens semânticos, elevação, forma e movimento.
- `ROADMAP.md`: escopo, progresso e limitações conhecidas.
- `ANDROID.md`: integração e convenções Android/Compose.
- `AGENTS.md`: estas regras obrigatórias.

Não presuma que a documentação está sempre atualizada: confira também a implementação real. Se encontrar divergência entre documentação e código, investigue e corrija a documentação no mesmo trabalho quando isso fizer parte do escopo.

## 3. Arquitetura atual — não presuma outra

### Web

A demonstração Web é implementada diretamente em HTML, CSS e JavaScript, sem framework:

- `web/index.html`: estrutura da página, exemplos e marcação dos componentes.
- `web/tokens.css`: tokens CSS, temas, escalas, elevação e valores semânticos.
- `web/components.css`: estilos e responsividade dos componentes.
- `web/theme.js`: seleção e aplicação dos temas Sistema, Claro e Escuro.
- `web/components.js`: comportamentos e interações da demonstração.

Regras:

- Preserve a arquitetura sem framework. Não introduza React, Vue, Angular, bibliotecas de componentes ou ferramentas de build sem solicitação explícita e justificativa técnica.
- Mantenha a separação entre estrutura, tokens/estilos, tema e comportamento.
- Carregue os arquivos na ordem correta e preserve os caminhos relativos usados pelo GitHub Pages.
- Não concentre toda a aplicação em um único arquivo nem duplique comportamentos já existentes.
- Evite dependências externas; se uma for realmente necessária, explique o motivo, o impacto, a licença e a manutenção.

### Android

A parte Android contém componentes e tema baseados em Jetpack Compose e Material 3. Este repositório **não é um aplicativo Android completo nem uma biblioteca publicada** e não possui, conforme a documentação atual, configuração Gradle independente validada.

- Consulte `ANDROID.md` e examine os arquivos Kotlin realmente existentes antes de editar.
- Não invente caminhos, classes, APIs ou dependências.
- Não declare que o Android compila sem executar a validação em um aplicativo consumidor configurado.
- Não misture código Android com a demonstração Web.

### GitHub Pages

A demonstração publicada usa a pasta `web/` e o workflow `.github/workflows/pages.yml`. Não altere o diretório publicado, os caminhos ou o workflow sem entender o impacto na publicação.

## 4. Design system e tokens

- Consulte `TOKENS.md` antes de criar ou alterar cores, espaçamentos, raios, elevação ou movimento.
- Use tokens semânticos nos componentes; não espalhe valores hexadecimais ou valores arbitrários pela interface.
- Preserve as duas camadas: tokens primitivos representam valores-base; tokens semânticos representam sua função.
- Não crie nomes concorrentes para tokens que já existem.
- Se um token novo for indispensável, defina-o de forma coerente em `TOKENS.md` e na implementação correspondente.
- Light, Dark e Sistema devem compartilhar os mesmos nomes semânticos; somente os valores mudam.
- O modo Sistema deve respeitar `prefers-color-scheme`; a escolha manual deve prevalecer sobre a preferência do sistema.
- Preserve a persistência da preferência de tema e trate falhas de armazenamento sem quebrar a interface.
- Não use cores como único meio de comunicar estado ou significado.
- Evite sombras, gradientes, animações e ornamentos que não tenham função clara.

Escalas atualmente documentadas: espaçamentos de 4 a 64 px; raios de 8, 12, 16, 24 e 999 px; movimento de 100, 200 e 300 ms. Não altere esses valores por preferência pessoal.

## 5. Regras para componentes e interações

- Antes de criar um componente, procure se já existe uma implementação ou padrão equivalente.
- Reutilize estilos, tokens e funções existentes.
- Mantenha estados visuais coerentes: padrão, hover, focus-visible, pressed, disabled, loading, erro e sucesso, conforme aplicável.
- Todo botão deve executar uma ação real e identificável.
- Todo link deve apontar para um destino válido e coerente; não use links vazios ou `#` como substituto de funcionalidade.
- Menus devem abrir, fechar, atualizar atributos de acessibilidade e responder ao teclado.
- Dialogs e sheets devem ter abertura e fechamento previsíveis, foco utilizável e comportamento de teclado adequado.
- Tabs devem atualizar o painel e permitir navegação por teclado.
- Formulários devem validar dados, informar o erro junto ao campo e não depender apenas de cor.
- Loading e skeleton devem representar carregamento real ou ser claramente identificados como demonstração.
- Snackbar, alertas e tooltips devem ser legíveis, temporários quando apropriado e não bloquear ações essenciais.
- Paginação deve atualizar os itens e o estado da página; não exiba controles que não funcionam.
- Não apresente dados demonstrativos como se fossem dados reais, persistidos ou provenientes de um servidor.

## 6. Temas e comportamento visual

- Teste Sistema, Claro e Escuro separadamente.
- Verifique contraste, bordas, superfícies elevadas, campos, overlays, skeletons, feedback e ícones em ambos os temas.
- Não fixe cores de tema diretamente em componentes.
- Evite flash de tema incorreto durante a inicialização.
- Não remova a preferência salva ao fazer refatorações.
- Respeite `prefers-reduced-motion`; nenhuma informação ou ação pode depender de animação.
- Não altere a tipografia ou a escala de espaçamento global sem avaliar todas as telas.

## 7. Responsividade

A Web deve funcionar em smartphones, tablets, notebooks e desktops.

- Comece verificando a experiência mobile e depois confira telas maiores.
- Use layouts flexíveis e grades que possam encolher sem transbordar.
- Verifique cabeçalho, navegação, cards, formulários, dialogs, sheets, listas e tabelas.
- Corrija conteúdo cortado, sobreposição, rolagem horizontal não intencional e controles fora da tela.
- Não esconda conteúdo ou funcionalidades importantes apenas para acomodar uma tela pequena.

## 8. Acessibilidade obrigatória

- Use HTML semântico e elementos nativos sempre que possível.
- Todo controle precisa de nome acessível; imagens informativas precisam de texto alternativo.
- Mantenha foco visível e ordem de tabulação lógica.
- Garanta operação por teclado, inclusive em menus, tabs e modais.
- Use atributos ARIA apenas quando necessários e com estados atualizados corretamente.
- Associe rótulos, instruções e mensagens de erro aos campos.
- Respeite zoom e ampliação de texto.
- Alvos de toque devem ter pelo menos 48 × 48 px quando possível, conforme TOKENS.md.
- Busque WCAG AA: contraste mínimo de 4.5:1 para texto normal e 3:1 para texto grande e elementos gráficos relevantes.
- Não remova semântica nativa para implementar controles personalizados sem necessidade.

## 9. Qualidade e segurança do código

- Escreva código legível, previsível, modular e compatível com as tecnologias já usadas.
- Evite duplicação, efeitos colaterais globais e manipulação insegura do DOM.
- Não use `innerHTML` com conteúdo controlado pelo usuário ou dados externos não sanitizados; prefira `textContent` e APIs seguras.
- Valide entradas e trate erros de forma explícita.
- Não adicione segredos, tokens, senhas ou chaves de API ao repositório.
- Não desative validações ou mecanismos de segurança para contornar falhas.
- Não execute comandos destrutivos nem apague arquivos sem necessidade e autorização.
- Não atualize dependências ou configurações sem avaliar compatibilidade e impacto.

## 10. Processo obrigatório antes de editar

1. Leia estas regras e os documentos relevantes.
2. Confira a árvore e os arquivos reais do repositório.
3. Identifique o comportamento atual e os pontos de entrada envolvidos.
4. Reproduza o problema, quando houver um bug.
5. Identifique a causa raiz; não aplique correções cosméticas para esconder sintomas.
6. Planeje a menor alteração que resolva o problema sem quebrar outras partes.
7. Identifique documentação, exemplos, tokens e testes que também precisarão ser atualizados.

Não edite arquivos apenas com base em nomes presumidos ou em trechos isolados.

## 11. Processo obrigatório depois de editar

Execute o que estiver disponível e seja pertinente:

- Verifique sintaxe e carregamento dos arquivos HTML, CSS e JavaScript alterados.
- Abra a demonstração e teste os fluxos afetados.
- Confira console e erros de carregamento, caminhos de arquivos e links.
- Teste temas Claro, Escuro e Sistema.
- Teste teclado, foco, estados disabled/loading e mensagens de validação.
- Confira pelo menos uma viewport mobile e uma desktop.
- Para Android, valide em um projeto consumidor com dependências definidas; se isso não for possível, declare explicitamente que não houve validação de build.
- Verifique se o GitHub Pages continua apontando para a pasta correta.
- Atualize documentação e exemplos afetados.

Não diga que testes passaram se não foram executados. Diferencie claramente: implementado, testado, validado em build e ainda não verificado.

## 12. Roadmap e documentação

- Consulte `ROADMAP.md` antes de expandir o design system.
- Não marque um item como concluído por existir apenas um mockup, layout estático, exemplo parcial ou orientação documental.
- Marque como concluído somente o que estiver implementado e verificado no escopo aplicável.
- Mantenha as limitações conhecidas explícitas.
- Atualize `ROADMAP.md`, `README.md`, `TOKENS.md` ou `ANDROID.md` quando a mudança afetar o conteúdo desses documentos.
- Não remova pendências para aparentar progresso.
- Exemplos de demonstração devem ser identificados como exemplos, não como componentes publicados ou integrações de produção.

## 13. Escopo e preservação

- Faça mudanças pequenas, focadas e revisáveis.
- Não reescreva o projeto inteiro para corrigir um defeito isolado.
- Não remova recursos existentes sem autorização explícita.
- Não altere arquivos sem relação com a tarefa.
- Preserve compatibilidade e caminhos públicos sempre que possível.
- Se uma solicitação exigir uma mudança estrutural, explique o impacto antes de executá-la.
- Se não for possível concluir algo, informe o que foi feito, o que falta e o motivo.

## 14. Critério de conclusão

Uma tarefa só está concluída quando:

- O comportamento solicitado foi implementado.
- A solução está integrada à arquitetura real do Minimal.
- Os fluxos afetados foram testados.
- Não foram introduzidas regressões conhecidas.
- Os temas, a responsividade e a acessibilidade foram considerados.
- A documentação e o roadmap refletem a situação real.
- As limitações e os testes não executados foram declarados.

## Princípio final

**Preserve o que funciona. Corrija a causa, não esconda o sintoma. Siga os tokens. Teste antes de declarar concluído. Mantenha o Minimal simples, consistente, acessível e funcional.**
