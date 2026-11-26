# Conceito de ilustração (rascunho de novembro)
Imagem: `artwork/concept.png`. É um rascunho feito com um programa em Python (a versão final será mais elaborada).

## O que mostra
- Uma mansão assombrada à noite, com o título "The 30th Door".
- As 31 casas (0 a 30) como portas, em serpentina. Verde = atalho (avanço), vermelho = alçapão (retorno), dourado = a saída (casa 30).
- Os dois jogadores como fantasmas (1 roxo, 2 laranja).

## Prompt principal para a versão final (gerador de imagens)
> Top-down illustrated board game of a haunted mansion at night, titled "The 30th Door". A winding path of 31 numbered door-shaped squares from "entrance" to "the exit" on a dark purple background with moonlight. Green doors with arrows are secret passages that jump forward; red trapdoors send pieces back; a golden door marks square 30. Two small ghost pawns, one purple and one orange, stand on the start. Spooky but friendly style, flat illustration, high contrast, clear readable numbers.

## Tabela de representações
| Conceito do jogo | Consola | Modelo | Ilustração |
|---|---|---|---|
| Casa normal | número sem símbolo | NormalCell | porta cinzenta-roxa |
| Avanço | `4^` | AdvanceCell(4, 10) | porta verde "atalho > 10" |
| Retorno | `9v` | ReturnCell(9, 3) | porta vermelha "alçapão < 3" |
| Meta | `30` | Board.SIZE | porta dourada "a saída" |
| Peões `(22, 24)` | `1` e `2` por baixo do número | Game.pos = {22, 24} | fantasmas 1 e 2 |
| Dado | "Saiu 4" ou número escrito | Dice | (por desenhar) |
