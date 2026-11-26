# Relatório de estado de novembro (prazo 26 de novembro)
**The 30th Door** · Mafalda Cardoso (30017160) e Nédes Júnior (30016916)

## 1. Repositório e versão avaliada
- URL: https://github.com/mafalda-cardoso/the-30th-door
- Tag: `november-milestone`
- Commit: [colar aqui o hash do último commit]

## 2. Estado atual
**Funciona:**
- Jogo jogável na consola, com os dois modos de dados (aleatório e manual) através da interface `Dice` (RandomDice, ManualDice, FixedDice).
- Lançamento de abertura (quem tirar o maior começa), movimento, ressalto no fim, 3 casas de avanço e 4 de retorno, vitória exata na casa 30, fim ao fim de 60 rondas, sair e recomeçar.
- Validação: texto que não é número ou fora de 1-6 é recusado e o estado não muda.
- Tabuleiro desenhado na consola, com os peões.
- 35 verificações com lançamentos predeterminados (35/35 bem-sucedidas), incluindo os dois modos de dados, cada efeito, o ressalto, a validação, o fim do jogo e o recomeço.
- Conceito de ilustração: `artwork/concept.png` e `ARTWORK_CONCEPT.md`.

**Incompleto:** a ilustração final, o relatório final de 4 páginas, a contagem de linhas por aluno, a imagem do UML e mais testes (por exemplo, jogo completo na consola).

**Como correr:** `javac -encoding UTF-8 *.java`, depois `java Main` (jogo) e `java Checks` (verificações). Java 17 ou superior.

## 3. Contribuições individuais
- **Mafalda Cardoso:** casas (Cell, NormalCell, EffectCell, AdvanceCell, ReturnCell), Board com lista de casas, Game, TurnResult, Checks.
- **Nédes Júnior:** dados (Dice, RandomDice, ManualDice, FixedDice), Main e ConsoleView, UML, README e o conceito de ilustração.
- Uso de IA: código e rascunho de ilustração gerados com o Claude, registados em AI_USE.md, e depois lidos, compilados e testados por nós.

## 4. Reflexão e próximos passos
**Mafalda:** A maior dificuldade foi perceber como encaixar as classes novas (Dice e Cell) no código que já existia sem estragar o que funcionava. Usámos o Claude para as gerar e eu li-as com as notas, compilei e corri as verificações a cada passo até passarem todas (35/35). Antes da entrega final falta a ilustração final, o relatório de quatro páginas e mais testes.
**Nédes:** Durante esta etapa do projeto, o meu principal desafio técnico foi compreender o funcionamento e a lógica por trás da interface Dice/ManualDice. No entanto, encarei este obstáculo como uma oportunidade de aprendizagem e me dediquei a um estudo mais aprofundado sobre o tema, pesquisei documentação e procurei apoio tanto junto da minha colega de equipa como com o auxílio do Claude para esclarecer dúvidas pontuais. Essa combinação de esforço individual e colaboração foi fundamental para que eu conseguisse não só ultrapassar a dificuldade, mas também consolidar os meus conhecimentos.

**O que falta até à entrega final (4 de janeiro):** ilustração final, relatório de 4 páginas, contagem de linhas, imagem do UML, mais testes e commits regulares em dezembro.
