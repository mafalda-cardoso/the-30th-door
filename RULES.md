# The 30th Door - Regras e mapa do tabuleiro

**Tema:** mansão assombrada.

## História
Dois desconhecidos acordam numa mansão abandonada, sem saber como chegaram lá. Uma voz misteriosa anuncia: "Thirty doors. Two souls. Only one way out." Para escapar, têm de atravessar 30 casas. Mas a mansão está viva e manipula o percurso, fazendo os jogadores avançar ou recuar.

## Regras

- 30 casas em linha; 0 = partida, 30 = meta. Os dois peões começam na casa 0. Quem começa decide-se com um lançamento de abertura: cada jogador lança o dado uma vez (não move o peão) e quem tirar o número maior joga primeiro. Em caso de empate lançam outra vez. Depois alternam.
- Lança-se um dado de 1 a 6 (aleatório ou manual) e o peão avança essas casas.
- **Ultrapassar a meta:** se o movimento passa de 30, o peão recua o excesso (28 + 5 = 33 -> 27).
- **Casas de avanço:** 4->10, 12->18, 20->26.
- **Casas de retorno:** 9->3, 17->11, 25->19, 28->14.
- **Ordem:** primeiro o recuo (se houver), depois no máximo um efeito. Um recuo pode cair numa casa de efeito (ex.: 29 + 6 = 35 -> recua para 25 -> retorna a 19). Sem encadeamento: o destino de um efeito nunca é outra casa de efeito.
- **Casas partilhadas:** os dois peões podem estar na mesma casa; não há bloqueio nem captura.
- **Vitória:** o primeiro peão a ficar exatamente na casa 30 (depois do recuo/efeito).
- **Fim sem jogo infinito:** após 60 rondas ganha o peão mais avançado; posições iguais = empate.
- **Entrada:** uma entrada inválida não altera o estado. `q` sai e `r` recomeça em qualquer pergunta.

```
 0 partida | 4>10 | 9<3 | 12>18 | 17<11 | 20>26 | 25<19 | 28<14 | 30 meta
```

## Tabela de representações
| Conceito do jogo | Consola | Modelo | Mockup |
|---|---|---|---|
| Posição dos peões | `Estado (22, 24)` | `Game.pos = {22, 24}` | Dois fantasmas-peão nas casas 22 e 24 |
| Jogada | `Jogador 1: 22 -> 26` | `TurnResult` | Jogador ativo destacado |
| Casa de avanço/retorno | `-> efeito -> 10` | `Board.effect()` | Passagens secretas a subir, alçapões a descer |
| Ultrapassar a meta | `de -> chegou` | `Board.bounce()` | Casa final com seta de recuo |
| Fim | `Final (30, 12) - Vence o jogador 1` | `Game.isOver()/winner()` | Faixa do vencedor |
