
package ihm;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;
/**
 * Classe case pour les cases de l'echiquier.
 *
 * @author Groupe3-ISTY
 * @version 1.0
 */
public class Case extends JPanel {
    /**
     */
    private static final long serialVersionUID = 5551687111417758889L;

    /**
     * Variable designe si la cese est selectionee.
     */
    private boolean isSelected;

    /**
     * Constructeur d'une case.
     *
     * @param caseName nom de la case.
     * @param caseColor Couleur de la case.
     * @param size Dimention d'une case.
     */
    public Case(final String caseName, final Color caseColor, final int size) {
        this.setName(caseName);
        this.setBackground(caseColor);
        this.setPreferredSize(new Dimension(size, size));
    }
    /**
     * Retourner l'etat d'une case.
     *
     * @return isSelected si une case est selectionee.
     */
    public final boolean isSelected() {
        return isSelected;
    }

    /**
     * Selectioner une case.
     *
     * @param selected variable pour selectioner.
     */
    public final void setSelected(final boolean selected) {
        this.isSelected = selected;
    }

    /**
     *
     * @return si une case contient une piece.
     */
    public final boolean contientPiece() {
        if (this.getComponentCount() >= 1) {
            return true;
        }
        else {
            return false;
        }
    }
}