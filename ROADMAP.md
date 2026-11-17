# Roadmap — Minimal UI

Acompanhe o estado real da implementação. A demonstração Web, os componentes Compose e uma biblioteca pronta para distribuição são entregas diferentes; não marque uma plataforma como concluída apenas porque existe um exemplo visual.

## 1. Componentes Web
- [x] Botões, campos, cartões e controles demonstrativos.
- [x] Dialogs, bottom sheet, menus, tabs e navegação demonstrativa.
- [x] Snackbar, alertas, loading, skeleton e paginação local.
- [x] Validação demonstrativa de formulário.
- [x] Navegação por teclado nas tabs e no menu (setas, Home, End e Escape).
- [ ] Testes automatizados de comportamento em navegador real.
- [ ] Verificação de acessibilidade com leitor de tela e auditoria automatizada.

## 2. Componentes Android (Jetpack Compose)
- [x] Botões, campo de texto e cartão.
- [x] Dialog, bottom sheet, dropdown, tabs e navegação inferior.
- [x] Radio option, slider, tooltip, snackbar, loading, skeleton estático, lista e paginação.
- [x] Estado enabled e erro configurável no campo de texto.
- [x] Linha inteira da opção de rádio selecionável e estados de índice protegidos.
- [ ] Criar projeto Gradle independente ou módulo Android de biblioteca.
- [ ] Definir versões compatíveis e validar compilação em CI.
- [ ] Adicionar testes Compose e testes de acessibilidade/TalkBack.
- [ ] Implementar shimmer opcional no skeleton respeitando redução de movimento.

## 3. Design tokens e temas
- [x] Tokens primitivos e semânticos documentados.
- [x] Tema Web claro, escuro e sistema.
- [x] Tema Android claro e escuro com Material 3.
- [ ] Unificar e validar formalmente todos os valores Web/Android.
- [ ] Automatizar testes de contraste WCAG AA para os pares de cores relevantes.
- [ ] Verificar visualmente os estados de foco, erro e desabilitado em ambos os temas.

## 4. Layouts e templates
- [x] Dashboard, configurações, perfil, formulários, listas e estados de referência.
- [ ] Exemplos de integração isolados por componente e sem dependência dos IDs da demonstração.

## 5. Documentação e distribuição
- [x] Guia de uso Web e Android.
- [x] Convenções e regras para agentes/contribuidores.
- [x] Créditos e licença.
- [ ] Definir API pública estável e política de compatibilidade.
- [ ] Preparar versionamento e publicação de pacote, se houver demanda real.
- [ ] Documentar testes executados e versões usadas em cada release.

## Limitações atuais
- A página GitHub Pages é uma documentação interativa, não uma biblioteca npm.
- O Android ainda é código-fonte Compose para integração em um app consumidor; não há build Gradle independente neste repositório.
- O skeleton Android atual é estático, sem shimmer.
- A paginação e os dados Web são demonstrativos e locais.
- A checagem de sintaxe JavaScript não substitui testes de interface, acessibilidade ou compilação Android.

## Ordem recomendada
1. Manter os componentes e estados consistentes entre plataformas.
2. Criar e validar o módulo Gradle Android.
3. Automatizar testes Web e Compose.
4. Auditar acessibilidade e contraste.
5. Só então estabilizar uma versão 1.0 e considerar distribuição como pacote.
