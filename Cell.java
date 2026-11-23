/* Module and purpose: Interface de uma casa do tabuleiro.
 * Student name/number and implemented contributions: Mafalda Cardoso 30017160 - implementou este módulo
 * Collaborator/reviewer and contribution: Nédes Júnior 30016916 - revisor
 * AI assistance: Claude, código gerado e depois revisto por nós (ver AI_USE.md)
 */

public interface Cell {
    /** Casa onde o peão fica depois de cair nesta casa. */
    int landOn();

    /** Símbolo na consola: espaço, ^ (avanço) ou v (retorno). */
    char symbol();

    /** Tipo da casa: normal, avanço ou retorno. */
    String kind();
}
