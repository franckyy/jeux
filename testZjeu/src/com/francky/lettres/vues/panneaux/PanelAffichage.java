package com.francky.lettres.vues.panneaux;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

import com.francky.lettres.vues.FenetrePrincipale;

public class PanelAffichage extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8624942466374820397L;

	//***********************************************************DECLARATIONS
	private FenetrePrincipale fenetreprincipale;
	
	/*
	 * Taille du panneau affichage
	 */
	private static final int PANEL_WIDTH = PanelKeyboard.KEYBOARD_WIDTH + PanelScore.SCORE_WIDTH;
	private static final int PANEL_HEIGHT = 150;
	
	//***********************************************************CONSTRUCTEUR
	public PanelAffichage(FenetrePrincipale fenetreprincipale) {
		this.fenetreprincipale = fenetreprincipale;
		
		setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
		setBackground(Color.BLACK);
	}
	//***********************************************************GETTERS & SETTERS
	
	//***********************************************************METHODES
}
