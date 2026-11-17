# Minimal Typography

Sistema tipográfico do Minimal UI. Os nomes das famílias abaixo são nomes de trabalho e devem passar por pesquisa de marca/nome antes de uma distribuição pública.

## Direção

A identidade é híbrida: estrutura minimalista, curvas cuidadosamente desenhadas e detalhes expressivos discretos. A prioridade é legibilidade em interfaces reais, não personalidade decorativa.

## Famílias planejadas

| Família | Papel | Estado |
|---|---|---|
| Minimal Sans | Interface, leitura e controles | Primeira família a desenhar |
| Minimal Display | Títulos e chamadas | Planejada; depende da Sans |
| Minimal Rounded | Contextos amigáveis e expressivos | Planejada; depende da Sans |
| Minimal Mono | Código, dados e identificadores | Planejada; depende da Sans |

Não há arquivos de fonte originais nesta etapa. O CSS e o Android usam fallbacks nativos até que os glifos sejam desenhados, compilados e validados. Não declare uma família como pronta nem distribua arquivos de outra fonte renomeados.

## Escopo recomendado da primeira versão

1. Desenhar Minimal Sans como família principal.
2. Começar por Regular e Medium, validar a direção e expandir para pesos adicionais.
3. Planejar eixo variável `wght` somente quando os masters e a interpolação forem aprovados.
4. Adicionar `opsz` apenas se houver desenhos/ajustes ópticos específicos e testes que comprovem sua utilidade.
5. Considerar `wdth` somente se a família tiver masters de largura reais; não simular um eixo variável sem desenhos correspondentes.
6. Expandir cobertura Latin Basic e Latin-1 Supplement, incluindo diacríticos usados em português. Latin Extended-A entra após revisão de posicionamento e composição de marcas.
7. Só então desenvolver Display, Rounded e Mono como famílias coerentes, não como filtros ou simples deformações da Sans.

## Princípios de desenho

- Altura-x generosa, contraformas abertas e distinção clara entre I/l/1, O/0 e rn/m.
- Curvas suaves sem geometria rígida excessiva.
- Terminais limpos e consistentes.
- Pontuação, acentos e cedilha desenhados com o mesmo cuidado dos caracteres-base.
- Espaçamento e kerning testados em palavras, não apenas glifos isolados.
- Métricas verticais estáveis entre pesos para evitar saltos de layout.
- Legibilidade testada em 12–16 px na Web e em escalas de fonte Android.
- Não usar tracking negativo generalizado em texto corrido.

## Matriz mínima de glifos

- A–Z e a–z.
- Dígitos, sinais matemáticos básicos e pontuação.
- Acentos combinantes e caracteres pré-compostos para português.
- Símbolos monetários e sinais tipográficos comuns.
- Latin-1 Supplement; Latin Extended-A após revisão.
- Kerning para pares frequentes e testes com texto em português e inglês.

A cobertura Unicode deve ser publicada com precisão. Não alegar suporte a idiomas ou scripts que não tenham sido desenhados e testados.

## Formatos e integração

- Web: WOFF2, com `font-display: swap` e subconjuntos apenas quando a estratégia de fallback e os caracteres forem verificados.
- Android: TTF/OTF testado em Compose e Android Views.
- Fontes variáveis: entregar apenas após validar tabelas, instâncias e interpolação.
- CSS e Compose mantêm fallbacks funcionais enquanto os binários originais não existem.

## Ferramentas de autoria e validação

O fluxo de produção pode usar UFO/designspace, FontForge ou Glyphs para desenho, FontTools/fontmake para compilação e FontBakery para verificações. Essas ferramentas são de autoria/CI; não são dependências de runtime do Minimal UI.

## Licenciamento e nomes

A licença SIL Open Font License 1.1 é uma candidata para os arquivos tipográficos, separada da licença MIT do restante do repositório. Antes da publicação, revisar os requisitos da OFL, escolher Reserved Font Names quando aplicável e verificar colisões dos nomes das famílias. Manter autoria, fontes editáveis e avisos de licença junto aos binários.

## Critérios de aceite antes de distribuir uma fonte

- Glifos originais e fontes editáveis versionadas.
- Métricas, sidebearings e kerning revisados.
- Diacríticos e marcas combinantes testados.
- Testes de legibilidade em tamanhos pequenos e grandes.
- Renderização conferida em Chromium, Firefox, Safari e Android suportados.
- FontBakery sem erros bloqueadores; avisos documentados.
- Arquivos WOFF2 e TTF/OTF reproduzíveis a partir das fontes.
- Licença, autoria, versão e cobertura Unicode publicadas.
- Testes de acessibilidade e fallback sem fonte carregada.

## Estado atual

Esta entrega estabelece tokens, página de espécime, integração Compose configurável e documentação de produção. **Não entrega ainda uma fonte original compilada**: o desenho dos glifos requer trabalho tipográfico próprio e revisão visual especializada. Os nomes e a API permanecem preparados para receber os arquivos aprovados sem fingir que já existem.
