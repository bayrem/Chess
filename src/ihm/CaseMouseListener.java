package ihm;


import java.awt.Color;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;

/**
 * Classe pour les evenement sur une case.
 *
 * @author Groupe3-ISTY
 * @version 1.0
 */
public class CaseMouseListener implements MouseListener {

    /** L'echequier. */
    private Echiquier echiquier;
    /** Click. */
    private boolean sndClick;
    /** Case de depart. */
    private Case departCase;

/**
 * Constructeur.
 *
 * @param echiquier L'echiquier.
 */
    public CaseMouseListener(final Echiquier echiquier) {
        this.echiquier = echiquier;
    }

/**
 * Lancer a l'action du click.
 *
 * @param e L'evenement.
 */
	public final void mouseClicked(final MouseEvent e) {
		Case c = (Case) e.getComponent();

		// La logique pour la selection
		if (c.contientPiece()) {
			if (c.isSelected()) {
				toggleBorder(c, false);
				c.setSelected(false);
				sndClick = false;
			}

			else if (!c.isSelected()
				&& echiquier.getSelectedCase() == null) {

				toggleBorder(c, true);
				c.setSelected(true);
				sndClick = true;
				departCase = c;
			}
		}
		// Logique pour un simple deplacement sans contrainte
		if (echiquier.getSelectedCase() != c && sndClick) {

			echiquier.deplacer(departCase, c);
			toggleBorder(departCase, false);
			departCase.setSelected(false);
			departCase = null;
		}
	}

	/** */
	public void mouseEntered(final MouseEvent e) {
	}

    /** */
	public void mouseExited(final MouseEvent e) {
	}

    /** */
	public void mousePressed(final MouseEvent e) {
	}

    /** */
	public void mouseReleased(final MouseEvent e) {
	}

	/**
     * Mettre les Bordures.
     * 
     * @param c la case.
     * @param flagToggle un flag.
     */
	private void toggleBorder(final Case c, final boolean flagToggle) {
		if (flagToggle) {
			c.setBorder(BorderFactory.createLineBorder(Color.red));
		}

		else {
			c.setBorder(BorderFactory.createEmptyBorder());
		}
	}
}
