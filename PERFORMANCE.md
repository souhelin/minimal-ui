# Performance — Minimal UI

Este documento define como medir a demonstração Web e evitar regressões sem adicionar dependências ao runtime da interface.

## Princípios

- A interface continua em HTML, CSS e JavaScript nativos.
- Não introduza Rust/WebAssembly, framework ou biblioteca de runtime sem um gargalo medido e um caso de uso que justifique o custo de download, inicialização e manutenção.
- Otimize primeiro o caminho crítico, o tamanho dos recursos e o trabalho no DOM.
- Não troque acessibilidade, compatibilidade ou legibilidade por ganhos não medidos.

## Verificações locais

Requisitos: Node.js 22 ou superior e Chrome/Chromium disponível para o Lighthouse.

~~~bash
npm run check:budget
npx --yes @lhci/cli@0.16.x autorun
~~~

O orçamento verifica o tamanho bruto dos arquivos de estilo e scripts principais. O Lighthouse executa três auditorias na pasta estática web/. Os relatórios ficam em .lighthouseci/.

## Orçamentos iniciais

| Recurso | Limite inicial |
|---|---:|
| web/components.css | 20 KiB |
| web/tokens.css | 6 KiB |
| web/components.js | 12 KiB |
| web/theme.js | 2 KiB |
| Total dos quatro arquivos | 40 KiB |

Os limites são guardrails para impedir crescimento acidental, não metas de minificação nem estimativas de transferência gzip/Brotli. Revise-os somente com justificativa e medição.

## Lighthouse CI

O workflow .github/workflows/performance.yml roda em pull requests, pushes para main e execução manual. As primeiras metas de performance, FCP, LCP, TBT e CLS são avisos, não bloqueios: primeiro precisamos coletar uma linha de base consistente em runner controlado. Os relatórios são armazenados como artefato do workflow, sem envio para armazenamento temporário público.

Resultados de Lighthouse variam conforme runner, navegador e carga. Compare várias execuções e priorize tendências e métricas, não um único score.

### Linha de base inicial (GitHub Actions, 17/11/2026)

Foram concluídas três execuções da página `web/index.html`:

| Execução | Performance | Acessibilidade | FCP | LCP | TBT | CLS |
|---|---:|---:|---:|---:|---:|---:|
| 1 | 93 | 100 | 906 ms | 906 ms | 333 ms | 0 |
| 2 | 100 | 100 | 908 ms | 908 ms | 0 ms | 0 |
| 3 | 100 | 100 | 907 ms | 907 ms | 2 ms | 0 |

O TBT de 333 ms na primeira execução ultrapassou isoladamente o aviso de 300 ms; as duas execuções seguintes ficaram abaixo dele. Isso não comprova uma regressão consistente. Mantenha os limites em `warn` e colete novas amostras antes de transformar as metas em bloqueios. A pontuação do Lighthouse é sintética e não representa, por si só, a experiência em aparelhos reais.

## Quando considerar Rust/WebAssembly

Só proponha WASM se um perfil real apontar uma tarefa computacional dominante (por exemplo, processamento em lote de dados grandes) e um protótipo mostrar ganho líquido depois de contabilizar download, compilação/inicialização e cópias entre JavaScript e WASM. Interações comuns de UI, eventos, formulários, temas e manipulação do DOM devem continuar em JavaScript e CSS.

## Limitações

- O orçamento mede bytes dos arquivos fonte, não bytes comprimidos servidos.
- Lighthouse CI mede uma execução sintética em ambiente de CI; não substitui dados de usuários reais (RUM), testes em aparelhos modestos nem avaliação manual.
- Os resultados registrados acima são uma linha de base sintética inicial; não substituem medições em dispositivos reais nem dados de usuários.
