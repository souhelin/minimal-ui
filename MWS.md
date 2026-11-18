# Minimal Writing System (MWS)

**Status:** especificação inicial (v0.1.0-draft)  
**Projeto:** Minimal UI  
**Escopo:** linguagem, conteúdo, tipografia e apresentação textual  
**Identidade:** híbrida — minimalista por padrão, expressiva quando o contexto justificar  
**Plataformas-alvo:** Web, Android e implementações futuras

> O MWS define padrões para escrever e apresentar conteúdo em interfaces. Ele não é um modelo de linguagem, tradutor, corretor gramatical, mecanismo de geração de texto ou fonte tipográfica.

## 1. Propósito

O Minimal Writing System estabelece uma linguagem de interface consistente, legível, acessível, localizável e adaptável. Seu objetivo é reduzir decisões repetitivas e inconsistências sem impor uma personalidade rígida a todos os produtos.

O sistema combina duas disciplinas relacionadas, mas independentes:

- **Minimal Writing:** regras editoriais, voz, tom, gramática, microcopy, estrutura, conversação, localização e linguagem inclusiva.
- **Minimal Typography:** famílias tipográficas escolhidas pelo produto, escala, pesos, métricas, hierarquia, espaçamento e comportamento responsivo.

O MWS é um subsistema do Minimal UI, não um substituto do design system. Pode ser adotado parcialmente por projetos que não utilizam os componentes visuais do Minimal UI.

## 2. Princípios normativos

As palavras **MUST**, **MUST NOT**, **SHOULD**, **SHOULD NOT** e **MAY** indicam, respectivamente, requisito obrigatório, proibição, recomendação, recomendação negativa e opção.

1. **Clareza antes da concisão:** não omitir contexto necessário para encurtar uma frase.
2. **Naturalidade:** escrever como uma pessoa informativa e respeitosa, sem simular intimidade.
3. **Consistência:** usar os mesmos termos para as mesmas funções em toda a experiência.
4. **Contexto:** adaptar tom, comprimento e detalhe à tarefa, ao risco e à superfície.
5. **Acessibilidade:** não depender apenas de cor, posição, ícone ou estilo para transmitir significado.
6. **Localização:** não assumir que idioma, pluralização, comprimento ou convenções são universais.
7. **Honestidade:** não prometer ações, estados ou capacidades que o produto não executou.
8. **Flexibilidade controlada:** permitir identidade de marca sem quebrar a compreensão básica.

## 3. Arquitetura

~~~text
Minimal UI
└── Minimal Writing System (MWS)
    ├── Minimal Writing
    │   ├── Voice & Tone
    │   ├── Grammar & Style
    │   ├── Content Structure
    │   ├── Microcopy
    │   ├── Conversational Writing
    │   ├── Error Communication
    │   ├── Inclusive Language
    │   └── Localization
    ├── Minimal Typography
    │   ├── Font Roles
    │   ├── Type Scale
    │   ├── Weights & Metrics
    │   ├── Text Spacing
    │   └── Responsive Behavior
    ├── Tokens
    ├── Profiles
    ├── Platform Mappings
    ├── Validation Guidance
    └── Documentation & Examples
~~~

### 3.1 Separação de responsabilidades

- Writing decide **o que dizer e como formular**.
- Typography decide **como o texto é visualmente apresentado**.
- Minimal UI Components decidem **onde e em qual estrutura o texto aparece**.
- O aplicativo consumidor decide **conteúdo de negócio, estado, validação, localização final e comportamento**.

Nenhuma camada deve exigir que o aplicativo use uma implementação específica de IA ou backend.

## 4. Identidade híbrida: voz e tom

### 4.1 Voz central

A voz padrão do MWS é:
- clara;
- objetiva;
- natural;
- respeitosa;
- segura sem ser autoritária;
- expressiva sem excesso;
- neutra o suficiente para diferentes marcas.

