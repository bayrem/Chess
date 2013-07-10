package ihm;

import ihm.echecs.Echec;
import ihm.pieces.Cavalier;
import ihm.pieces.Dame;
import ihm.pieces.Fou;
import ihm.pieces.Piece;
import ihm.pieces.Pion;
import ihm.pieces.Roi;
import ihm.pieces.Tour;
import ihm.strategie.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.*;
import java.util.HashMap;

import javax.swing.JPanel;

import pgn.*;


/**
 * Un Jpanel pour le jeu de l'echiquier.
 *
 * @author Groupe3-ISTY
 * @version 1.0
 */

public class Echiquier extends JPanel {

/***/
	private static final long serialVersionUID = -4994945608183798527L;

	/** Table pour la sauvgarde. */
	private TablePgn pgn = new TablePgn();
	/** HashMap pour les cases. */
	private HashMap<String, Case> cases = new HashMap<String, Case>();
	/** HashMap pour les pieces. */
	private HashMap<String, Piece> pieces = new HashMap<String, Piece>();
	/** Programme Moteur1. */
	private CommunicationUCI uci;
	/** Programme Moteur2. */
	private CommunicationUCI uci1;
	/** La case du roi blanc.*/
	private Case caseRoiBlanc;
	/** La case du roi noire. */
	private Case caseRoiNoir;
	/** Variables pour le mode de jeu. */
	private int i , contrePC;
	/** Best Move. */
	private String bestMove;
	/** Variable pour mettre un ouvement au moteur. */
	private String setMove = "";

	/**
	 * Constructeur.
	 */
	public Echiquier() {
		creerLesCases();
		relierLesListenersAuxCase();
		creerLesPieces();
		placerLesPieces();
	}

	/**
	 * Setter des Moteurs.
	 * @param u1 1er Moteur.
	 * @param u2 2eme Moteur.
	 */
	public final void setUci(final CommunicationUCI u1,
			final CommunicationUCI u2) {
		this.uci = u1;
		this.uci1 = u2;
	}
	/**
	 * Mettre un Mode.
	 * @param a nombre de mode
	 */
	public final void setMode(final int a) {
		this.contrePC = a;
	}
	/**
	 * Relier Les Listeners Aux Cas.
	 */
	private void relierLesListenersAuxCase() {
		CaseMouseListener mouseListener = new CaseMouseListener(this);
		for (int ligne : Constantes.lignes) {
			for (char colonne : Constantes.colonnes) {
				cases.get(colonne + "" + ligne)
				.addMouseListener(mouseListener);
			}
		}
	}
    /**
     * Changer le tour.
     * @param i
     */
	public void setTurn(int i) {
		this.i += i;
	}

	/**
	 * Creer les cases.
	 */
	private void creerLesCases() {
		Color couleur;
		boolean flag = false;
		this.setPreferredSize(new Dimension(400, 400));
		this.setBackground(Color.gray);
		this.setLayout(new GridLayout(8, 8));
		for (int ligne : Constantes.lignes) {
			flag = toggleFlag(flag);
			for (char colonne : Constantes.colonnes) {
				if (flag) {
					couleur = Color.lightGray;
				}
				else {
					couleur = Color.darkGray;
				}
				Case c = new Case(colonne + ""
				+ ligne, couleur, 50);
				this.add(c);
				cases.put(c.getName(), c);
				flag = toggleFlag(flag);
			}
		}
	}

	/**
	 * Placer les pieces.
	 */
	private void placerLesPieces() {
		int lignePion = 2;
		int ligneAutres = 1;
		for (TypeJoueur type : TypeJoueur.values()) {
			char index = 'a';
			for (int i = 1; i <= 8; i++) {
				cases.get(index + "" + lignePion)
				.add(pieces.get(type.getValue() + "_pion" + i));
				index++;
			}

			cases.get("a" + ligneAutres).add(pieces.get(type.
					getValue() + "_tour1"));
			cases.get("h" + ligneAutres).add(pieces
					.get(type.getValue() + "_tour2"));

			cases.get("b" + ligneAutres).add(pieces.get(type
					.getValue() + "_cavalier1"));

			cases.get("g" + ligneAutres).add(pieces.
					get(type.getValue() + "_cavalier2"));

			cases.get("c" + ligneAutres).add(pieces
					.get(type.getValue() + "_fou1"));

			cases.get("f" + ligneAutres).add(pieces
					.get(type.getValue() + "_fou2"));

			cases.get("d" + ligneAutres).add(pieces
					.get(type.getValue() + "_dame"));

			cases.get("e" + ligneAutres).add(pieces
					.get(type.getValue() + "_roi"));
			lignePion = 7;
			ligneAutres = 8;
		}
		setCaseRoiBlanc((Case) cases.get("e1"));
		setCaseRoiNoir((Case) cases.get("e8"));
	}

