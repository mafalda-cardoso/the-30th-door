/* Module and purpose: Regras e estado do jogo, sem entrada/saída na consola.
 * Student name/number and implemented contributions: Mafalda Cardoso 30017160 - implementou este módulo
 * Collaborator/reviewer and contribution: Nédes Júnior 30016916 - revisor/a
 * AI assistance: Claude, código do protótipo inicial (ver AI_USE.md)
 */
public class Game {
    public static final int MAX_ROUNDS = 60;
    private final Board board = new Board();
    private final int[] pos = {0, 0};
    private int turn = 0;
    private int moves = 0;
    private boolean over = false;

    public TurnResult play(int roll) {
        if (over) throw new IllegalStateException("Jogo terminado");
        if (roll < 1 || roll > 6) throw new IllegalArgumentException("O lançamento deve ser entre 1 e 6");
        int p = turn;
        int from = pos[p];
        int landed = board.bounce(from + roll);
        int fin = board.effect(landed);
        pos[p] = fin;
        boolean won = fin == Board.SIZE;
        moves++;
        if (won || moves >= 2 * MAX_ROUNDS) over = true;
        turn = 1 - turn;
        return new TurnResult(p + 1, roll, from, landed, fin, won);
    }


    /** Lançamento de abertura: o jogador com o número maior começa. 0 = empate (nada muda). */
    public int chooseStarter(int roll1, int roll2) {
        if (moves > 0) throw new IllegalStateException("O jogo já começou");
        if (roll1 < 1 || roll1 > 6 || roll2 < 1 || roll2 > 6)
            throw new IllegalArgumentException("O lançamento deve ser entre 1 e 6");
        if (roll1 == roll2) return 0;
        turn = roll1 > roll2 ? 0 : 1;
        return turn + 1;
    }

    public int currentPlayer() { return turn + 1; }
    public boolean isOver() { return over; }
    public String state() { return "(" + pos[0] + ", " + pos[1] + ")"; }

    /** 0 = empate, caso contrário o número do jogador; só válido no fim. */
    public int winner() {
        if (pos[0] == pos[1]) return 0;
        return pos[0] > pos[1] ? 1 : 2;
    }
}
