/* Module and purpose: Dado manual: valida o texto escrito (número entre 1 e 6) e repete até ser válido.
 * Student name/number and implemented contributions: Nédes Júnior 30016916 - implementou este módulo
 * Collaborator/reviewer and contribution: Mafalda Cardoso 30017160 - revisora
 * AI assistance: Claude, código gerado e depois revisto por nós (ver AI_USE.md)
 */

import java.util.function.Consumer;
import java.util.function.Supplier;

public class ManualDice implements Dice {
    private final Supplier<String> input;
    private final Consumer<String> errors;

    public ManualDice(Supplier<String> input, Consumer<String> errors) {
        this.input = input;
        this.errors = errors;
    }

    @Override
    public int roll() {
        while (true) {
            String text = input.get().trim();
            try {
                int value = Integer.parseInt(text);
                if (value >= 1 && value <= 6) return value;
                errors.accept("O lançamento deve ser entre 1 e 6.");
            } catch (NumberFormatException e) {
                errors.accept("Entrada inválida.");
            }
        }
    }
}
