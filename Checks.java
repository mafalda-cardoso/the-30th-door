/* Module and purpose: Verificações repetíveis com lançamentos predeterminados.
 * Student name/number and implemented contributions: Mafalda Cardoso 30017160 - implementou este módulo
 * Collaborator/reviewer and contribution: Nédes Júnior 30016916 - revisor/a
 * AI assistance: Claude, código do protótipo inicial (ver AI_USE.md)
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

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


        // ---- Dados: modo aleatório ----
        RandomDice rd1 = new RandomDice(new Random(7));
        RandomDice rd2 = new RandomDice(new Random(7));
        List<Integer> seq1 = new ArrayList<>(), seq2 = new ArrayList<>();
        for (int i = 0; i < 10; i++) { seq1.add(rd1.roll()); seq2.add(rd2.roll()); }
        check("dado aleatório com semente é repetível", "Random(7) duas vezes", "10 lançamentos", "true", String.valueOf(seq1.equals(seq2)));
        RandomDice rd3 = new RandomDice(new Random(1));
        int min = 9, max = 0;
        java.util.Set<Integer> vistos = new java.util.HashSet<>();
        for (int i = 0; i < 1000; i++) { int v = rd3.roll(); min = Math.min(min, v); max = Math.max(max, v); vistos.add(v); }
        check("dado aleatório só dá 1 a 6", "Random(1)", "1000 lançamentos", "1 6 6", min + " " + max + " " + vistos.size());
        int semArg = new RandomDice().roll();
        check("dado aleatório sem argumentos (sobrecarga)", "RandomDice()", "1 lançamento", "true", String.valueOf(semArg >= 1 && semArg <= 6));

        // ---- Dados: modo manual (validação) ----
        List<String> erros1 = new ArrayList<>();
        Iterator<String> it1 = List.of("4").iterator();
        int m1 = new ManualDice(it1::next, erros1::add).roll();
        check("dado manual aceita 4", "entrada [4]", "4", "4 e 0 erros", m1 + " e " + erros1.size() + " erros");
        List<String> erros2 = new ArrayList<>();
        Iterator<String> it2 = List.of("x", "", "4").iterator();
        int m2 = new ManualDice(it2::next, erros2::add).roll();
        check("dado manual recusa texto e repete", "entrada [x, vazio, 4]", "x, vazio, 4", "4 e 2 erros", m2 + " e " + erros2.size() + " erros");
        List<String> erros3 = new ArrayList<>();
        Iterator<String> it3 = List.of("0", "7", "-2", "6").iterator();
        int m3 = new ManualDice(it3::next, erros3::add).roll();
        check("dado manual recusa fora de 1-6", "entrada [0, 7, -2, 6]", "0, 7, -2, 6", "6 e 3 erros", m3 + " e " + erros3.size() + " erros");
        Iterator<String> it4 = List.of("  3  ").iterator();
        int m4 = new ManualDice(it4::next, s -> {}).roll();
        check("dado manual ignora espaços", "entrada [\"  3  \"]", "  3  ", "3", String.valueOf(m4));

        // ---- Os dois modos usam a mesma lógica de jogo ----
        Dice fixo = new FixedDice(6, 1, 6, 2, 6, 3, 6);
        Iterator<String> itM = List.of("6", "1", "6", "2", "6", "3", "6").iterator();
        Dice manualDice = new ManualDice(itM::next, s -> {});
        Game gf = new Game(), gm = new Game();
        while (!gf.isOver()) gf.play(fixo.roll());
        while (!gm.isOver()) gm.play(manualDice.roll());
        check("jogo completo com dado fixo", "(0, 0)", "6,1,6,2,6,3,6", "(30, 6) vencedor 1", gf.state() + " vencedor " + gf.winner());
        check("dado manual dá o mesmo jogo que o fixo", "(0, 0)", "6,1,6,2,6,3,6 escritos", gf.state(), gm.state());
        String esgotado;
        try { new FixedDice(1).roll(); new FixedDice().roll(); esgotado = "sem exceção"; } catch (IllegalStateException e) { esgotado = "ISE"; }
        check("dado fixo sem valores lança exceção", "FixedDice()", "roll()", "ISE", esgotado);

        // ---- Casas ----
        String ia;
        try { new AdvanceCell(5, 3); ia = "sem exceção"; } catch (IllegalArgumentException e) { ia = "IAE"; }
        check("avanço para trás é recusado", "AdvanceCell(5, 3)", "construir", "IAE", ia);
        String ir;
        try { new ReturnCell(5, 8); ir = "sem exceção"; } catch (IllegalArgumentException e) { ir = "IAE"; }
        check("retorno para a frente é recusado", "ReturnCell(5, 8)", "construir", "IAE", ir);
        Board b = new Board();
        check("tipos das casas 4, 9, 5", "tabuleiro novo", "cellAt(4), cellAt(9), cellAt(5)", "avanço retorno normal",
              b.cellAt(4).kind() + " " + b.cellAt(9).kind() + " " + b.cellAt(5).kind());
        check("destino de cada efeito", "tabuleiro novo", "effect(4,12,20,9,17,25,28,5)", "10 18 26 3 11 19 14 5",
              b.effect(4) + " " + b.effect(12) + " " + b.effect(20) + " " + b.effect(9) + " " + b.effect(17) + " "
              + b.effect(25) + " " + b.effect(28) + " " + b.effect(5));
        String[] linhas = ConsoleView.render(new Game()).split("\n");
        check("consola: os dois peões na casa 0", "jogo novo", "render", "12", linhas[1].substring(0, 2));

        System.out.println(passed + "/" + total + " verificações bem-sucedidas");
    }
}
