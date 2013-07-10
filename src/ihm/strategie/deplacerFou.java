
package ihm.strategie;

import ihm.Case;
import ihm.Echiquier;
import ihm.pieces.Piece;
/**
 * Strategie de deplacement de fou.
 *
 * @author Groupe3-ISTY
 * @version 1.0
 */
public class deplacerFou implements StrategieDeplacement {

    /**
     *
     */
    boolean b = false;

    @Override
    public boolean execute(final Case departCase,
            final Case destinationCase, final Echiquier e) {

        char nDes = destinationCase.getName().charAt(1);
        char nDep = departCase.getName().charAt(1);
        char cDes = destinationCase.getName().charAt(0);
        char cDep = departCase.getName().charAt(0);
        int numDestination = conversionCharInt(nDes);
        int numDepart = conversionCharInt(nDep);
        int carDepart=(int) cDep;
        int carDestination=(int) cDes;
        boolean valide=true;
        int i;
        int diffnum = numDestination - numDepart;
        int diffcar = (int) cDes - (int) cDep;
        
        if (Math.abs(diffnum) == Math.abs(diffcar)) {
          	// ************************
            /*deplacement vers le NORD-EST*/
            if(numDepart>numDestination && carDepart<carDestination)
            {
                int j=carDepart+1;
                for(i=numDepart-1;i>numDestination;i--)
                {
                    if(((Case)e.getCases().get(conversionIntChar(j) +""+ i)).contientPiece()) valide=false;
                    j++;
                }
               if(!valide) {System.out.println("kayn chi lwasst");return false;}
            }
            /*deplacement vers le NORD-OUEST*/
            else if(numDepart>numDestination && carDepart>carDestination)
            {
                int j=carDepart-1;
                for(i=numDepart-1;i>numDestination;i--)
                {
                    if(((Case)e.getCases().get(conversionIntChar(j) +""+ i)).contientPiece()) valide=false;
                    j--;
                }
                if(!valide) {System.out.println("kayn chi lwasst");return false;}
            }
            /*deplacement vers le SUD-EST*/
            else if(numDepart<numDestination && carDepart<carDestination)
            {
                int j=carDepart+1;
                for(i=numDepart+1;i<numDestination;i++)
                {
                    if(((Case)e.getCases().get(conversionIntChar(j) +""+ i)).contientPiece()) valide=false;
                    j++;
                }
                if(!valide) {System.out.println("kayn chi lwasst");return false;}
            }
            /*deplacement vers le SUD-OUEST*/
            else if(numDepart<numDestination && carDepart>carDestination)
            {
                int j=carDepart-1;
                for(i=numDepart+1;i<numDestination;i++)
                {
                    if(((Case)e.getCases().get(conversionIntChar(j) +""+ i)).contientPiece()) valide=false;;
                    j--;
                }
                if(!valide) {System.out.println("kayn chi lwasst");return false;}
            }
            
        	// ************************
        	//case vide ou adversaire
        	if (!destinationCase.contientPiece()
                    || ((Piece) departCase.getComponents()[0]).getType()
                    != ((Piece) destinationCase.getComponents()[0]).getType()) {
                b = true;
                return true;
            }
        
        	
        }return false;
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
            default :break;
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