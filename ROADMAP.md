# Roadmap — Minimal UI

O roadmap distingue implementação de demonstração, biblioteca reutilizável e validação automatizada. Um item só deve ser marcado como concluído quando houver implementação verificável.

## 1. Web
- [x] Demonstração responsiva em HTML, CSS e JavaScript sem framework.
- [x] Botões, campos, seleção, menus, tabs, dialogs e bottom sheet.
- [x] Snackbar, alertas, loading, skeleton e paginação demonstrativa.
- [x] Validação de formulário e estados disabled.
- [x] Navegação por teclado no menu e nas tabs.
- [x] Renderização segura dos itens dinâmicos com APIs DOM e textContent.
- [x] Testes automatizados de integridade HTML, referências, IDs e contraste dos tokens.
- [ ] Testes automatizados de interação em navegador real e diferentes viewports.
- [ ] Auditoria manual com leitores de tela.

## 2. Android — Jetpack Compose
- [x] Módulo Gradle independente com namespace e manifesto Android.
- [x] Botões, campo de texto, cartão, dialog, bottom sheet e dropdown.
- [x] Tabs, navegação inferior, rádio, slider, tooltip, snackbar, loading, lista e paginação.
- [x] Estados enabled e erro no campo de texto.
- [x] Validação dos índices selecionados e do intervalo do slider.
- [x] Opção de rádio selecionável pela linha inteira.
- [x] Skeleton com shimmer opcional e respeito à configuração de animações do sistema.
- [x] Testes instrumentados Compose para rádio, campo de texto, tabs e navegação.
- [x] Workflow de build, lint e testes instrumentados no GitHub Actions.
- [x] Confirmar build e testes verdes no CI antes de declarar release estável (workflow Android aprovado no PR).
- [ ] Testar TalkBack, teclado físico, tamanhos de fonte e dispositivos reais.

## 3. Design tokens e temas
- [x] Tokens primitivos e semânticos documentados.
- [x] Tema Web Sistema, Claro e Escuro.
- [x] Tema Android Material 3 claro e escuro alinhado aos tokens monocromáticos.
- [x] Testes automatizados de contraste para texto, texto secundário, foco, erro e superfícies invertidas.
- [ ] Verificação visual completa dos estados e componentes em ambos os temas.

## 4. Layouts e exemplos
- [x] Templates demonstrativos de dashboard, configurações, perfil, formulários e listas.
- [ ] Exemplos Android de integração por componente e aplicativo de showcase.
- [ ] Exemplos Web independentes da estrutura de IDs da página demonstrativa.

## 5. Distribuição e estabilidade
- [x] Documentação de uso, integração e compilação Android.
- [x] Créditos, licença e regras para contribuidores.
- [ ] Definir API pública estável e política de compatibilidade.
- [ ] Adicionar publicação versionada da biblioteca Android (Maven), caso haja demanda.
- [ ] Preparar versão 1.0 após testes em dispositivos e revisão de acessibilidade.

## 6. Performance Web
- [x] Orçamento inicial de tamanho para CSS e JavaScript principais.
- [x] Verificação do orçamento integrada ao workflow de qualidade.
- [x] Configuração do Lighthouse CI com três execuções e relatórios como artefatos do workflow.
- [ ] Coletar e revisar a linha de base real do Lighthouse no GitHub Actions.
- [ ] Ajustar metas de performance após observar resultados repetidos.
- [ ] Avaliar Rust/WebAssembly somente se um perfil apontar processamento computacional relevante.

## Limites conhecidos
- A página GitHub Pages é uma documentação interativa; não é um pacote npm.
- O módulo Android é uma biblioteca de componentes, não um aplicativo ou APK.
- A demonstração Web usa dados locais e não inclui backend, autenticação ou persistência de produto.
- Os testes automatizados não substituem validação manual com tecnologias assistivas e dispositivos reais.

## Ordem recomendada
1. Confirmar os workflows Web e Android aprovados.
2. Corrigir qualquer falha de compilação ou teste encontrada pelo CI.
3. Fazer auditoria manual de acessibilidade e responsividade.
4. Criar exemplos independentes de integração.
5. Estabilizar a API e preparar uma versão 1.0.
