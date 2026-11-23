/* Module and purpose: Casa normal: não acontece nada.
 * Student name/number and implemented contributions: Mafalda Cardoso 30017160 - implementou este módulo
 * Collaborator/reviewer and contribution: Nédes Júnior 30016916 - revisor
 * AI assistance: Claude, código gerado e depois revisto por nós (ver AI_USE.md)
 */

public class NormalCell implements Cell {
    private final int index;

    public NormalCell(int index) {
        this.index = index;
    }

    @Override
    public int landOn() { return index; }

    @Override
    public char symbol() { return ' '; }

    @Override
    public String kind() { return "normal"; }
}
