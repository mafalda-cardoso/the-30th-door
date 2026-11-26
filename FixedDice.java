/* Module and purpose: Dado com valores predeterminados, para verificações repetíveis.
 * Student name/number and implemented contributions: Nédes Júnior 30016916 - implementou este módulo
 * Collaborator/reviewer and contribution: Mafalda Cardoso 30017160 - revisora
 * AI assistance: Claude, código gerado e depois revisto por nós (ver AI_USE.md)
 */

public class FixedDice implements Dice {
    private final int[] values;
    private int next = 0;

    public FixedDice(int... values) {
        this.values = values.clone();
    }

    @Override
    public int roll() {
        if (next >= values.length) throw new IllegalStateException("Sem mais valores");
        return values[next++];
    }
}
