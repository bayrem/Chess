package ihm.pieces;


import ihm.TypeJoueur;
/**
 * Classe Cavalier pour la piece cavalier herite de la classe Piece.
 *
 * @author Groupe3-ISTY.
 * @version 1.0
 *
 */
public class Cavalier extends Piece {
    /**
     */
    private static final long serialVersionUID = 5730666905385434158L;
    /**
     * Donner un nom pour la piece cavalier.
     */
    private static final String name = "cavalier";
    /**
     * Constructeur de classe Cavalier.
     * @param type de type TypeJoue
     */
    public Cavalier(final TypeJoueur type) {
        super(name, type);
    }
}
