/* Module and purpose: Apenas a interface de consola.
 * Student name/number and implemented contributions: Nédes Júnior 30016916 - implementou este módulo
 * Collaborator/reviewer and contribution: Mafalda Cardoso 30017160 - revisor/a
 * AI assistance: Claude, código do protótipo inicial (ver AI_USE.md)
 */
import java.util.Scanner;

public class Main {
    static class Sair extends RuntimeException {}
    static class Recomecar extends RuntimeException {}

    static final Scanner in = new Scanner(System.in);
    static boolean manual = false;
    static String quem = "";

    static String readLine() {
        if (manual) System.out.print("Escreve o lançamento de " + quem + " (1-6, r recomeçar, q sair): ");
        else System.out.print("Prime Enter para lançar (" + quem + ") (r recomeçar, q sair): ");
        if (!in.hasNextLine()) throw new Sair();
        String s = in.nextLine().trim().toLowerCase();
        if (s.equals("q")) throw new Sair();
        if (s.equals("r")) throw new Recomecar();
        return s;
    }

    static int roll(Dice dice, String jogador) {
        quem = jogador;
        if (!manual) readLine();
        int r = dice.roll();
        if (!manual) System.out.println("Saiu " + r);
        return r;
    }

    public static void main(String[] args) {
        Game game = null;
        Dice dice = null;
        while (true) {
            try {
                if (game == null) {
                    System.out.print("Modo de dados: (a)leatório, (m)anual ou (q) sair? ");
                    if (!in.hasNextLine()) return;
                    String m = in.nextLine().trim().toLowerCase();
                    if (m.equals("q")) return;
                    if (!m.equals("a") && !m.equals("m")) { System.out.println("Opção inválida."); continue; }
                    manual = m.equals("m");
                    dice = manual ? new ManualDice(Main::readLine, System.out::println) : new RandomDice();
                    game = new Game();
                    System.out.println("Lançamento de abertura: quem tirar o número maior começa.");
                    int starter = 0;
                    while (starter == 0) {
                        int a = roll(dice, "jogador 1");
                        int b = roll(dice, "jogador 2");
                        starter = game.chooseStarter(a, b);
                        if (starter == 0) System.out.println("Empate. Lançam outra vez.");
                    }
                    System.out.println("Começa o jogador " + starter + ".");
                }
                System.out.print(ConsoleView.render(game));
                System.out.println("Estado " + game.state() + " - Jogador " + game.currentPlayer());
                int r = roll(dice, "jogador " + game.currentPlayer());
                TurnResult t = game.play(r);
                System.out.println("Jogador " + t.player() + ": " + t.from() + " -> " + t.landed()
                    + (t.finalPos() != t.landed() ? " -> efeito -> " + t.finalPos() : ""));
                if (game.isOver()) {
                    System.out.print(ConsoleView.render(game));
                    System.out.println("Final " + game.state() + (game.winner() == 0 ? " - Empate"
                        : " - Vence o jogador " + game.winner()));
                    game = null;
                }
            } catch (Sair e) {
                return;
            } catch (Recomecar e) {
                game = null;
            }
        }
    }
}