	/**
	 * Creer les pieces.
	 */
	private void creerLesPieces() {
		for (TypeJoueur type:TypeJoueur.values()) {
			for (int i = 1; i <= 8; i++) {
				pieces.put(type.getValue() + "_pion"
			+ i, new Pion(type));
			}
			for(int i = 1; i<= 2; i++){
				pieces.put(type.getValue() + "_tour"
						+ i, new Tour(type));
				pieces.put(type.getValue()
						+ "_fou" + i, new Fou(type));
				pieces.put(type.getValue()
					+ "_cavalier" + i, new Cavalier(type));
			}
			pieces.put(type.getValue()
					+ "_roi", new Roi(type));
			pieces.put(type.getValue()
					+ "_dame", new Dame(type));
		}
	}

	/**
	 * toggl a flag.
	 *
	 * @param flag boolean
	 * @return boolean
	 */
	private boolean toggleFlag(final boolean flag) {
		if (flag) {
			return false;
		}

		else {
			return true;
		}
	}

	/**
	 * Getter.
	 * @return la Hashmap des cases.
	 */
	public final HashMap<String, Case> getCases() {
		return cases;
	}

	/**
	 * getter pour la case selectiner.
	 * @return ********
	 */
	public final Case getSelectedCase() {
		for (Case c : cases.values()) {
			if (c.isSelected()) {
				return c;
			}
		}
		return null;
	}

	/**
	 * interpreter le Best Move.
	 * @param best string best move.
	 */
	public final void interpreterBestMove(final String best) {
		String nameDepart = new String();
		String nameDestination = new String();
		nameDepart = best.substring(0, 2);
		nameDestination = best.substring(2, 4);

		this.deplacer(this.cases.get(nameDepart)
				, this.cases.get(nameDestination));

	}

	/** Fonction qui s'occupe du deplacement des cases.
	*
	* @param departCase case  depart.
	* @param destinationCase case destination.
	*/