### 4.2 Tom contextual

A voz permanece reconhecível; o tom muda conforme o contexto.

| Contexto | Tom recomendado | Diretriz |
|---|---|---|
| Navegação | Neutro e direto | Identificar destinos e ações |
| Configuração | Instrutivo | Explicar efeito e consequência |
| Sucesso | Breve e factual | Confirmar somente o que ocorreu |
| Erro | Calmo e útil | Explicar o problema e próximo passo |
| Aviso | Claro e proporcional | Explicitar risco ou consequência |
| IA conversacional | Natural e colaborativo | Distinguir fato, inferência e incerteza |
| Onboarding | Acolhedor e objetivo | Ensinar sem sobrecarregar |
| Contexto técnico | Preciso | Preservar termos e detalhes técnicos |

Não usar humor, entusiasmo ou linguagem promocional em erros críticos, avisos de segurança ou situações sensíveis, salvo quando o produto justificar e a clareza permanecer intacta.

### 4.3 Formalidade e personalidade

O perfil base usa formalidade moderada e vocabulário comum. Produtos podem definir formalidade, terminologia e expressividade próprios, mas não devem remover instruções essenciais, mensagens de risco ou informação necessária para consentimento.

## 5. Grammar & Style

### 5.1 Regras gerais

- Preferir voz ativa quando o agente da ação for relevante.
- Usar frases diretas e ordem sintática familiar.
- Preferir palavras concretas a abstrações vagas.
- Evitar jargões quando houver alternativa simples; preservar jargão necessário em interfaces técnicas.
- Evitar redundância, intensificadores vazios e pontuação decorativa.
- Não usar caixa alta integral para dar ênfase.
- Usar pontuação convencional do idioma e evitar múltiplas exclamações.
- Manter capitalização consistente em títulos, rótulos e navegação.
- Não terminar rótulos de botões com ponto final.
- Usar reticências tipográficas ou padrão consistente apenas para ações em andamento, quando isso não substituir um indicador acessível.

### 5.2 Ações

Começar rótulos de ação com verbo específico sempre que possível.

| Preferir | Evitar |
|---|---|
| Salvar alterações | Clique aqui |
| Criar conta | Enviar |
| Baixar arquivo | Fazer download agora!!! |
| Tentar novamente | OK, tentar de novo |
| Remover acesso | Excluir (se o efeito for revogação) |

A ação deve descrever o resultado real. Ações irreversíveis ou destrutivas devem identificar claramente o objeto e, quando necessário, a consequência.

### 5.3 Terminologia

Cada produto SHOULD manter um glossário com:
- termo preferido;
- definição;
- contexto;
- termos proibidos ou legados;
- traduções aprovadas;
- observações de acessibilidade.

Não alternar sinônimos apenas para evitar repetição quando isso puder sugerir funções diferentes.

## 6. Content Structure

### 6.1 Hierarquia

- Um título principal por página ou região semântica.
- Subtítulos devem descrever o conteúdo que organizam.
- Parágrafos devem agrupar uma ideia principal.
- Listas devem ser usadas para conjuntos de itens, etapas ou alternativas.
- Instruções devem aparecer próximas ao controle a que se referem.
- Conteúdo essencial não deve existir apenas em tooltip, placeholder ou texto que desaparece.

### 6.2 Densidade

O MWS não define limite universal de palavras. O conteúdo deve ser tão curto quanto possível e tão completo quanto necessário. Textos de risco, consentimento, privacidade, pagamento e exclusão exigem contexto suficiente, mesmo que ocupem mais espaço.

### 6.3 Microcopy por componente

| Elemento | Regra |
|---|---|
| Button | Verbo + resultado esperado |
| Text field label | Nome do dado solicitado |
| Placeholder | Exemplo opcional; nunca substitui label |
| Helper text | Explica formato, finalidade ou requisito |
| Validation | Identifica campo e como corrigir |
| Empty state | Explica ausência e próximo passo |
| Tooltip | Informação complementar, não essencial |
| Dialog title | Identifica decisão ou assunto |
| Snackbar | Confirma evento ou oferece ação reversível |
| Tab | Nome curto e reconhecível da seção |
| Navigation item | Nome do destino, não instrução longa |

