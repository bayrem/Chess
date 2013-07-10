package ihm;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
/**
 * Classe qui se charge a la communication avec les moteurs.
 *
 * @author Groupe3-ISTY
 * @version 1.0
 */
public class CommunicationUCI {

	/** Process pour lancer le moteur. */
	private Process p;

	/** Buffer pour les entrees. */
	private BufferedReader out;

    /** Buffer pour les sorties. */
	private BufferedWriter in;

	/**
	 * Constructeur.
	 * @param lien pour recuperer le moteur.
	 */
	public CommunicationUCI(final String lien) {
		try {
			Runtime rt = Runtime.getRuntime();
			p = rt.exec(lien);
		} catch (Exception x) {
			x.printStackTrace();
		}
		out = new BufferedReader(
			new InputStreamReader(p.getInputStream()));
		in = new BufferedWriter(
			new OutputStreamWriter(p.getOutputStream()));
	}

	/**
	 * Mettre le moteur sur le standard UCI.
	 */
	public final void setUCI() {
		String line = "";
		try {
			in.write("uci \n");
			in.flush();
			while (true) {
				line = out.readLine();
				if (line.contains("uciok")) {
					break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(line);

	}

	/**
	 * voir si le moteur est pret.
	 * @return boolean pour l'etat du moteur.
	 */
	public final boolean isReady() {
		String line = "";
		try {
			in.write("isready \n");
			in.flush();
			line = out.readLine();
			if (line.equals("readyok")) {
				return true;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	/**
	 * Avoir le mielleur coup a jouer du moteur.
	 * @return chaine pour le bestMove.
	 * @throws IOException in out exception.
	 */
	public final String getBestMove() throws IOException {
		String line = "";
		in.write("go movetime 1000\n");
		in.flush();

		while (true) {
			line = out.readLine();
			//System.out.println(++lineCounter + ": " + line);
			if (line.contains("bestmove")) {
				break;
			}
		}
		line = line.substring(9, 13);
		System.out.println(line);
		return line;
	}
	/**
	 * donner les mouvements au moteur.
	 *
	 * @param mv mouvements.
	 */
	public final void setMove(final String mv) {
		try {
			in.write("position startpos moves " + mv + "\n");
			in.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Fermer le moteur.
	 */
	public final void fin() {
		try {
			in.write("quit");
			in.close();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		p.destroy();
	}
}