	public final void deplacer(final Case departCase, final Case destinationCase) {
		Piece piece = (Piece) departCase.getComponents()[0];
		System.out.println(piece.getName() + " " + piece.getType());
		Context context;
		boolean resultat = false;

		if ((i % 2 == 0 && piece.getType().equals("BLANC"))
			|| (i % 2 == 1 && piece.getType().equals("NOIR"))) {

			//Pion
			if (piece.getName().equals("pion")) {
				context = new Context(new deplacerPion());
				resultat = context.executeStrategy(departCase
						, destinationCase, this);
			if (resultat) {
				if (destinationCase.contientPiece()) {
					this.deplacementToString(departCase
						.getName().charAt(0) + "x"
						+ destinationCase.getName());
				}
				else {
					this.deplacementToString(
						destinationCase.getName());
					}
			}
				else {
					System.out.println("None-Valide move");
				}
			}


			//Cavalier
			else if (piece.getName().equals("cavalier")) {
				context = new Context(new deplacerCavalier());
				resultat = context.executeStrategy(
					departCase, destinationCase, this);

				if (resultat) {

					if (destinationCase.contientPiece()) {
						this.deplacementToString("Nx"
					+ destinationCase.getName());
					}


					else {
						this.deplacementToString("N"
					+ destinationCase.getName());
					}

			}

			else {
				System.out.println("None-Valide move");
			}

			}

			//Tour
			else if (piece.getName().equals("tour")) {
				context = new Context(new deplacerTour());

				resultat = context.executeStrategy(
					departCase, destinationCase, this);

				if (resultat) {

					if (destinationCase.contientPiece()) {
						this.deplacementToString("Rx"
					+ destinationCase.getName());
					}


					else {
						this.deplacementToString("R"
					+ destinationCase.getName());
					}


				//pgn.save();
				}

				else {
					System.out.println("None-Valide move");
				}
			}

			//Dame
			else if (piece.getName().equals("dame")) {
				context = new Context(new deplacerDame());
				resultat = context.executeStrategy(
					departCase, destinationCase, this);

				if (resultat) {

					if (destinationCase.contientPiece()) {
						this.deplacementToString("Qx"
					+ destinationCase.getName());
					}

					else {
						this.deplacementToString("Q"
					+ destinationCase.getName());
					}

				}

			else {
				System.out.println("None-Valide move");
			}

			}

			//Fou
			else if (piece.getName().equals("fou")) {
				context = new Context(new deplacerFou());
				resultat = context.executeStrategy(
					departCase, destinationCase, this);

				if (resultat) {
					if (destinationCase.contientPiece()) {
						this.deplacementToString("Bx"
					+ destinationCase.getName());
					}

					else {
						this.deplacementToString("B"
						+ destinationCase.getName());
					}


			}

			else {
				System.out.println("None-Valide move");
			}

			}

			//Roi
			else if (piece.getName().equals("roi")) {

				context = new Context(new deplacerRoi());
				resultat = context.executeStrategy(
					departCase, destinationCase, this);

				if (resultat) {
					//************ROCK*********************
					if ((departCase.getName().charAt(1)
					== '8' || departCase.getName().charAt(1)
					== '1') && departCase.getName()
					.charAt(1) == destinationCase
					.getName().charAt(1)) {

					if (departCase.getName().charAt(0)
					== 'e' && destinationCase.getName()
					.charAt(0) == 'g') {

					if (((Piece) getCases().
					get("h" + departCase.getName()
						.charAt(1)).getComponents()[0])
					.getName().equalsIgnoreCase("tour")) {

						getCases().get("f" + departCase
						.getName().charAt(1))
						.add(((Piece) getCases()
						.get("h" + departCase.getName()
					.charAt(1)).getComponents()[0]));

						getCases().get("h"
						+ departCase.getName()
						.charAt(1)).removeAll();
					}
					}

						else if (departCase.getName()
							.charAt(0) == 'e'
							&& destinationCase
							.getName().charAt(0)
							== 'b') {

							if (((Piece) getCases()
							.get("a" + departCase
							.getName().charAt(1))
							.getComponents()[0])
							.getName()
						.equalsIgnoreCase("tour")) {

						getCases()
						.get("c" + departCase.getName()
						.charAt(1))
						.add(((Piece) getCases()
						.get("a" + departCase.getName()
						.charAt(1))
						.getComponents()[0]));
							}

							getCases().get("a"
							+ departCase.getName()
							.charAt(1)).removeAll();
						}
					}
					//*************************************

					if (piece.getType().equals("BLANC")) {
					setCaseRoiBlanc(destinationCase);
					}


					else if (piece.getType()
							.equals("NOIR")) {
						setCaseRoiNoir(destinationCase);
					}



					if (destinationCase.contientPiece()) {
						this.deplacementToString("Kx"
					+ destinationCase.getName());
					}


					else {
						this.deplacementToString("K"
					+ destinationCase.getName());
					}

			}

			else {
				System.out.println("None-Valide move");
			}
			}
		}

		else {
			System.out.println("Veuiller attendre votre tour.");
		}
		/*
		Echec echec=new Echec();
		if(echec.echecBlanc(getCaseRoiBlanc(), this)) System.out.println("********echec blanc");
		if(echec.echecNoire(getCaseRoiNoir(), this)) System.out.println("********eche noir");
		resultat = resultat && !echec.echecBlanc(getCaseRoiBlanc(), this) && !echec.echecNoire(getCaseRoiNoir(), this);*/
		if (resultat) {

			if (destinationCase.getName().charAt(1)
					== '8' && piece.getName()
					.equals("pion")) {

				pieces.put(TypeJoueur.BLANC
					+ "_dame1", new Dame(TypeJoueur.BLANC));

				piece = pieces.get(TypeJoueur.BLANC + "_dame1");
				if(destinationCase.contientPiece()) 
            		destinationCase.remove((Piece)destinationCase.getComponents()[0]);
				destinationCase.add(piece);
				if(departCase.contientPiece()) 
            		departCase.remove((Piece)departCase.getComponents()[0]);
				Echec echec=new Echec();
				if(echec.echecBlanc(getCaseRoiBlanc(), this)) System.out.println("********echec blanc");
				if(echec.echecNoire(getCaseRoiNoir(), this)) System.out.println("********eche noir");
				resultat = resultat && !echec.echecBlanc(getCaseRoiBlanc(), this) && !echec.echecNoire(getCaseRoiNoir(), this);
				
				if (resultat) {
				this.repaint();
				this.revalidate();
				resultat = false;
				}
				
				else if(((echec.echecBlanc(getCaseRoiBlanc(), this) && piece.getType().equals("BLANC")) || (echec.echecNoire(getCaseRoiBlanc(), this) && piece.getType().equals("NOIR")))) {
					destinationCase.remove(piece);
					departCase.add(piece);
                	this.setTurn(-1);
				}
			}

			if (destinationCase.getName()
				.charAt(1) == '1' && piece
				.getName().equals("pion")) {

				pieces.put(TypeJoueur.NOIR
					+ "_dame1", new Dame(TypeJoueur.NOIR));

				piece = pieces.get(TypeJoueur.NOIR + "_dame1");

				setMove = setMove + departCase.getName()
					+ destinationCase.getName() + " ";
				//System.out.println(setMove);
				if(destinationCase.contientPiece()) 
            		destinationCase.remove((Piece)destinationCase.getComponents()[0]);
				destinationCase.add(piece);
				if(departCase.contientPiece()) 
            		departCase.remove((Piece)departCase.getComponents()[0]);
				Echec echec=new Echec();
				if(echec.echecBlanc(getCaseRoiBlanc(), this)) System.out.println("********echec blanc");
				if(echec.echecNoire(getCaseRoiNoir(), this)) System.out.println("********eche noir");
				resultat = resultat && !echec.echecBlanc(getCaseRoiBlanc(), this) && !echec.echecNoire(getCaseRoiNoir(), this);

				if (resultat) {
				this.repaint();
				this.revalidate();
				resultat = false;
				}

				else if(((echec.echecBlanc(getCaseRoiBlanc(), this) && piece.getType().equals("BLANC")) || (echec.echecNoire(getCaseRoiBlanc(), this) && piece.getType().equals("NOIR")))) {
					destinationCase.remove(piece);
					departCase.add(piece);
                	this.setTurn(-1);
				}

			}

			else {

				setMove = setMove + departCase.getName()
					+ destinationCase.getName() + " ";
				//System.out.println(setMove);
				if(destinationCase.contientPiece()) 
            		destinationCase.remove((Piece)destinationCase.getComponents()[0]);
				destinationCase.add(piece);
				departCase.remove(piece);
				Echec echec=new Echec();
				if(echec.echecBlanc(getCaseRoiBlanc(), this)) System.out.println("********echec blanc");
				if(echec.echecNoire(getCaseRoiNoir(), this)) System.out.println("********eche noir");
				resultat = resultat && !echec.echecBlanc(getCaseRoiBlanc(), this) && !echec.echecNoire(getCaseRoiNoir(), this);

				if (resultat) {
				this.repaint();
				this.revalidate();
				resultat = false;
				}

				else if(((echec.echecBlanc(getCaseRoiBlanc(), this) && piece.getType().equals("BLANC")) || (echec.echecNoire(getCaseRoiBlanc(), this) && piece.getType().equals("NOIR")))) {
					destinationCase.remove(piece);
					departCase.add(piece);
                	this.setTurn(-1);
				}
			}

			this.pgn.setHistorique(departCase
					.getName() + destinationCase.getName());
			this.pgn.setFfwind(this.pgn.getHistorique());
			i++;
		}
		//Jouer contre PC
		System.out.println("i=" + i);
		if (contrePC == 1) {
			Moteur1();
		}

		if (contrePC == 2) {
			Moteur2();
		}


	}
/**
 * Conversion de deplacement en chaine.
 *
 * @param s chaine. 
 */
	public final void deplacementToString(String s) {
		if (i % 2 != 0) {
			pgn.setJeu(s);
			pgn.getTable().add((i / 2 + 1) + pgn.getJeu());
			System.out.println(pgn.getTable().toString());
			pgn.resetJeu();

		}

		else {
			pgn.setJeu(s);
		}

	}

