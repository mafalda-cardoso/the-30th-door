/* Module and purpose: Verificações repetíveis com lançamentos predeterminados.
 * Student name/number and implemented contributions: Mafalda Cardoso 30017160 - implementou este módulo
 * Collaborator/reviewer and contribution: Nédes Júnior 30016916 - revisor/a
 * AI assistance: Claude, código do protótipo inicial (ver AI_USE.md)
 */
import java.util.Arrays;

public class Checks {
    static int passed = 0, total = 0;

    static void check(String nome, String inicio, String entrada, String esperado, String obtido) {
        total++;
        boolean ok = esperado.equals(obtido);
        if (ok) passed++;
        System.out.println((ok ? "OK " : "FALHOU ") + nome + " | início " + inicio
            + " | entrada " + entrada + " | esperado " + esperado + " | obtido " + obtido);
    }

    static Game run(int... rolls) {
        Game g = new Game();
        for (int r : rolls) g.play(r);
        return g;
    }

    static int[] repeat(int v, int n) { int[] a = new int[n]; Arrays.fill(a, v); return a; }

    public static void main(String[] args) {
        check("movimento normal", "(0, 0)", "1", "(1, 0)", run(1).state());
        check("avanço 4->10", "(0, 0)", "4", "(10, 0)", run(4).state());
        check("retorno 9->3", "(6, 1)", "3 (J1 na casa 6)", "(3, 1)", run(6, 1, 3).state());
        check("casa partilhada", "(0, 0)", "1,1", "(1, 1)", run(1, 1).state());
        check("ressalto sem efeito", "(0, 0)", "6,1,6,1,5,1,6,1,2", "(29, 10)",
              run(6, 1, 6, 1, 5, 1, 6, 1, 2).state());
        check("ressalto e depois retorno", "(0, 0)", "6,1,6,1,5,1,6,1,6", "(19, 10)",
              run(6, 1, 6, 1, 5, 1, 6, 1, 6).state());

        Game g = new Game();
        String r;
        try { g.play(0); r = "sem exceção"; } catch (IllegalArgumentException e) { r = "IAE"; }
        check("valor 0 inválido mantém estado", "(0, 0) J1", "0", "IAE (0, 0) J1",
              r + " " + g.state() + " J" + g.currentPlayer());
        try { g.play(7); r = "sem exceção"; } catch (IllegalArgumentException e) { r = "IAE"; }
        check("valor 7 inválido mantém estado", "(0, 0) J1", "7", "IAE (0, 0) J1",
              r + " " + g.state() + " J" + g.currentPlayer());

        Game w = run(6, 1, 6, 2, 6, 3, 6);
        check("vitória exata no 30", "(0, 0)", "6,1,6,2,6,3,6", "(30, 6) terminado vencedor 1",
              w.state() + (w.isOver() ? " terminado" : " não terminado") + " vencedor " + w.winner());
        try { w.play(1); r = "sem exceção"; } catch (IllegalStateException e) { r = "ISE"; }
        check("jogar depois do fim é rejeitado", "jogo terminado", "1", "ISE", r);

        check("não termina antes da ronda 60", "(0, 0)", "119 uns", "false", String.valueOf(run(repeat(1, 119)).isOver()));
        Game t = run(repeat(1, 120));
        check("termina na ronda 60 com empate", "(0, 0)", "120 uns", "true vencedor 0",
              t.isOver() + " vencedor " + t.winner());

        Game fresh = new Game();
        check("recomeço dá jogo novo", "qualquer", "novo jogo", "(0, 0) J1",
              fresh.state() + " J" + fresh.currentPlayer());


        Game s1 = new Game();
        check("abertura: jogador 2 tira mais", "(0, 0) J1", "3,5", "2 J2", s1.chooseStarter(3, 5) + " J" + s1.currentPlayer());
        Game s2 = new Game();
        check("abertura: jogador 1 tira mais", "(0, 0) J1", "6,2", "1 J1", s2.chooseStarter(6, 2) + " J" + s2.currentPlayer());
        Game s3 = new Game();
        check("abertura: empate não decide", "(0, 0) J1", "4,4", "0 J1", s3.chooseStarter(4, 4) + " J" + s3.currentPlayer());
        Game s4 = new Game();
        try { s4.chooseStarter(0, 3); r = "sem exceção"; } catch (IllegalArgumentException e) { r = "IAE"; }
        check("abertura: valor 0 inválido mantém estado", "(0, 0) J1", "0,3", "IAE J1", r + " J" + s4.currentPlayer());
        Game s5 = new Game();
        s5.chooseStarter(1, 6);
        s5.play(4);
        check("jogador 2 começa e joga", "(0, 0)", "abertura 1,6; jogada 4", "(0, 10) J1", s5.state() + " J" + s5.currentPlayer());
        try { s5.chooseStarter(2, 1); r = "sem exceção"; } catch (IllegalStateException e) { r = "ISE"; }
        check("abertura depois de jogar é rejeitada", "jogo a decorrer", "2,1", "ISE", r);
        Game s6 = new Game();
        s6.chooseStarter(1, 6);
        for (int i = 0; i < 120; i++) s6.play(1);
        check("60 rondas com jogador 2 a abrir", "(0, 0)", "abertura 1,6; 120 uns", "true vencedor 0", s6.isOver() + " vencedor " + s6.winner());

        System.out.println(passed + "/" + total + " verificações bem-sucedidas");
    }
}
