package ihm.echecs;

import ihm.Case;
import ihm.Constantes;
import ihm.Echiquier;
import ihm.pieces.Piece;
import ihm.strategie.Context;
import ihm.strategie.deplacerCavalier;
import ihm.strategie.deplacerDame;
import ihm.strategie.deplacerFou;
import ihm.strategie.deplacerPion;
import ihm.strategie.deplacerRoi;
import ihm.strategie.deplacerTour;

/**
 * Classe pour tester si il y a un echec.
 *
 * @author Groupe3-ISTY
 * @version 1.0
 */
public class Echec {
	/**
	 * Constructeur.
	 */
	public Echec(){
	}
	/**
	 * Tester l'echec chez le joueur blanc.
	 *
	 * @param roi la case de roi.
	 * @param e l'echiquier.
     * @return si il y a un echec ou non.
	 */
	public boolean echecBlanc(Case roi, Echiquier e){
		Context context;
		boolean resultat=false;
		Piece piece;
		for (int ligne : Constantes.lignes) {
			for (char colonne : Constantes.colonnes){

				if(((Case)e.getCases().get(colonne +""+ ligne)).contientPiece()){
					piece = (Piece) e.getCases().get(colonne +""+ ligne).getComponents()[0];
					if ( piece.getType().equals("NOIR")) {
						/* cas pion */
						if (piece.getName().equals("pion")) {
							context = new Context(new deplacerPion());
						}
						/* cas cavalier */
						else if (piece.getName().equals("cavalier")) {
							context = new Context(new deplacerCavalier());	
						}
						/* cas fou*/
						else if (piece.getName().equals("fou")) {
							context = new Context(new deplacerFou());
						}
						
						/* cas tour*/
						else if (piece.getName().equals("tour")) {
							context = new Context(new deplacerTour());
							}
						
						/* cas damme*/
						else if (piece.getName().equals("dame")) {
							context = new Context(new deplacerDame());
							
						}
						/* cas roi*/
						else /*if (piece.getName().equals("roi"))*/ {
							context = new Context(new deplacerRoi());
						}
						resultat = resultat || context.executeStrategy(e.getCases().get(colonne +""+ ligne), roi, e);
					}
				}
			}
		}
		return resultat;

	}
	/**
	 * testet l'echec chez le joueur noir.
	 *
	 * @param roi la case de roi.
	 * @param e l'echiquier.
	 * @return si il y a un echec ou non.
	 */
	public boolean echecNoire(Case roi, Echiquier e){
		Context context;
		boolean resultat=false;
		Piece piece;
		for (int ligne : Constantes.lignes) {
			for (char colonne : Constantes.colonnes){
				if(((Case)e.getCases().get(colonne +""+ ligne)).contientPiece()){
					piece = (Piece) e.getCases().get(colonne +""+ ligne).getComponents()[0];
					if ( piece.getType().equals("BLANC")) {
						/* cas pion */
						if (piece.getName().equals("pion")) {
							context = new Context(new deplacerPion());
						}
						/* cas cavalier */
						else if (piece.getName().equals("cavalier")) {
							context = new Context(new deplacerCavalier());	
						}
						/* cas fou*/
						else if (piece.getName().equals("fou")) {
							context = new Context(new deplacerFou());
						}
						/* cas tour*/
						else if (piece.getName().equals("tour")) {
							context = new Context(new deplacerTour());
						}
						/* cas damme*/
						else if (piece.getName().equals("dame")) {
							context = new Context(new deplacerDame());
						}
						/* cas roi*/
						else /*if (piece.getName().equals("roi"))*/ {
							context = new Context(new deplacerRoi());
						}
						resultat = resultat || context.executeStrategy(e.getCases().get(colonne +""+ ligne), roi, e);
					}
				}
			}
		}
		return resultat;
	}
}
