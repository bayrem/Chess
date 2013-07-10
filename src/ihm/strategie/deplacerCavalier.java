
package ihm.strategie;

import ihm.Case;
import ihm.Echiquier;
import ihm.pieces.Piece;

/**
 * Strategie de deplacement du cavalier.
 *
 * @author Groupe3-ISTY.
 * @version 1.0
 */
public class deplacerCavalier implements StrategieDeplacement {
    /**
     *
     */
    boolean b = false;

    @Override
	public final boolean execute(final Case departCase,
            final Case destinationCase, final Echiquier e) {

        char nDes = destinationCase.getName().charAt(1);
        char nDep = departCase.getName().charAt(1);
        char cDes = destinationCase.getName().charAt(0);
        char cDep = departCase.getName().charAt(0);
        int n = (int) nDes;
        int d = (int) nDep;


        if ((n == d + 2 || n == d - 2) && (cDes == ((char) ((int) cDep) + 1)
          || cDes == ((char) ((int) cDep) - 1)) || (n == d + 1 || n == d - 1)
          && (cDes == ((char) ((int) cDep) + 2)
          || cDes == ((char) ((int) cDep) - 2))) {
                //case vide ou adversaire
            if (!destinationCase.contientPiece()
                    || ((Piece) departCase.getComponents()[0]).getType()
                   != ((Piece) destinationCase.getComponents()[0]).getType()) {
                //destinationCase.removeAll();
            	
            	
                b = true;
                return true;
                }
            }
            return false;
	}

	@Override
	public final int conversionCharInt(final char c) {
		int i = 0;
		switch((int) c) {
		    case 49 : i = 1; break;
		    case 50 : i = 2; break;
		    case 51 : i = 3; break;
		    case 52 : i = 4; break;
		    case 53 : i = 5; break;
		    case 54 : i = 6; break;
		    case 55 : i = 7; break;
		    case 56 : i = 8; break;
		    default: break;
		}
		return i;
	}
}
