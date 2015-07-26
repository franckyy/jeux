package com.francky.lettres.vues.panneaux;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

import com.francky.lettres.vues.FenetrePrincipale;

public class PanelKeyboard extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4553507983435786002L;
	
	//************************************************************DECLARATIONS
	
	/*
	 * Nombre de touches dans le clavier
	 */
	private static final int ROWS = 6;
	private static final int COLS = 7;
	
	/*
	 * hauteur et largeur d'une touche de clavier en pixel
	 */
	private static final int KEY_HEIGHT = 50;
	private static final int KEY_WIDTH = 50;
	
	/*
	 * Marge autour d'une touche de clavier
	 */
	private static final int KEY_MARGE = 5;
	
	/*
	 * Calcul de la hauteur du panneaux clavier
	 * nombre de colonnes * hauteur d'une colonne + marge
	 */
	private static final int KEYBOARD_HEIGTH = ROWS * KEY_HEIGHT + KEY_MARGE;
	private static final int KEYBOARD_WIDTH = COLS * KEY_WIDTH + KEY_MARGE;
	
	/*
	 * Instanciation de la fenêtre principale
	 */
	private FenetrePrincipale fenetreprincipale;
	
	//************************************************************CONSTUCTEUR
	public PanelKeyboard(FenetrePrincipale fenetreprincipale) {
		this.fenetreprincipale = fenetreprincipale;
		
		setPreferredSize(new Dimension(KEYBOARD_WIDTH, KEYBOARD_HEIGTH));
		setBackground(Color.BLACK);
	}
	
	//************************************************************METHODES
}
