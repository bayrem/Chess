package ihm.strategie;

import ihm.Case;
import ihm.Echiquier;
import ihm.pieces.Piece;

/**
 * Strategie de deplacement du Roi.
 *
 * @author Groupe3-ISTY
 * @version 1.0
 *
 */
public class deplacerRoi implements StrategieDeplacement {
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
        int n = this.conversionCharInt(nDes);
        int d = this.conversionCharInt(nDep);
        int i = n - d;
        int j = (int) cDes - (int) cDep;

        if ((d == 8 || d == 1) && n == d) {
            if (cDep == 'e' && cDes == 'g') {
                if (((Piece) e.getCases().get("h" + d
                    ).getComponents()[0]).getName().equalsIgnoreCase("tour")) {
                    return true;
                }
            }

            else if (cDep == 'e' && cDes == 'b') {
                if (((Piece) e.getCases().get("a" + d
                    ).getComponents()[0]).getName().equalsIgnoreCase("tour")) {
                    return true;
                }
            }
        }
        else if ((n == d && (cDes == ((char) ((int) cDep) + 1)
                || cDes == ((char) ((int) cDep) - 1)))
                || (cDes == cDep && (n == d + 1 || n == d - 1))
                || (Math.abs(i) == Math.abs(j) && ((n == d + 1 || n == d - 1))
                || (cDes == ((char) ((int) cDep) + 1)
                        && cDes == ((char) ((int) cDep) - 1)))) {

            //case vide ou adversaire
            if (!destinationCase.contientPiece()
                   || ((Piece) departCase.getComponents()[0]).getType()
                   != ((Piece) destinationCase.getComponents()[0]).getType()) {


                b = true;
                return true;
               }
           }
        return false;
    }

    @Override
    public final int conversionCharInt(final char c) {
        int i = 0;
        switch((int) c){
            case 49 : i = 1; break;
            case 50 : i = 2; break;
            case 51 : i = 3; break;
            case 52 : i = 4; break;
            case 53 : i = 5; break;
            case 54 : i = 6; break;
            case 55 : i = 7; break;
            case 56 : i = 8; break;
        }
        return i;
    }
}
