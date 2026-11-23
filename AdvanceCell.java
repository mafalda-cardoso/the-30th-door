/* Module and purpose: Casa de avanço: o destino tem de ser maior do que a casa.
 * Student name/number and implemented contributions: Mafalda Cardoso 30017160 - implementou este módulo
 * Collaborator/reviewer and contribution: Nédes Júnior 30016916 - revisor
 * AI assistance: Claude, código gerado e depois revisto por nós (ver AI_USE.md)
 */

public class AdvanceCell extends EffectCell {
    public AdvanceCell(int index, int target) {
        super(index, target);
        if (target <= index) throw new IllegalArgumentException("Avanço tem de ir para a frente");
    }

    @Override
    public char symbol() { return '^'; }

    @Override
    public String kind() { return "avanço"; }
}
