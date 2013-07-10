package ihm.pieces;

import ihm.TypeJoueur;
/**
 * Classe Pion herite de la classe Piece.
 *
 * @author Group3-ISTY.
 * @version 1.0
 */
public class Pion extends Piece {
    /**
     */
    private static final long serialVersionUID = 5730666905385434158L;
    /**
     * Donner un nom a la piece.
     */
    private static final String name = "pion";

    /**
     * Constructeur.
     *
     * @param type de type TypeJoueur.
     */
    public Pion(final TypeJoueur type) {
        super(name, type);
    }
}
