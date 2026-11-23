/* Module and purpose: Tabuleiro: lista de casas (normais, avanço, retorno) e o ressalto no fim.
 * Student name/number and implemented contributions: Mafalda Cardoso 30017160 - implementou este módulo
 * Collaborator/reviewer and contribution: Nédes Júnior 30016916 - revisor
 * AI assistance: Claude, código gerado e depois revisto por nós (ver AI_USE.md)
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Board {
    public static final int SIZE = 30;
    private static final Map<Integer, Integer> ADVANCES = Map.of(4, 10, 12, 18, 20, 26);
    private static final Map<Integer, Integer> RETURNS = Map.of(9, 3, 17, 11, 25, 19, 28, 14);

    private final List<Cell> cells = new ArrayList<>();

    public Board() {
        for (int i = 0; i <= SIZE; i++) {
            if (ADVANCES.containsKey(i)) cells.add(new AdvanceCell(i, ADVANCES.get(i)));
            else if (RETURNS.containsKey(i)) cells.add(new ReturnCell(i, RETURNS.get(i)));
            else cells.add(new NormalCell(i));
        }
    }

    public int bounce(int cell) {
        return cell > SIZE ? SIZE - (cell - SIZE) : cell;
    }

    public int effect(int cell) {
        return cells.get(cell).landOn();
    }

    public Cell cellAt(int index) {
        return cells.get(index);
    }
}
