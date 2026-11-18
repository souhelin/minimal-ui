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
- [x] Paletas Web monocromática, azul, violeta, verde, laranja, rosa e personalizada.
- [x] Detecção de AccentColor do sistema na Web quando exposta pelo navegador, com fallback neutro.
- [x] Tema Android Material 3 com paleta dinâmica do wallpaper no Android 12+, cor-semente do desenvolvedor e ColorScheme completo opcional.
- [x] Testes automatizados de contraste para tokens semânticos estáticos claros e escuros.
- [ ] Auditoria visual e contrastes automatizados das paletas dinâmicas geradas em navegador real.
- [ ] Verificar atualização da paleta de wallpaper em dispositivos Android 12+ e versões posteriores.
- [ ] Verificação visual completa dos estados e componentes em ambos os temas.
- [ ] Auditoria manual com leitores de tela, TalkBack e tamanhos de fonte.
- [x] Biblioteca Web de ícones vetoriais SVG com rótulos acessíveis e API de criação.
- [x] Wrapper Android MinimalIcon para ImageVector fornecido pelo aplicativo consumidor.
- [ ] Kit Android com 15 componentes visuais para navegador mobile (implementação adicionada; build e testes pendentes).
- [ ] Ampliação da biblioteca Web com novos ícones de acessibilidade, arquivos, mídia, dispositivos, navegação e produtividade (implementação adicionada; testes pendentes).
- [ ] Kit Android com 15 componentes visuais para navegador mobile (implementação adicionada; build e testes pendentes).
- [x] Padrão Web visual de chat IA e campo de composição (sem envio ou respostas simuladas).
- [x] Padrão Web visual de navegador mobile com endereço, atalhos e controles ilustrativos (sem navegação ou gerenciamento de abas).
- [x] Componentes Compose visuais MinimalAiComposer e MinimalBrowserAddressBar com callbacks controlados pelo aplicativo consumidor.
- [ ] Testes de interação em navegador real e viewports; validação de componentes em aplicativos consumidores.
- [ ] Validar os 15 estilos de barra de endereço Android em emulador, diferentes larguras e escala de fonte (API e teste instrumentado adicionados na branch de desenvolvimento).

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

## 6. Minimal Writing System (MWS)
- [x] Definir escopo integrado: Minimal Writing + Minimal Typography.
- [x] Definir identidade híbrida e princípios editoriais.
- [x] Documentar voz, tom, gramática, microcopy, erros, conversação, inclusão e localização.
- [x] Propor escala tipográfica e papéis semânticos multiplataforma.
- [x] Documentar arquitetura e limites de integração Web/Android.
- [ ] Revisar a especificação com mantenedores e validar a escala contra a tipografia atual.
- [x] Auditar estilos tipográficos existentes antes de implementar tokens.
- [x] Mapear tokens para CSS e Compose sem duplicar tokens ou temas.
- [ ] Criar exemplos de microcopy e glossário por idioma.
- [x] Adicionar testes automatizados para presença dos tokens e papéis tipográficos Web e escala padrão Compose.
- [ ] Executar e validar testes Web/Android, responsividade, zoom, fontScale e acessibilidade manual.

## 7. Performance Web
- [x] Orçamento inicial de tamanho para CSS e JavaScript principais.
- [x] Verificação do orçamento integrada ao workflow de qualidade.
- [x] Configuração do Lighthouse CI com três execuções e relatórios como artefatos do workflow.
- [x] Coletar e revisar a linha de base inicial do Lighthouse CI (3 execuções: performance 93/100, 100/100 e 100/100; acessibilidade 100/100 nas três; FCP/LCP de 906–908 ms; TBT de 333/0/2 ms; CLS 0). O pico isolado de TBT reforça manter as metas em modo de aviso até novas medições.
- [ ] Ajustar metas de performance após observar resultados repetidos.
- [ ] Avaliar Rust/WebAssembly somente se um perfil apontar processamento computacional relevante.

## Limites conhecidos
- A página GitHub Pages é uma documentação interativa; não é um pacote npm.
- O módulo Android é uma biblioteca de componentes, não um aplicativo ou APK.
- A demonstração Web usa dados locais e não inclui backend, autenticação ou persistência de produto. Os padrões de IA e navegador são referências visuais; não enviam prompts nem executam navegação.
- Os testes automatizados não substituem validação manual com tecnologias assistivas e dispositivos reais.

## Ordem recomendada
1. Confirmar os workflows Web e Android aprovados.
2. Corrigir qualquer falha de compilação ou teste encontrada pelo CI.
3. Fazer auditoria manual de acessibilidade e responsividade.
4. Criar exemplos independentes de integração.
5. Estabilizar a API e preparar uma versão 1.0.
