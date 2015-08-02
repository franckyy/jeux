package com.francky.lettres.vues.panneaux;
 
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
 
import javax.swing.JPanel;
import javax.swing.Timer;
 
import com.francky.lettres.ctrl.Controleur;
 
public class PanelAffichage extends JPanel {
 
	/**
	 * 
	 */
	private static final long serialVersionUID = 8624942466374820397L;
 
	//***********************************************************DECLARATIONS
	private Controleur ctrl;
 
	private volatile boolean showText;
	private volatile int showTextCall;
	/*
	 * Déclarations concernant le clignotement
	 */
	private int count;
	private Timer animator;
 
	/*
	 * indication du mot trouvé pour le panel affichage
	 */
	private boolean motTrouve = false;		//lorsque le mot est trouvé, l'affichage de congratulation par paintComponent
 
	/*
	 * Taille du panneau affichage
	 */
	private static final int PANEL_WIDTH = PanelKeyboard.KEYBOARD_WIDTH + PanelScore.SCORE_WIDTH;
	private static final int PANEL_HEIGHT = 150;
 
	/*
	 * Taille et position de la zone d'affichage du texte
	 */
	private static final int TEXT_ZONE_WIDTH = PanelKeyboard.KEYBOARD_WIDTH + PanelScore.SCORE_WIDTH - 20;
	private static final int TEXT_ZONE_HEIGHT = 100;
	private static final int TEXT_ZONE_X = 10;
	private static final int TEXT_ZONE_Y = 25;
 
	/*
	 * Taille et position du mot à chercher
	 */
	private static final int MOT_TAILLE = 50;
	private static final Font MOT_FONT = new Font("Tahoma", Font.BOLD, MOT_TAILLE);
	private static final int MOT_Y = TEXT_ZONE_HEIGHT / 2 + TEXT_ZONE_Y + 20;
	private static final int MOT_X = TEXT_ZONE_X + 15;
 
	//***********************************************************CONSTRUCTEUR
	public PanelAffichage(Controleur ctrl) {
		this.ctrl = ctrl;
 
		setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
		setBackground(ctrl.COL_FOND);
	}
 
 
	//***********************************************************METHODES
	//modifier le background du panel
	public void modifieBackgroundColor() {
		setBackground(ctrl.COL_FOND);
	}
 
	//régénération du graphisme
	public void repaintPanelAffich() {
		this.repaint();
	}
 
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
 
		//AFFICHAGE DU RECTANGLE
		g.setColor(ctrl.COL_FOND);
 
		g.setColor(ctrl.COL_GRAPH);
		g.drawRect(TEXT_ZONE_X, TEXT_ZONE_Y, TEXT_ZONE_WIDTH, TEXT_ZONE_HEIGHT);
 
 
		if(showText && motTrouve){
			//affichage de BRAVO
			g.setFont(MOT_FONT);
			switch(count){
			case 1:
				g.setColor(ctrl.COL_MOT_TROUVE1);
				break;
			case 2:
				g.setColor(ctrl.COL_MOT_TROUVE2);
				break;
			case 3:
				g.setColor(ctrl.COL_MOT_TROUVE3);
				break;
			case 4:
				g.setColor(ctrl.COL_MOT_TROUVE4);
				break;
			case 5:
				g.setColor(ctrl.COL_MOT_TROUVE5);
				break;
			case 6:
				g.setColor(ctrl.COL_MOT_TROUVE6);
				break;
			case 7:
				g.setColor(ctrl.COL_MOT_TROUVE7);
				break;
			case 8:
				g.setColor(ctrl.COL_MOT_TROUVE8);
				break;
			case 9:
				g.setColor(ctrl.COL_MOT_TROUVE9);
				count = 0;
				break;
			}
 
			//je ne sais pas pourqupi il a fallu diviser le g.getFontMetrics().getHeight() par 3 ... je m'attendais plutôt à diviser par 2 mais ca passe mieux avec 3
			g.drawString(ctrl.FELICIT_MOT_TROUVE, TEXT_ZONE_WIDTH / 2 + 10 - g.getFontMetrics().stringWidth(ctrl.FELICIT_MOT_TROUVE) / 2, TEXT_ZONE_HEIGHT / 2 + 25 + g.getFontMetrics().getHeight() / 3);
			count++;
		} 
 
		else {			
			//affichage du mot caché
			g.setColor(ctrl.COL_MOT);
			g.setFont(MOT_FONT);
 
			int decal = 0;			
			for(int caractere = 0; caractere < ctrl.getListeLettres().size(); caractere++){
				g.drawString("" + ctrl.getListeLettres().get(caractere), MOT_X + decal, MOT_Y);
				decal += (g.getFontMetrics().stringWidth("" + ctrl.getListeLettres().get(caractere)) + 15);
			}
		}
	}
 
	public synchronized void panelAffichFelicit(int time) {
		final int showTextTimer = ++showTextCall; // ça c'est juste pour si on rappelle showText alors qu'il y a un texte déjà affiché
		final Timer timer = new Timer(time, e-> { if ( showTextTimer==showTextCall) hideText(); });
		timer.setRepeats(false);
		showText=true;
		addNotify();
		timer.start();
	}
 
	public synchronized void hideText() {
		if ( showText ) {
			showText=false;
			ctrl.apresMotTrouve();
		}
	}
 
	private ActionListener animatorTask = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			repaint();
		}
	};
 
	public void addNotify() {
		super.addNotify();
		animator = new Timer(300, animatorTask);
		animator.start();
	}
 
	//***********************************************************GETTERS & SETTERS
	public boolean isMotTrouve() {return motTrouve;}
	public void setMotTrouve(boolean motTrouve) {this.motTrouve = motTrouve;}
}