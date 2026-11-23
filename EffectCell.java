/* Module and purpose: Parte comum das casas com efeito (guarda o destino). As subclasses decidem o tipo.
 * Student name/number and implemented contributions: Mafalda Cardoso 30017160 - implementou este módulo
 * Collaborator/reviewer and contribution: Nédes Júnior 30016916 - revisor
 * AI assistance: Claude, código gerado e depois revisto por nós (ver AI_USE.md)
 */

public abstract class EffectCell implements Cell {
    protected final int index;
    protected final int target;

    protected EffectCell(int index, int target) {
        this.index = index;
        this.target = target;
    }

    @Override
    public int landOn() { return target; }
}