	/**
	 * Getter.
	 * @return la table du sauvgarde.
	 */
	public final TablePgn getpgn() {
		return this.pgn;
	}

	/**
	 * Moteur1.
	 */
	public final void Moteur1() {

		if (i % 2 == 1) {

			uci.setMove(setMove);
			try {
				bestMove = uci.getBestMove();
			} catch (IOException e) {
				e.printStackTrace();
			}
				this.interpreterBestMove(bestMove);
		}
	}

	/**
	 * Moteur 2.
	 */
	public final void Moteur2() {

		Moteur1();

		if (i % 2 == 0) {

			uci1.setMove(setMove);
			try {
				bestMove = uci1.getBestMove();
			} catch (IOException e) {
			// TODO Auto-generated catch block
				e.printStackTrace();
			}
				this.interpreterBestMove(bestMove);
		}
	}

/**
 * Getter.
 * @return la case du roi blanc.
 */
	public final Case getCaseRoiBlanc() {
		return caseRoiBlanc;
	}
/**
 * setter.
 * @param caseRoiBlanc la case du roi blanc.
 */
	public final void setCaseRoiBlanc(Case caseRoiBlanc) {
		this.caseRoiBlanc = caseRoiBlanc;
	}

/**
 * Getter.
 * @return la case du roi noir.
 */
	public final Case getCaseRoiNoir() {
		return caseRoiNoir;
	}
/**
 * Setter.
 * @param caseRoiNoir La case du roi noir.
 */
	public final void setCaseRoiNoir(Case caseRoiNoir) {
		this.caseRoiNoir = caseRoiNoir;
	}

}