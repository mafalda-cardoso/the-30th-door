/* Module and purpose: Dado aleatório. Dois construtores: sem argumentos (jogo normal) e com Random (testes repetíveis).
 * Student name/number and implemented contributions: Nédes Júnior 30016916 - implementou este módulo
 * Collaborator/reviewer and contribution: Mafalda Cardoso 30017160 - revisora
 * AI assistance: Claude, código gerado e depois revisto por nós (ver AI_USE.md)
 */

import java.util.Random;

public class RandomDice implements Dice {
    private final Random random;

    public RandomDice() {
        this(new Random());
    }

    public RandomDice(Random random) {
        this.random = random;
    }

    @Override
    public int roll() {
        return random.nextInt(6) + 1;
    }
}
