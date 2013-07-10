
package ihm.strategie;
import ihm.Case;
import ihm.Echiquier;

/**
 * Contexte de la strategie.
 *
 * @author Groupe3-ISTY
 * @version 1.0
 */
public class Context {
    /**
     * Pattern pour la strategie de deplacement.
     */
    private StrategieDeplacement strategie;

    /**
     *  Constructeur.
     *
     * @param strategy la strategie a passee au contexte.
     */
    public Context(final StrategieDeplacement strategy) {
        this.strategie = strategy;
    }

    /**
     * Executer la strategie.
     *
     * @param departCase Case de depart.
     * @param destinationCase Case destination.
     * @param e le jeu echequier.
     *
     * @return boolean si on peut deplacer de la case
     *         depart a la case destination.
     */
    public final boolean executeStrategy(final Case departCase,
            final Case destinationCase, final Echiquier e) {
        return this.strategie.execute(departCase, destinationCase, e);
    }
}
