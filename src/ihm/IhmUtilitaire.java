package ihm;

import java.awt.Toolkit;
import java.net.URL;

import javax.swing.ImageIcon;

import main.Main;


/**
 * Utilitaire pour cargement d'image.
 *
 * @author Groupe3-ISTY
 * @version 1.0
 */
public class IhmUtilitaire {

	/**
	 *
	 * @param filename nom de l'image.
	 * @return l'image.
	 */
	public static ImageIcon lireImageIcon(final String filename) {
        URL url = Main.class.getResource("images/" + filename);
        return new ImageIcon(Toolkit.getDefaultToolkit().getImage(url));
    }
}