### 6.4 Estados

- **Default:** instrução ou conteúdo esperado.
- **Loading:** informar a atividade sem afirmar conclusão.
- **Success:** confirmar somente após resultado confirmado.
- **Error:** explicar o que falhou, impacto e ação possível.
- **Disabled:** quando necessário, explicar por que a ação está indisponível.
- **Empty:** distinguir ausência de dados, ausência de resultados e falha de carregamento.

## 7. Error Communication

Mensagens de erro SHOULD seguir esta estrutura, quando aplicável:

1. O que não foi possível fazer.
2. A causa conhecida, sem especulação.
3. O impacto ou estado atual.
4. Uma ação concreta para recuperação.

Exemplo:
- Preferir: "Não foi possível enviar o arquivo. Verifique sua conexão e tente novamente."
- Evitar: "Erro 0x003" como única explicação.

Códigos técnicos podem ser apresentados como detalhe secundário para suporte. Nunca culpar ou constranger a pessoa. Não alegar perda, salvamento ou recuperação de dados sem confirmação do sistema.

## 8. Conversational Writing e interfaces de IA

O MWS fornece padrões editoriais, não comportamento de modelo.

Assistentes e experiências conversacionais SHOULD:
- responder primeiro ao objetivo principal;
- organizar explicações longas em seções;
- distinguir fatos confirmados, hipóteses e recomendações;
- declarar incerteza quando relevante;
- não inventar ações, fontes, resultados ou capacidades;
- preservar contexto necessário sem repetir informações;
- solicitar esclarecimento somente quando a ambiguidade impedir uma resposta segura ou útil;
- adaptar densidade e vocabulário ao contexto;
- evitar antropomorfismo enganoso e intimidade não solicitada.

Mensagens de geração, streaming, falha, interrupção e conclusão devem corresponder ao estado real fornecido pela aplicação. O MWS não implementa geração, streaming nem armazenamento.

## 9. Inclusive Language & Accessibility

- Preferir linguagem respeitosa, não discriminatória e centrada na tarefa.
- Evitar expressões que infantilizem, culpem ou presumam características da pessoa.
- Não presumir gênero, parentesco, nacionalidade ou capacidade.
- Respeitar as convenções de linguagem inclusiva do idioma e do produto, sem sacrificar clareza.
- Usar texto visível e nomes acessíveis coerentes.
- Não usar apenas cor, peso, posição ou ícone para indicar erro, sucesso ou estado.
- Não transmitir instruções essenciais exclusivamente por tooltip ou conteúdo temporário.
- Permitir zoom e escalonamento de fonte sem corte de conteúdo.
- Preservar ordem de leitura semântica e hierarquia de títulos.
- Usar abreviações somente quando conhecidas pelo público e explicá-las na primeira ocorrência quando necessário.

## 10. Localization & Internationalization

O MWS é independente de idioma. A especificação não presume que regras do português sejam universais.

Implementações SHOULD:
- manter strings separadas do código;
- usar identificadores estáveis para mensagens;
- suportar pluralização e interpolação próprias do locale;
- evitar concatenação de fragmentos de frases;
- considerar variações de data, hora, número, moeda, endereço e unidades;
- suportar expansão de texto e escrita RTL quando aplicável;
- definir fallback de idioma;
- revisar traduções em contexto de interface;
- não usar bandeiras como substituto universal de idioma.

O conteúdo deve ser validado em cada idioma suportado. Tradução literal não garante naturalidade ou equivalência funcional.

## 11. Minimal Typography

### 11.1 Princípios

