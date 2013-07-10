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
public class deplacerTour implements StrategieDeplacement {
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
        int d = (int) this.conversionCharInt(nDep);

        if (nDes == nDep || cDes == cDep) {
            for (int i = 1; i <= Math.abs(n - d); i++) {
                if (d != 8 && n > d + i && ((Case) e.getCases().get(
                         cDep + "" + (d + i))).contientPiece()) {
                    System.out.println(((Case) e.getCases().get(
                       cDep + "" + (d + i))).getName() + "contient piece");
                    return false;
                }

                if (d != 1 && n < d - i && ((Case) e.getCases().get(
                          cDep + "" + (d - i))).contientPiece()) {
                    System.out.println(((Case) e.getCases().get(
                        cDep + "" + (d - i))).getName() + "contient piece");
                    return false;
                }
            }
            for (int i = 1; i < cDep - cDes; i++) {
                if (cDep != 'a' && ((Case) e.getCases().get(
                    conversionIntChar(cDep - i) + "" + d)).contientPiece()) {

                    System.out.println(((Case) e.getCases().get(
                          conversionIntChar(cDep - i) + "" + d)).getName()
                              + "contient piece");
                    return false;
                }

                if (cDep != 'h' && ((Case) e.getCases().get(
                    conversionIntChar(cDep + i) + "" + d)).contientPiece()) {

                    System.out.println(((Case) e.getCases().get(
                            conversionIntChar(cDep + i) + "" + d)).getName()
                                + "contient piece");
                    return false;
                }
            }
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
        switch((int) c) {
            case 49 : i=1; break;
            case 50 : i=2; break;
            case 51 : i=3; break;
            case 52 : i=4; break;
            case 53 : i=5; break;
            case 54 : i=6; break;
            case 55 : i=7; break;
            case 56 : i=8; break;
            default:break;
        }
        return i;
    }

    /**
     * conversion de int a char.
     *
     * @param i entier a convertire.
     * @return caractaire.
     */
    public final char conversionIntChar(final int i) {
        char c = '0';
        switch(i) {
            case 97 : c = 'a'; break;
            case 98 : c = 'b'; break;
            case 99 : c = 'c'; break;
            case 100 : c = 'd'; break;
            case 101 : c = 'e'; break;
            case 102 : c = 'f'; break;
            case 103 : c = 'g'; break;
            case 104 : c = 'h'; break;
            default: break;
        }
        return c;
    }
}
