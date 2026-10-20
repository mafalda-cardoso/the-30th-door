/* Module and purpose: Resultado imutável de uma jogada.
 * Student name/number and implemented contributions: Mafalda Cardoso 30017160 - implementou este módulo
 * Collaborator/reviewer and contribution: Nédes Júnior 30016916 - revisor/a
 * AI assistance: Claude, código do protótipo inicial (ver AI_USE.md)
 */
public record TurnResult(int player, int roll, int from, int landed, int finalPos, boolean won) {}
