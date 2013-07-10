package ihm;

/**
 * Ennumeration pour le type de joueur.
 *
 * @author Groupe3-ISTY
 * @version 1.0
 */
public enum TypeJoueur {

	/**Les types */
	BLANC("b"), NOIR("n");

	/** Valeur. */
	private final String value;

	/**
	 * Constructeur.
	 * @param v la valeur
	 */
	TypeJoueur(final String v) {
		this.value = v;
	}

	/**
	 * Getter pour la valeur.
	 * @return valeur.
	 */
	public String getValue() {
		return value;
	}
}
