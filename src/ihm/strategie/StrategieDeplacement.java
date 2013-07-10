package ihm.strategie;

import ihm.Case;
import ihm.Echiquier;
/**
 * Execution du test de la strategie de deplacement..
 *
 * @author Group3-ISTY.
 * @version 1.0
 */
public interface StrategieDeplacement {
    /**
     * Methode permettant d'executer.
     *
     * @param departCase case de depart de deplacement.
     * @param destinationCase case destination de deplacement.
     * @param e l'echiquier.
     * @return l'etat de test de deplacement.
     */
    boolean execute(Case departCase, Case destinationCase, Echiquier e);
    /**
     * Converstion de caractaire a entier des cases de l'echiquier.
     *
     * @param c Caractaire a convertir.
     * @return resultat de la conversion.
     */
    int conversionCharInt(char c);
}