package pgn;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;

/**
 * Classe de la sauvgarde..
 *
 * @author Groupe3-ISTY
 * @version 1.0
 */
public class TablePgn {
	/** Player 1 */
    String player1 = "Player1";
    /** Player 2 */
    String player2 = "Player2";
    /** Nom du jeu. */
    private String jeu = "";
    /** l'historique en chaine.*/
    private String historique = "";
    /** l'historique en chaine.*/
    private String charge = historique;
    /***/
    private String ffwind;
    /** Table de l'historique.*/
    private ArrayList <String> table = new ArrayList <String>();

	/**
	 * Getter.
	 * @return the charge
	 */
    public final String getCharge() {
    return charge;
    }

	/**
	 * Setter.
	 * @param p the charge
	 */
	public void setPlayer1(String p) {
        this.player1 = p;
    }
	/**
	 * Setter.
	 * @param p the charge
	 */
	public void setPlayer2(String p) {
        this.player2 = p;
    }

	/**
	 * Setter.
	 * @param historique l'historique.
	 */
    public final void setHistorique(final String historique) {
    this.historique += historique;
    }

    /**
	 * Setter.
	 * @param historique l'historique.
	 */
    public final void setHistorique1(final String historique) {
    this.historique = historique;
    }

    /**
	 * Getter.
	 * @return historique l'historique.
	 */
    public final String getHistorique() {
    	return this.historique;
    }
	/**
	 * getter.
	 * @return table table de l'historique.
	 */
    public final ArrayList <String> getTable() {
        return table;
    }
	/**
	 * Setter.
	 * @param table table de l'historique.
	 */
    public final void setTable(final ArrayList <String> table) {
        this.table = table;
    }

	/**
	 * Getter.
	 * @return le jeu .
	 */
	public final String getJeu() {
		return jeu;
	}

	/**
	 * Setter.
	 * @param jeu the move.
	 */
	public final void setJeu(final String jeu) {
		this.jeu = this.jeu + "." + jeu;
	}

	/**
	 * Reset.
	 */
	public final void resetJeu() {
		this.jeu = "";
	}

	/**
	 * Sauvgarder la partie.
	 *
	 */
	public final void save() {
		Date date = new Date();
		System.out.println("table sauvegarder en fichier");

		FileWriter out;
		try {
			out = new FileWriter("testoutput.pgn",false);
			out.write("[Event \"Jeu Echecs ISTY INI1\"]\n");
            out.write("[Date " + (date.getYear() + 1900) + "." + date.getMonth()
					+ "." + date.getDay() + "]\n");
			out.write("[Round \"1\"]\n");
			out.write("[White \"" + player1 + ",G\"]\n");
			out.write("[Black \"" + player1 + ",R\"]\n");
			out.write("[Result \"0-0\"]\n");
			out.write(table.toString());
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			out = new FileWriter("testoutput.hist",false);
			out.write(historique);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Charger une partie depuis un fichier.
	 *
	 * @param file
	 */
	public final void chargerPartie(String file) {

		System.out.println("chargement en cours");
		try {
            InputStream ips=new FileInputStream(file); 
			InputStreamReader ipsr=new InputStreamReader(ips);
			BufferedReader br=new BufferedReader(ipsr);
			String ligne;
			while ((ligne = br.readLine()) != null) {

				charge += ligne;
            }

		}
		catch (Exception e){
			System.out.println(e.toString());
		}
		this.setHistorique(this.getCharge());
		System.out.println(charge);
	}


	/**
	 * @return the ffwind
	 */
	public String getFfwind() {
		return ffwind;
	}


	/**
	 * @param ffwind the ffwind to set
	 */
	public void setFfwind(String ffwind) {
		this.ffwind = ffwind;
	}

}
