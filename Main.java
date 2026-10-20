/* Module and purpose: Apenas a interface de consola.
 * Student name/number and implemented contributions: Nédes Júnior 30016916 - implementou este módulo
 * Collaborator/reviewer and contribution: Mafalda Cardoso 30017160 - revisor/a
 * AI assistance: Claude, código do protótipo inicial (ver AI_USE.md)
 */
import java.util.Random;
import java.util.Scanner;

public class Main {
    static final int SAIR = -2, RECOMECAR = -3;
    static final Scanner in = new Scanner(System.in);
    static final Random rnd = new Random();

    static int readRoll(String quem, boolean manual) {
        while (true) {
            if (manual) System.out.print("Escreve o lançamento de " + quem + " (1-6, r recomeçar, q sair): ");
            else System.out.print("Prime Enter para lançar (" + quem + ") (r recomeçar, q sair): ");
            if (!in.hasNextLine()) return SAIR;
            String s = in.nextLine().trim().toLowerCase();
            if (s.equals("q")) return SAIR;
            if (s.equals("r")) return RECOMECAR;
            if (!manual) {
                int r = rnd.nextInt(6) + 1;
                System.out.println("Saiu " + r);
                return r;
            }
            try {
                int r = Integer.parseInt(s);
                if (r >= 1 && r <= 6) return r;
                System.out.println("O lançamento deve ser entre 1 e 6.");
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida.");
            }
        }
    }

    public static void main(String[] args) {
        boolean manual = false;
        Game game = null;
        while (true) {
            if (game == null) {
                System.out.print("Modo de dados: (a)leatório, (m)anual ou (q) sair? ");
                if (!in.hasNextLine()) return;
                String m = in.nextLine().trim().toLowerCase();
                if (m.equals("q")) return;
                if (!m.equals("a") && !m.equals("m")) { System.out.println("Opção inválida."); continue; }
                manual = m.equals("m");
                game = new Game();
                int starter = 0;
                boolean recomecar = false;
                System.out.println("Lançamento de abertura: quem tirar o número maior começa.");
                while (starter == 0) {
                    int a = readRoll("jogador 1", manual);
                    if (a == SAIR) return;
                    if (a == RECOMECAR) { recomecar = true; break; }
                    int b = readRoll("jogador 2", manual);
                    if (b == SAIR) return;
                    if (b == RECOMECAR) { recomecar = true; break; }
                    starter = game.chooseStarter(a, b);
                    if (starter == 0) System.out.println("Empate. Lançam outra vez.");
                }
                if (recomecar) { game = null; continue; }
                System.out.println("Começa o jogador " + starter + ".");
            }
            System.out.println("Estado " + game.state() + " - Jogador " + game.currentPlayer());
            int roll = readRoll("jogador " + game.currentPlayer(), manual);
            if (roll == SAIR) return;
            if (roll == RECOMECAR) { game = null; continue; }
            TurnResult t = game.play(roll);
            System.out.println("Jogador " + t.player() + ": " + t.from() + " -> " + t.landed()
                + (t.finalPos() != t.landed() ? " -> efeito -> " + t.finalPos() : ""));
            if (game.isOver()) {
                System.out.println("Final " + game.state() + (game.winner() == 0 ? " - Empate"
                    : " - Vence o jogador " + game.winner()));
                game = null;
            }
        }
    }
}
