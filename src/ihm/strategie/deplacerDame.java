
package ihm.strategie;

import ihm.Case;
import ihm.Echiquier;

/**
 * Strategie de deplacement de la dame.
 *
 * @author Groupe3-ISTY.
 * @version 1.0
 */
public class deplacerDame implements StrategieDeplacement {
    /**
     *
     */
    boolean b = false;

    @Override
    public final boolean execute(final Case departCase,
            final Case destinationCase, final Echiquier e) {

        boolean c = new Context(new deplacerTour()
                ).executeStrategy(departCase, destinationCase, e);
        boolean a = new Context(new deplacerFou()
                ).executeStrategy(departCase, destinationCase, e);

        if (a || c) {
            b = true;
        }

        return a || c;
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
            case 56 : i=8; break;
            default: break;
        }
        return i;
    }
}
