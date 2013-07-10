package ihm.pieces;

import ihm.TypeJoueur;
/**
 * Classe Fou herite de la classe piece.
 *
 * @author Groupe3-ISTY.
 * @version 1.0
 */
public class Fou extends Piece {
    /**
     */
    private static final long serialVersionUID = 5730666905385434158L;

    /**
     * Donner un nom pour la piece fou.
     */
    private static final String name = "fou";

    /**
     * Constructur.
     * @param type de type TypeJoueur.
     */
    public Fou(final TypeJoueur type) {
        super(name, type);
    }
}
