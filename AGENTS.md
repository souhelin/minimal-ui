REGRAS DE DESENVOLVIMENTO — MINIMAL OPEN SOURCE UI

Estas regras são obrigatórias para qualquer IA, agente ou desenvolvedor que modificar, atualizar, corrigir ou expandir o projeto Minimal.

1. IDENTIDADE DO PROJETO

- O Minimal deve preservar sua identidade visual minimalista.
- A interface deve ser moderna, limpa, organizada e intuitiva.
- Evite elementos visuais desnecessários.
- Priorize simplicidade, consistência e facilidade de utilização.
- Não transforme o projeto em uma interface visualmente poluída.
- Utilize o Google Material Design 3 Expressive como referência de design.

2. PRESERVAÇÃO DO DESIGN SYSTEM

- Respeite as cores, tipografia, espaçamentos, ícones, bordas e componentes existentes.
- Não crie novos padrões visuais sem necessidade.
- Reutilize componentes já existentes.
- Mantenha consistência entre todas as páginas.
- Respeite os temas claro e escuro.
- Não substitua a identidade visual original por outro estilo.

3. ANTES DE MODIFICAR O CÓDIGO

A IA deve:

1. Analisar a estrutura completa do projeto.
2. Identificar as tecnologias utilizadas.
3. Examinar os componentes existentes.
4. Entender como os arquivos se relacionam.
5. Identificar possíveis dependências e limitações.
6. Planejar as alterações antes de executá-las.

Nunca modifique arquivos sem compreender sua finalidade.

4. REGRAS DE IMPLEMENTAÇÃO

- Escreva código organizado, legível e reutilizável.
- Evite duplicação de código.
- Não remova funcionalidades existentes sem autorização.
- Não substitua implementações funcionais por versões incompletas.
- Não utilize soluções temporárias como resultado final.
- Não deixe botões sem funcionamento.
- Não deixe links quebrados.
- Não utilize dados fictícios como se fossem dados reais.
- Evite dependências desnecessárias.

5. FUNCIONALIDADE OBRIGATÓRIA

Toda funcionalidade implementada deve funcionar de verdade.

A IA deve verificar:

- Botões e ações.
- Navegação entre páginas.
- Menus e configurações.
- Formulários.
- Responsividade.
- Persistência de dados, quando necessária.
- Tema claro e escuro.
- Integrações e dependências.

Não considere uma funcionalidade concluída apenas porque sua aparência está pronta.

6. RESPONSIVIDADE

A interface deve funcionar corretamente em:

- Smartphones Android.
- Smartphones iOS.
- Tablets.
- Notebooks.
- Computadores desktop.

Priorize a experiência mobile sem prejudicar a versão desktop.

Não permita:

- Elementos cortados.
- Textos sobrepostos.
- Barras de rolagem horizontais desnecessárias.
- Botões inacessíveis.
- Componentes fora da tela.

7. ACESSIBILIDADE E USABILIDADE

- Utilize ícones compreensíveis.
- Mantenha contraste adequado.
- Utilize nomes acessíveis nos controles.
- Respeite áreas de toque adequadas.
- Não dependa exclusivamente das cores para transmitir informações.
- Garanta uma navegação intuitiva.

8. SEGURANÇA

- Nunca exponha senhas, tokens ou chaves de API.
- Não introduza dependências suspeitas.
- Valide entradas de usuários.
- Evite vulnerabilidades conhecidas.
- Não execute comandos destrutivos sem necessidade.
- Preserve informações e arquivos importantes.
- Não desative mecanismos de segurança para contornar erros.

9. TESTES E CORREÇÕES

Depois de qualquer modificação:

1. Execute os testes disponíveis.
2. Verifique erros de compilação.
3. Verifique erros no console.
4. Teste as funcionalidades afetadas.
5. Verifique os temas claro e escuro.
6. Confira a interface em diferentes tamanhos de tela.
7. Corrija os problemas encontrados.

Não declare que o problema foi resolvido sem verificar o resultado.

10. REGRAS PARA MODIFICAÇÕES

- Faça alterações pequenas e controladas sempre que possível.
- Preserve tudo que já funciona.
- Não reescreva o projeto inteiro para corrigir um problema isolado.
- Não altere arquivos sem relação com a tarefa.
- Não remova recursos para simplificar a implementação.
- Explique alterações importantes.
- Informe limitações que não puderem ser resolvidas.

11. CONCLUSÃO DAS TAREFAS

Uma tarefa só pode ser considerada concluída quando:

- A implementação estiver realizada.
- O código estiver integrado corretamente.
- Os testes relevantes forem executados.
- Os erros introduzidos forem corrigidos.
- A interface preservar a identidade Minimal.
- As funcionalidades afetadas estiverem verificadas.

Se algo não funcionar, continue investigando e corrigindo enquanto houver uma solução viável.

12. PRINCÍPIO FUNDAMENTAL

O Minimal deve continuar sendo Minimal.

Toda alteração deve respeitar três princípios:

1. SIMPLICIDADE — menos elementos desnecessários.
2. CONSISTÊNCIA — uma identidade visual em todo o projeto.
3. FUNCIONALIDADE — tudo que aparece na interface deve cumprir sua finalidade.

A IA não deve modificar o projeto apenas para demonstrar criatividade. Toda mudança precisa ter uma justificativa técnica, funcional ou de usabilidade.

REGRA FINAL: Preserve o que funciona, melhore o que precisa melhorar e nunca comprometa a identidade do Minimal.


13. ROADMAP E CONTROLE DE PENDÊNCIAS

- Consulte `ROADMAP.md` antes de iniciar trabalhos de expansão do design system.
- Use o roadmap como fonte oficial do que está pendente.
- Não marque um item como concluído apenas porque existe um layout, mockup, exemplo estático ou implementação parcial.
- Antes de concluir um item, implemente, integre e teste seu comportamento nas plataformas aplicáveis.
- Atualize o `ROADMAP.md` no mesmo conjunto de alterações que concluir ou alterar o escopo de um item.
- Preserve os itens ainda pendentes; não os remova para dar aparência de progresso.
