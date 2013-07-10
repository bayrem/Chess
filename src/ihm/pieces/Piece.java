package ihm.pieces;

import ihm.IhmUtilitaire;
import ihm.TypeJoueur;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
/**
 * Classe Piece herite de Jpanel.
 * @author Groupe3-ISTY.
 */
public abstract class Piece extends JPanel {
    /**
     */
    private static final long serialVersionUID = 3976917913817113741L;

    /**
     * Declarer le variable type.
     */
    private TypeJoueur type;

    /**
    * Constructeur.
    *
    * @param name parametre de type String.
    * @param type parametre de type TypeJoueur.
    */
    public Piece(final String name, final TypeJoueur type) {
        this.setOpaque(false);
        this.setLayout(new BorderLayout());
        this.setName(name);
        this.setType(type);
        JLabel label = new JLabel(IhmUtilitaire.lireImageIcon(
                  type.getValue() + "_" + name + ".png"));
        this.add(label, BorderLayout.CENTER);
    }

    /**
     * @return une chaine de caractere.
     */
    public final String getType() {
        return type.toString();
    }
    /**
     * @param type1 de type TypeJoueur.
     */
     public final void setType(final TypeJoueur type1) {
        this.type = type1;
    }
}
