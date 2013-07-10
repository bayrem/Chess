package ihm;

import ihm.pieces.Piece;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * La fenetre du jeu.
 * 
 * @author Groupe3-ISTY
 * @version 1.0
 */
public class JeuEchecs extends JFrame {

	/**	Composant de la fenetre. */
    private static final long serialVersionUID = -4541867487313798077L;
	private java.awt.Button buttonAvancer;
    private java.awt.Button buttonRetour;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JMenuItem jMenuItem13;
    private javax.swing.JPanel jPanel1;
    private java.awt.List list1;
    private javax.swing.JMenu menuAide;
    private javax.swing.JMenu menuApropos;
    private javax.swing.JMenu menuFichier;
    private javax.swing.JMenu menuMode;
    private javax.swing.JMenu menuMoteurs;
    private javax.swing.JMenu menuTheme;
    private javax.swing.JPanel panelChess;
    private Echiquier echiquier;

	/**
	 * Constructeur.
	 */
	public JeuEchecs() {
		this.setTitle("Projet JAVA : Jeu d'echec");
		this.getContentPane().add(construireEchecs());
	}

	/**
	 * Construction de la fenettre de l'echiquier. 
	 * @return *********
	 */
	@SuppressWarnings("deprecation")
	private JComponent construireEchecs() {
		JPanel result = new JPanel();
		echiquier = new Echiquier();
		jPanel1 = new javax.swing.JPanel();
        panelChess = new javax.swing.JPanel();
        buttonRetour = new java.awt.Button();
        buttonAvancer = new java.awt.Button();
        jLabel1 = new javax.swing.JLabel();
        list1 = new java.awt.List();
        jMenuBar1 = new javax.swing.JMenuBar();
        menuFichier = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        menuMode = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        menuMoteurs = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        menuTheme = new javax.swing.JMenu();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenuItem10 = new javax.swing.JMenuItem();
        jMenuItem11 = new javax.swing.JMenuItem();
        jMenuItem12 = new javax.swing.JMenuItem();
        jMenuItem13 = new javax.swing.JMenuItem();

        menuAide = new javax.swing.JMenu();
        menuApropos = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(746, 600));

        jPanel1.setMinimumSize(new java.awt.Dimension(10, 10));
        jPanel1.setPreferredSize(new java.awt.Dimension(726, 568));

        javax.swing.GroupLayout panelChessLayout =
        		new javax.swing.GroupLayout(panelChess);

        panelChess.add(echiquier);


        panelChessLayout.setHorizontalGroup(
            panelChessLayout.createParallelGroup(
            javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 572, Short.MAX_VALUE)
        );

        panelChessLayout.setVerticalGroup(
            panelChessLayout.createParallelGroup(
            javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 462, Short.MAX_VALUE)
        );

