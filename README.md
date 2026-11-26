# The 30th Door (jogo de tabuleiro de POO, UAL 2026-2027)
Tema: mansão assombrada. Dois jogadores atravessam 30 casas. Quem tirar o número maior no lançamento de abertura começa.

**Java:** 17 ou superior (testado em Java 21).

**Compilar:** `javac -encoding UTF-8 *.java`
**Jogar:** `java Main` (a = dados aleatórios, m = dados manuais; r recomeça, q sai)
**Verificações:** `java Checks` (última linha esperada: `35/35 verificações bem-sucedidas`)

No Windows, se os acentos aparecerem trocados, corre antes `chcp 65001`.

## Estrutura
| Ficheiro | Papel |
|---|---|
| Dice, RandomDice, ManualDice, FixedDice | De onde vem o número do dado (interface e três implementações) |
| Cell, NormalCell, EffectCell, AdvanceCell, ReturnCell | As casas do tabuleiro (interface, classe abstrata e subclasses) |
| Board | Lista de casas e o ressalto no fim |
| Game, TurnResult | Regras, estado e resultado de cada jogada (sem consola) |
| ConsoleView, Main | Consola: desenho do tabuleiro e interação com o jogador |
| Checks | Verificações repetíveis com lançamentos predeterminados |

Ver também: RULES.md, CONSOLE_SKETCH.md, UML.md, TASKS.md, ARTWORK_CONCEPT.md, AI_USE.md, SAMPLE_TRANSCRIPT.txt, STATUS_REPORT_OCTOBER.md, STATUS_REPORT_NOVEMBER.md, artwork/concept.png.
