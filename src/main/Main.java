package main;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import ihm.JeuEchecs;
/**
 * Main.
 *
 * @author Groupe3-ISTY
 * @version 1.0
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable()
	    {
	      public void run()
	      {
	    	  JeuEchecs jeuEchecs = new JeuEchecs();
	    	  jeuEchecs.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    	  jeuEchecs.setPreferredSize(new Dimension(726, 600));
	    	  jeuEchecs.pack();
	    	  jeuEchecs.setVisible(true);
	      }
	    });
	}

}
