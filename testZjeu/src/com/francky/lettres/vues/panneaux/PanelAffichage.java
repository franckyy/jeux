package com.francky.lettres.vues.panneaux;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

import com.francky.lettres.ctrl.Controleur;

public class PanelAffichage extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8624942466374820397L;

	//***********************************************************DECLARATIONS
	private Controleur ctrl;
	int x = 0;
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
	//***********************************************************GETTERS & SETTERS
	
	
	//***********************************************************METHODES
	//modifier le background du panel
	public void modifieBackgroundColor() {
		setBackground(ctrl.COL_FOND);
	}
	
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.setColor(ctrl.COL_FOND);
		
		g.setColor(ctrl.COL_GRAPH);
		g.drawRect(TEXT_ZONE_X, TEXT_ZONE_Y, TEXT_ZONE_WIDTH, TEXT_ZONE_HEIGHT);
		
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