- Legibilidade prevalece sobre ornamentação.
- Família tipográfica é configurável pelo produto.
- A escala deve manter hierarquia previsível.
- O layout deve tolerar textos maiores e diferentes idiomas.
- Peso, tamanho, cor e espaçamento não devem ser os únicos meios de transmitir significado.
- O sistema não distribui nem exige uma fonte proprietária.

### 11.2 Papéis tipográficos

| Token | Tamanho de referência | Papel |
|---|---:|---|
| display | 40 px | Destaques e apresentação |
| headline | 32 px | Título principal |
| title | 24 px | Título de seção |
| subtitle | 20 px | Subtítulo |
| body-large | 18 px | Texto de leitura destacado |
| body | 16 px | Texto padrão |
| body-small | 14 px | Texto auxiliar |
| label | 12 px | Rótulos e metadados |

Esses valores são referências iniciais para Web em escala CSS de referência, não dimensões universais. Android deve mapear para unidades sp e respeitar fontScale. Web deve permitir zoom, preferências de fonte e reflow. Projetos podem ajustar a escala desde que preservem hierarquia, legibilidade e acessibilidade.

### 11.3 Pesos semânticos

| Token | Peso de referência | Uso |
|---|---:|---|
| regular | 400 | Leitura contínua |
| medium | 500 | Ênfase leve e controles |
| semibold | 600 | Títulos e ênfase |
| bold | 700 | Destaques pontuais |

A disponibilidade real depende da família tipográfica. Não sintetizar pesos sem avaliar o resultado. Evitar excesso de pesos na mesma tela.

### 11.4 Line-height e comprimento

Referências iniciais:
- títulos: 1.15–1.3;
- corpo: 1.45–1.65;
- labels: 1.2–1.4.

Comprimento de linha recomendado para leitura contínua: aproximadamente 45–75 caracteres, ajustado ao idioma, tamanho e dispositivo. Não aplicar largura fixa que cause overflow em telas pequenas.

### 11.5 Espaçamento tipográfico

Tracking e espaçamento de parágrafo devem ser configuráveis por papel e plataforma. Não aplicar letter-spacing negativo indiscriminadamente. Não justificar blocos de texto por padrão. Não usar altura fixa em componentes que contenham conteúdo textual variável.

## 12. Tokens do MWS

Os tokens são semânticos e devem ser mapeados para a implementação da plataforma. Eles não substituem os tokens de cor, forma, espaçamento e movimento já definidos pelo Minimal UI.

### 12.1 Tokens de conteúdo

- `writing.voice`: `hybrid`
- `writing.tone.default`: `neutral-clear`
- `writing.formality.default`: `moderate`
- `writing.conciseness`: `balanced`
- `writing.locale`: definido pelo aplicativo consumidor
- `writing.glossary`: glossário do produto

### 12.2 Tokens tipográficos

- `type.family.sans`
- `type.family.serif`
- `type.family.mono`
- `type.size.display`
- `type.size.headline`
- `type.size.title`
- `type.size.subtitle`
- `type.size.body-large`
- `type.size.body`
- `type.size.body-small`
- `type.size.label`
- `type.weight.regular`
- `type.weight.medium`
- `type.weight.semibold`
- `type.weight.bold`
- `type.line-height.heading`
- `type.line-height.body`
- `type.line-length.reading`

Os valores concretos devem ser definidos por tema/plataforma e não duplicados em cada componente. Cor tipográfica deve continuar vindo dos tokens semânticos existentes, como `--text` e `--text-secondary`.

## 13. Perfis de escrita

Perfis são presets de configuração sobre o núcleo comum. Não são sistemas independentes nem justificam divergência de acessibilidade.

| Perfil | Uso | Característica |
|---|---|---|
| Core | Interfaces gerais | Equilibrado e neutro |
| Expressive | Onboarding e experiências de marca | Mais energia, sem perder clareza |
| Technical | Ferramentas e ambientes de desenvolvimento | Termos precisos e detalhe |
| Conversational | Chat e assistentes | Natural, contextual e transparente |
| Compact | Superfícies densas e mobile | Conciso, preservando informação |

