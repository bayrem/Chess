package ihm.pieces;

import ihm.TypeJoueur;
/**
 * Classe Dame herite de la classe Piece.
 *
 * @author Groupe3-ISTY
 * @version 1.0
 */

public class Dame extends Piece {
    /**
    */
    private static final long serialVersionUID = 5730666905385434158L;
    /**
     * Donner un nom pour la piece dame.
     */
    private static final String name = "dame";
    /**
     * Constructeur de la classe Dame.
     * @param type de type TypeJoueur.
     */
    public Dame(final TypeJoueur type) {
        super(name, type);
    }
}
