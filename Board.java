/* Module and purpose: Tabuleiro, ressalto no fim e casas de efeito.
 * Student name/number and implemented contributions: Mafalda Cardoso 30017160 - implementou este módulo
 * Collaborator/reviewer and contribution: Nédes Júnior 30016916 - revisor/a
 * AI assistance: Claude, código do protótipo inicial (ver AI_USE.md)
 */
import java.util.Map;

public class Board {
    public static final int SIZE = 30;
    private static final Map<Integer, Integer> EFFECTS = Map.of(
        4, 10, 12, 18, 20, 26,
        9, 3, 17, 11, 25, 19, 28, 14);

    public int bounce(int cell) {
        return cell > SIZE ? SIZE - (cell - SIZE) : cell;
    }

    public int effect(int cell) {
        return EFFECTS.getOrDefault(cell, cell);
    }
}