O perfil Compact não pode remover avisos, consequências, instruções ou rótulos essenciais. O perfil Expressive não pode transformar mensagens de erro em publicidade.

## 14. Integração com Minimal UI

### 14.1 Web

A implementação Web preserva a arquitetura atual sem framework e separa tokens tipográficos de componentes.

Mapeamento implementado:
- CSS custom properties para tokens tipográficos;
- classes utilitárias opcionais para papéis de texto;
- estilos de componentes consumindo os tokens;
- atributos/classes para perfis somente se houver necessidade comprovada;
- strings de conteúdo permanecem sob responsabilidade da aplicação.

Exemplo de uso da API CSS implementada:

~~~css
:root {
  --mws-font-sans: system-ui, sans-serif;
  --mws-type-display: 2.5rem;
  --mws-type-headline: 2rem;
  --mws-type-title: 1.5rem;
  --mws-type-body: 1rem;
  --mws-type-label: 0.75rem;
  --mws-line-body: 1.55;
}
~~~

### 14.2 Android

A implementação futura deve mapear papéis para `Typography` do Material 3/Compose, sem criar um segundo sistema de tema concorrente. Usar sp e respeitar escala de fonte do sistema.

Exemplo conceitual (não representa API já implementada):

~~~kotlin
val mwsTypography = Typography(
    bodyLarge = TextStyle(fontSize = 18.sp, lineHeight = 27.sp),
    bodyMedium = TextStyle(fontSize = 16.sp, lineHeight = 24.sp),
    labelSmall = TextStyle(fontSize = 12.sp, lineHeight = 16.sp)
)
~~~

### 14.3 Componentes consumidores

Componentes Minimal UI devem usar papéis tipográficos sem codificar conteúdo específico de negócio. Componentes de texto não devem assumir que o conteúdo está em português ou que sempre cabe em uma linha.

## 15. API e formato de configuração

A API de tipografia está implementada nos mapeamentos Web e Android. As regras editoriais permanecem uma especificação para orientar pessoas e equipes; não existe motor automático de reescrita, tradução ou validação semântica.

Uma futura configuração pode seguir este formato conceitual:

~~~json
{
  "profile": "core",
  "voice": "hybrid",
  "locale": "pt-BR",
  "typography": {
    "fontFamily": "system-ui",
    "scale": "default"
  }
}
~~~

A configuração não executa tradução, correção gramatical ou geração automática. Cada consumidor decide como carregar e aplicar os valores.

## 16. Validação e critérios de qualidade

Antes de aprovar textos e tipografia, verificar:

### Conteúdo
- A ação ou informação está explícita?
- O texto corresponde ao estado real?
- A terminologia está consistente?
- Há informação essencial omitida?
- O tom é apropriado à consequência?
- O conteúdo foi revisado no idioma e contexto finais?

### Tipografia
- A hierarquia é clara?
- O texto continua legível em Light e Dark?
- O zoom e o escalonamento de fonte funcionam?
- Há corte, sobreposição ou overflow?
- Textos longos e traduções maiores são suportados?
- A largura de leitura é adequada?

### Acessibilidade
- Títulos e regiões têm semântica?
- Labels não dependem de placeholder?
- Leitores de tela recebem ordem e nomes coerentes?
- Contraste atende às diretrizes WCAG aplicáveis?
- Conteúdo essencial não depende de cor, ícone ou animação?

## 17. Testes planejados

A implementação deve adicionar validações automatizadas proporcionais ao código introduzido:
- integridade e unicidade dos tokens;
- presença dos papéis tipográficos essenciais;
- valores válidos e fallbacks;
- ausência de alturas fixas em padrões de texto variável, quando verificável;
- exemplos de strings para estados comuns;
- regressão visual em tamanhos de viewport e escala de fonte;
- revisão manual com leitor de tela e TalkBack.

