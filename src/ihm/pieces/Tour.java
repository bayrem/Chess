package ihm.pieces;

import ihm.TypeJoueur;
/**
 * Classe Tour herite de la classe Piece.
 *
 * @author Groupe3-ISTY.
 * @version 1.0
 */
public class Tour extends Piece {
    /**
     */
    private static final long serialVersionUID = 5730666905385434158L;

    /**
     * Nom de la piece tour.
     */
    private static final String name = "tour";

    /**
     * Constructeur.
     *
     * @param type Type de Joueur.
     */
    public Tour(final TypeJoueur type) {
        super(name, type);
    }
}
