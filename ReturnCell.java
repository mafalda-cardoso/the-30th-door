/* Module and purpose: Casa de retorno: o destino tem de ser menor do que a casa.
 * Student name/number and implemented contributions: Mafalda Cardoso 30017160 - implementou este módulo
 * Collaborator/reviewer and contribution: Nédes Júnior 30016916 - revisor
 * AI assistance: Claude, código gerado e depois revisto por nós (ver AI_USE.md)
 */

public class ReturnCell extends EffectCell {
    public ReturnCell(int index, int target) {
        super(index, target);
        if (target >= index) throw new IllegalArgumentException("Retorno tem de ir para trás");
    }

    @Override
    public char symbol() { return 'v'; }

    @Override
    public String kind() { return "retorno"; }
}