Testes automatizados de estilo não conseguem determinar sozinhos se um texto é claro, respeitoso ou adequado ao contexto. Revisão editorial humana permanece necessária.

## 18. Versionamento e governança

- O MWS deve acompanhar o versionamento do Minimal UI inicialmente.
- Alterações incompatíveis em nomes ou semântica de tokens exigem documentação de migração.
- Novos perfis devem resolver um caso de uso real e reutilizável.
- Alterações de voz devem incluir exemplos antes/depois e justificativa.
- Traduções devem ser revisadas por idioma; não presumir equivalência automática.
- Não adicionar dependências ou fontes externas sem avaliar licença, desempenho, privacidade e manutenção.

## 19. Limites explícitos

O MWS não:
- gera, corrige ou traduz textos automaticamente;
- contém um modelo de IA;
- impõe uma fonte específica;
- substitui revisão editorial, jurídica ou de acessibilidade;
- define conteúdo de negócio do aplicativo;
- implementa localização em runtime;
- substitui os componentes e tokens existentes do Minimal UI.

## 20. Plano de implementação

### Fase 1 — Especificação
- [x] Definir escopo, identidade híbrida e separação Writing/Typography.
- [x] Definir princípios editoriais e perfis.
- [x] Definir escala tipográfica de referência e diretrizes de acessibilidade.
- [x] Definir arquitetura de integração Web/Android.
- [ ] Revisão da especificação pelos mantenedores.

### Fase 2 — Tokens e mapeamentos
- [x] Auditar tipografia atual em Web e Android.
- [x] Consolidar tokens tipográficos sem duplicar escalas existentes.
- [x] Mapear papéis para CSS e Compose.
- [ ] Validar Light, Dark, zoom e fontScale.

### Fase 3 — Padrões de conteúdo
- [ ] Documentar microcopy por componente.
- [ ] Criar glossário e exemplos de estados.
- [ ] Definir diretrizes de localização.
- [ ] Revisar padrões de IA, erros, avisos e formulários.

### Fase 4 — Implementação e validação
- [x] Implementar tokens e papéis tipográficos Web e Android.
- [x] Adicionar testes automatizados de integridade da escala Web e Compose.
- [ ] Realizar revisão editorial e auditoria de acessibilidade.
- [x] Atualizar guia de uso, tokens e roadmap para refletir a implementação.

## 21. Referências do próprio Minimal UI

- `TOKENS.md`: cores semânticas, escalas, temas e acessibilidade.
- `AGENTS.md`: arquitetura, processo de contribuição e critérios de qualidade.
- `GUIA-DE-USO.md`: integração e responsabilidade do aplicativo consumidor.
- `ROADMAP.md`: estado real e pendências do projeto.

O MWS deve respeitar esses documentos. Em caso de divergência, corrigir a documentação e a implementação em conjunto, sem afirmar capacidades que ainda não existem.


## 22. Estado da implementação tipográfica (v0.1.0)

A escala de tipografia está integrada ao código do Minimal UI:

- Web: tokens CSS `--mws-*` em `web/tokens.css` e classes semânticas `.mws-*` em `web/components.css`. Os tamanhos usam `rem` e a família padrão continua herdando `--font-family`.
- Android: `MinimalTypography` em `org.minimalui.theme` fornece os estilos Material 3 em `sp`; `MinimalTheme` aplica essa tipografia por padrão e aceita substituição pelo parâmetro `typography`.
- Testes: `tests/web/typography.test.mjs` verifica tokens e classes; `MinimalTypographyTest` verifica tamanhos e line-heights do Compose.

A implementação não modifica automaticamente textos nem aplica um tom editorial em runtime. A escala ainda precisa de execução de testes, revisão visual em zoom/fontScale e auditoria manual de acessibilidade antes de ser considerada validada.
