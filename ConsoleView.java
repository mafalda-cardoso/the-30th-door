/* Module and purpose: Desenha o tabuleiro e os peões na consola. Só apresentação, sem regras.
 * Student name/number and implemented contributions: Nédes Júnior 30016916 - implementou este módulo
 * Collaborator/reviewer and contribution: Mafalda Cardoso 30017160 - revisora
 * AI assistance: Claude, código gerado e depois revisto por nós (ver AI_USE.md)
 */

public class ConsoleView {
    public static String render(Game game) {
        Board board = game.board();
        int p1 = game.positionOf(1);
        int p2 = game.positionOf(2);
        StringBuilder sb = new StringBuilder();
        for (int start = 0; start <= Board.SIZE; start += 10) {
            StringBuilder numbers = new StringBuilder();
            StringBuilder pawns = new StringBuilder();
            for (int i = start; i < start + 10 && i <= Board.SIZE; i++) {
                numbers.append(String.format("%2d%c ", i, board.cellAt(i).symbol()));
                pawns.append(i == p1 ? "1" : " ").append(i == p2 ? "2" : " ").append("  ");
            }
            sb.append(numbers).append('\n').append(pawns).append('\n');
        }
        sb.append("^ avanço   v retorno   1 = jogador 1   2 = jogador 2\n");
        return sb.toString();
    }
}