        buttonRetour.setLabel("<<");
        buttonRetour.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(final java.awt.event.ActionEvent evt) {
            	if (evt.getSource() == buttonRetour) {

            		String mvt = echiquier.getpgn().getHistorique().substring(echiquier.getpgn().getHistorique().length()-4);
            		System.out.println(mvt);
            		System.out.println(echiquier.getCases().get(mvt.substring(2)).getName());
            		Piece piece = (Piece) echiquier.getCases().get(mvt.substring(2)).getComponents()[0];
                	((Case) echiquier.getCases().get(mvt.substring(2))).remove(piece);
                	((Case) echiquier.getCases().get(mvt.substring(0,2))).add(piece);
                	echiquier.repaint();
                	echiquier.revalidate();
                	echiquier.getpgn().setHistorique1(echiquier.getpgn().getHistorique().substring(0, echiquier.getpgn().getHistorique().length()-4));
                	echiquier.setTurn(-1);
                }
            }
        });

        buttonAvancer.setLabel(">>");
        buttonAvancer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(final java.awt.event.ActionEvent evt) {
            	if (evt.getSource() == buttonAvancer) {
            		System.out.println(echiquier.getpgn().getFfwind());
            		System.out.println(echiquier.getpgn().getHistorique());
            		String mvt = echiquier.getpgn().getFfwind().substring(echiquier.getpgn().getHistorique().length(),echiquier.getpgn().getHistorique().length()+4);
            		System.out.println(mvt);
            		System.out.println(echiquier.getCases().get(mvt.substring(0,2)).getName());
            		Piece piece = (Piece) echiquier.getCases().get(mvt.substring(0,2)).getComponents()[0];
                	((Case) echiquier.getCases().get(mvt.substring(0,2))).remove(piece);
                	((Case) echiquier.getCases().get(mvt.substring(2))).add(piece);
                	echiquier.repaint();
                	echiquier.revalidate();
                	echiquier.getpgn().setHistorique(mvt);
                	echiquier.setTurn(1);
                }
            }
        });

        javax.swing.GroupLayout jPanel1Layout =
        	new javax.swing.GroupLayout(jPanel1);

        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(
            	javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(163, 163, 163)
                .addComponent(buttonRetour, javax.swing.GroupLayout
                .PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
                javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23)
                .addComponent(buttonAvancer, javax.swing
                .GroupLayout.PREFERRED_SIZE, javax.swing
                .GroupLayout.DEFAULT_SIZE, javax.swing
                .GroupLayout.PREFERRED_SIZE)
                .addContainerGap(350, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(
            	javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(panelChess, javax.swing.GroupLayout
                    .DEFAULT_SIZE, javax.swing.GroupLayout
                    .DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(144, 144, 144)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(
            	javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(499, 499, 499)
                .addGroup(jPanel1Layout.createParallelGroup(
                	javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buttonAvancer, javax.swing
                    .GroupLayout.PREFERRED_SIZE, javax.swing
                    .GroupLayout.DEFAULT_SIZE, javax.swing
                    .GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonRetour, javax.swing.GroupLayout
                    .PREFERRED_SIZE, javax.swing.GroupLayout
                    .DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(javax.swing.GroupLayout
                    .DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createParallelGroup(
            		javax.swing.GroupLayout.Alignment.LEADING)
            		.addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(panelChess, javax.swing.GroupLayout
                    .PREFERRED_SIZE, javax.swing.GroupLayout
                    .DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(1095, Short.MAX_VALUE))));

        jLabel1.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel1.setText("Coups");

        menuFichier.setText("Fichier");

        jMenuItem1.setLabel("Sauvgarder");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                if (evt.getSource() == jMenuItem1) {
                	((Echiquier) panelChess
                		.getComponents()[0]).getpgn().save();
                	list1.clear();
                	for(int i=0;i<echiquier.getpgn().getTable().size();i++)
                	list1.add(echiquier.getpgn().getTable().get(i));
                }
            }
        });
        menuFichier.add(jMenuItem1);
        jMenuItem11.setLabel("Charger partie");
        jMenuItem11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
            	int i = 0;

                if (evt.getSource() == jMenuItem11) {
                	((Echiquier) panelChess
                    		.getComponents()[0]).getpgn()
                    		.chargerPartie("testoutput.hist");

                	for (i = 0; i < ((Echiquier) panelChess
                			.getComponents()[0]).getpgn()
                			.getCharge().length(); i += 4) {

                		System.out.println(((Echiquier) panelChess
                        	.getComponents()[0]).getpgn().getHistorique()
                        	.substring(i, i + 4));

                		((Echiquier) panelChess.getComponents()[0])
                		.interpreterBestMove(((Echiquier) panelChess
                		.getComponents()[0]).getpgn().getHistorique()
                		.substring(i, i + 4));
                	}
                }
            }
        });
        menuFichier.add(jMenuItem11);

        jMenuItem2.setLabel("Quitter");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                if (evt.getSource() == jMenuItem2) {
                	System.exit(NORMAL);
                }
            }
        });
        menuFichier.add(jMenuItem2);
        jMenuBar1.add(menuFichier);
        menuMode.setText("Mode");
        jMenuItem5.setLabel("2 Joueurs");
        
        jMenuItem5.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent evt) {
				if (evt.getSource() == jMenuItem5) {
					System.out.println("Selected: "
				+ evt.getActionCommand());
					((Echiquier) panelChess
						.getComponents()[0]).setMode(0);
				}
			}
        });
        menuMode.add(jMenuItem5);
        jMenuItem6.setLabel("VS PC");
        jMenuItem6.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent evt) {
				// TODO Auto-generated method stub
				if (evt.getSource() == jMenuItem6) {
					System.out.println("Selected: "
				+ evt.getActionCommand());
					((Echiquier) panelChess
						.getComponents()[0]).setMode(1);
				}
			}
        });
        menuMode.add(jMenuItem6);
        jMenuItem7.setLabel("Demo");
        jMenuItem7.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent evt) {
				// TODO Auto-generated method stub
				if (evt.getSource() == jMenuItem7) {
					System.out.println("Selected: "
				+ evt.getActionCommand());
					((Echiquier) panelChess
						.getComponents()[0]).setMode(2);
				}
			}
        });

        menuMode.add(jMenuItem7);
        jMenuBar1.add(menuMode);
        menuMoteurs.setText("Moteurs");
        jMenuItem3.setLabel("M1");
        jMenuItem3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent evt) {
				// TODO Auto-generated method stub
				if (evt.getSource() == jMenuItem3) {
					System.out.println("Selected: "
				+ evt.getActionCommand());
				((Echiquier) panelChess
				.getComponents()[0]).setUci(
				new CommunicationUCI("src\\moteurs\\"
				+ "Rybkav2.3.exe"),
				new CommunicationUCI("src\\moteurs\\"
				+ "Rybka v2.3.2a.mp.w32.exe"));
				}
			}
        });
        menuMoteurs.add(jMenuItem3);
        jMenuItem4.setLabel("M2");
        jMenuItem4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent evt) {
				// TODO Auto-generated method stub
				if (evt.getSource() == jMenuItem4) {
					System.out.println("Selected: "
				+ evt.getActionCommand());
				((Echiquier) panelChess
				.getComponents()[0]).setUci(
				new CommunicationUCI("src\\moteurs\\"
				+ "Rybka v2.3.2a.mp.w32.exe"),
				new CommunicationUCI("src\\moteurs\\"
				+ "Rybkav2.3.exe"));
				}
			}
        });
        menuMoteurs.add(jMenuItem4);
        jMenuBar1.add(menuMoteurs);
        menuTheme.setText("Theme");
        jMenuItem8.setLabel("Theme1");
        jMenuItem8.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent evt) {
				// TODO Auto-generated method stub
				if (evt.getSource() == jMenuItem8) {
					System.out.println("Selected: "
				+ evt.getActionCommand());
					/*
					((Echiquier) panelChess
					.getComponents()[0])
					.setLightColor(Color.lightGray);
					((Echiquier) panelChess
					.getComponents()[0])
					.setDarkColor(Color.darkGray);
					((Echiquier) panelChess
						.getComponents()[0]).repaint();
					((Echiquier) panelChess
					.getComponents()[0]).revalidate();*/
				}
			}
        });

        menuTheme.add(jMenuItem8);
        jMenuItem9.setLabel("Theme2");
        jMenuItem9.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent evt) {
				// TODO Auto-generated method stub
				if (evt.getSource() == jMenuItem9) {
					System.out.println("Selected: "
				+ evt.getActionCommand());
					/*
					((Echiquier) panelChess
					.getComponents()[0])
					.setLightColor(Color.black);*/
				}
			}
        });
        menuTheme.add(jMenuItem9);
        jMenuItem10.setLabel("Theme3");
        jMenuItem10.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent evt) {
				if (evt.getSource() == jMenuItem10) {
					System.out.println("Selected: "
				+ evt.getActionCommand());
					/*
					((Echiquier) panelChess
					.getComponents()[0])
					.setLightColor(Color.black);
					((Echiquier) panelChess
					.getComponents()[0])
					.setDarkColor(Color.white);
				((Echiquier) panelChess
					.getComponents()[0]).repaint();
				((Echiquier) panelChess
					.getComponents()[0]).revalidate();*/
				}
			}
        });
        menuTheme.add(jMenuItem10);
        jMenuBar1.add(menuTheme);

        menuAide.setText("Aide");
        jMenuItem12.setLabel("Regle du jeu");
        jMenuItem12.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent evt) {
				if (evt.getSource() == jMenuItem12) {
					System.out.println("Selected: "
				+ evt.getActionCommand());
					if (Desktop.isDesktopSupported()) {
						try {
							Desktop.getDesktop().browse(new URI("http://www.thechessstore.com/category/rulesofchess/"));
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (URISyntaxException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					    }
				}
			}
        });
        menuAide.add(jMenuItem12);
        
        jMenuItem13.setLabel("Strategies du jeu");
        jMenuItem13.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent evt) {
				if (evt.getSource() == jMenuItem13) {
					System.out.println("Selected: "
				+ evt.getActionCommand());
					if (Desktop.isDesktopSupported()) {
						try {
							Desktop.getDesktop().browse(new URI("http://www.chesscentral.com/Chess_Moves_a/183.htm"));
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (URISyntaxException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					    }
				}
			}
        });
        menuAide.add(jMenuItem13);
        jMenuBar1.add(menuAide);

        menuApropos.setText("?");
        jMenuBar1.add(menuApropos);
        setJMenuBar(jMenuBar1);
        javax.swing.GroupLayout layout =
        	new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(
            javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jPanel1, javax.swing
            .GroupLayout.DEFAULT_SIZE, 596, Short.MAX_VALUE)
            .addGap(18, 18, 18)
            .addGroup(layout.createParallelGroup(
            javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(list1, javax.swing.GroupLayout
            .PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(layout.createSequentialGroup()
            .addGap(37, 37, 37)
            .addComponent(jLabel1)))
            .addContainerGap()));

        layout.setVerticalGroup(
            layout.createParallelGroup(
            javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(layout.createParallelGroup(
            javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
            .addGap(21, 21, 21)
            .addComponent(jLabel1)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(list1, javax.swing.GroupLayout
            .PREFERRED_SIZE, 400, javax.swing.GroupLayout
            .PREFERRED_SIZE)).addComponent(
            jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE,
            533, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addContainerGap(989, Short.MAX_VALUE)));
        pack();
		return result;
	}
}
