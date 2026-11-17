# CLAUDE.md — Regras de desenvolvimento do Minimal

## 1. Identidade do projeto

O Minimal é um projeto open source de hobby e um design system inspirado no Material Design 3 Expressive.

Seu objetivo é oferecer uma biblioteca de componentes pequena, moderna, consistente, reutilizável, acessível e funcional.

O projeto possui duas plataformas:

- **Web:** HTML, CSS e JavaScript.
- **Android:** Kotlin, Jetpack Compose e Material 3.

O Minimal não deve se transformar em um framework complexo ou em uma aplicação com funcionalidades desnecessárias.

## 2. Regra principal

Antes de qualquer alteração:

1. Analise a estrutura atual do repositório.
2. Leia o README.md, ROADMAP.md, AGENTS.md e demais instruções existentes.
3. Identifique como os componentes estão implementados.
4. Verifique as tecnologias e dependências já utilizadas.
5. Entenda o impacto da alteração nas duas plataformas.
6. Planeje a implementação antes de modificar arquivos.

Nunca implemente algo sem compreender a arquitetura existente.

## 3. Arquitetura e tecnologias

Respeite obrigatoriamente as tecnologias estabelecidas:

### Web
- HTML semântico.
- CSS organizado e reutilizável.
- JavaScript moderno.
- Design responsivo.
- Componentes consistentes.
- Tokens de design centralizados.

### Android
- Kotlin.
- Jetpack Compose.
- Material 3.
- Arquitetura compatível com o projeto existente.
- Componentes reutilizáveis.
- Estados declarativos.

Não introduza frameworks, bibliotecas ou linguagens adicionais sem necessidade técnica comprovada.

Não migre tecnologias apenas por preferência pessoal.

## 4. Filosofia de desenvolvimento

Toda implementação deve priorizar:

- Simplicidade.
- Performance.
- Baixo consumo de recursos.
- Código limpo.
- Manutenibilidade.
- Reutilização.
- Acessibilidade.
- Consistência visual.
- Compatibilidade.
- Estabilidade.

Evite:
- Código duplicado.
- Dependências desnecessárias.
- Abstrações excessivas.
- Complexidade artificial.
- Componentes gigantes.
- Soluções temporárias sem justificativa.
- Otimizações prematuras.

Prefira soluções simples que resolvam corretamente o problema.

## 5. Componentes e design system

Todos os componentes devem respeitar os princípios do Minimal.

Requisitos:

- Aparência consistente entre componentes.
- Uso dos tokens oficiais.
- Estados de interação bem definidos.
- Responsividade.
- Acessibilidade.
- API previsível.
- Propriedades configuráveis quando necessário.
- Comportamento funcional.

Considere os estados:

- Default.
- Hover.
- Focus.
- Pressed.
- Disabled.
- Loading.

Não crie componentes visualmente diferentes sem uma justificativa de design.

Evite duplicar componentes que já existem.

## 6. Paridade entre plataformas

A Web e o Android devem seguir a mesma identidade visual e os mesmos princípios de design.

Sempre que implementar ou modificar um componente:

1. Verifique se existe equivalente na outra plataforma.
2. Preserve a consistência dos tokens.
3. Mantenha comportamentos equivalentes quando aplicável.
4. Documente diferenças inevitáveis entre plataformas.

Não force implementações idênticas quando as plataformas possuem limitações técnicas diferentes.

## 7. Performance e otimização

A performance é uma prioridade do Minimal.

Ao desenvolver:

- Evite processamento desnecessário.
- Reduza operações repetitivas.
- Evite recomposições desnecessárias no Compose.
- Evite manipulações excessivas do DOM.
- Prefira CSS eficiente.
- Reduza dependências.
- Evite arquivos e recursos desnecessariamente grandes.
- Preserve a fluidez das animações.

Não sacrifique acessibilidade, estabilidade ou legibilidade em nome de pequenas melhorias de desempenho.

Toda otimização importante deve ter justificativa técnica.

## 8. Acessibilidade

A acessibilidade não é opcional.

Verifique:

