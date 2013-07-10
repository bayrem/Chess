package ihm.pieces;

import ihm.TypeJoueur;
/**
 * Classe Roi herite de la classe Piece.
 * @author Groupe3-ISTY.
 *
 */
public class Roi extends Piece {
    /**
     */
    private static final long serialVersionUID = 5730666905385434158L;
    /**
     *
     */
    private static final String name = "roi";
    /**
     * Constructeur.
     *
     * @param type Type TypeJoueur.
     */
    public Roi(final TypeJoueur type) {
        super(name, type);
    }
}