- HTML semântico.
- Navegação por teclado.
- Indicadores de foco.
- Contraste adequado, buscando WCAG AA.
- Atributos ARIA quando necessários.
- Compatibilidade com leitores de tela.
- TalkBack no Android.
- Descrições acessíveis.
- Redução de movimento quando aplicável.

Não considere um componente concluído apenas porque funciona visualmente.

## 9. Testes e validação

Antes de finalizar qualquer implementação:

1. Execute os testes relacionados às alterações.
2. Execute o build da plataforma afetada.
3. Verifique erros de compilação.
4. Verifique avisos relevantes.
5. Execute lint e verificações de qualidade disponíveis.
6. Teste os comportamentos modificados.
7. Verifique se outras funcionalidades continuam funcionando.

Para Android, não confunda falhas de conexão com o emulador com erros de compilação.

Entretanto, não declare o Android validado sem evidências de que o build foi concluído com sucesso.

Não ignore testes que falharam.

Não invente resultados de testes.

## 10. Segurança das alterações

- Não modifique arquivos fora do escopo sem justificativa.
- Não remova funcionalidades existentes sem autorização ou necessidade comprovada.
- Não sobrescreva configurações importantes.
- Não introduza credenciais ou segredos no repositório.
- Não altere workflows de CI sem compreender suas consequências.
- Não faça commits diretamente na main.
- Prefira alterações isoladas e revisáveis em Pull Requests.

Nunca considere uma alteração segura apenas porque o código compila.

## 11. Documentação e roadmap

Mantenha a documentação sincronizada com a implementação.

Quando concluir uma funcionalidade:

- Atualize o README quando necessário.
- Atualize o ROADMAP.md.
- Documente novos componentes.
- Registre mudanças relevantes.
- Informe limitações conhecidas.
- Não marque funcionalidades incompletas como concluídas.

Não remova pendências do roadmap sem comprovar que foram resolvidas.

## 12. Pull Requests

Todo Pull Request deve apresentar:

- Objetivo da alteração.
- Problema resolvido.
- Arquivos modificados.
- Solução implementada.
- Testes executados.
- Resultado dos testes.
- Limitações conhecidas.
- Possíveis impactos em outras áreas.

Os Pull Requests devem ser pequenos, organizados e fáceis de revisar.

Não misture refatorações sem relação com a tarefa.

## 13. Colaboração com o ChatGPT

O ChatGPT pode atuar como arquiteto, analista e revisor independente.

Durante a colaboração:

- Respeite as decisões arquiteturais documentadas.
- Não ignore problemas identificados durante revisões.
- Não repita alterações já implementadas.
- Analise os comentários do revisor antes de propor novas mudanças.
- Apresente justificativas técnicas para discordâncias.

Não aceite automaticamente sugestões de outra IA sem avaliar sua necessidade e compatibilidade com o projeto.

## 14. Proibições

É proibido:

- Criar backend sem necessidade e autorização.
- Adicionar autenticação ou banco de dados.
- Transformar o Minimal em uma aplicação completa.
- Adicionar dependências sem justificativa.
- Fazer migrações tecnológicas desnecessárias.
- Remover componentes existentes arbitrariamente.
- Ignorar erros de compilação.
- Declarar testes aprovados sem executá-los.
- Marcar tarefas incompletas como concluídas.
- Introduzir complexidade que contrarie a filosofia do projeto.

## 15. Critérios de conclusão

Uma tarefa somente pode ser considerada concluída quando:

- A implementação está funcional.
- O código respeita a arquitetura.
- Os testes aplicáveis foram executados.
- Não existem erros conhecidos introduzidos pela alteração.
- A acessibilidade foi considerada.
- A documentação foi atualizada quando necessário.
- O impacto nas outras plataformas foi avaliado.

Se algum requisito não puder ser validado, informe claramente.

## 16. Regra final

O objetivo não é adicionar o máximo de funcionalidades ao Minimal.

O objetivo é construir um design system pequeno, eficiente, consistente, acessível e confiável.

**Qualidade acima de quantidade. Simplicidade acima de complexidade. Estabilidade acima de velocidade.**
