package com.francky.lettres.vues.panneaux;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JButton;
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
	private static final int ROWS_NUMBER = 6;
	private static final int COLS_NUMBER = 7;
	
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
	private static final int KEYBOARD_HEIGTH = ROWS_NUMBER * KEY_HEIGHT + KEY_MARGE;
	private static final int KEYBOARD_WIDTH = COLS_NUMBER * KEY_WIDTH + KEY_MARGE;
	
	/*
	 * Déclaration des boutons du clavier et d'une HashMap
	 */
	
	HashMap<Character, JButton> boutons;
	BoutonsMap bm;
	List<Character> lettres;
	
	/*
	 * Instanciation de la fenêtre principale
	 */
	private FenetrePrincipale fenetreprincipale;
	
	//************************************************************CONSTUCTEUR
	public PanelKeyboard(FenetrePrincipale fenetreprincipale) {
		this.fenetreprincipale = fenetreprincipale;
		
		setPreferredSize(new Dimension(KEYBOARD_WIDTH, KEYBOARD_HEIGTH));
		setBackground(Color.BLACK);
		
		//paramétrage du layout du clavier
		setLayout(new GridLayout(ROWS_NUMBER, COLS_NUMBER, KEY_MARGE, KEY_MARGE));

		//Construction des boutons du clavier
		bm = new BoutonsMap();
		boutons = bm.remplissageBoutons();
		lettres= new ArrayList<Character>();
		
		for(char alphabet = 'A'; alphabet <= 'Z';alphabet++) {
		    lettres.add(alphabet);
		}
		lettres.add('h');
		
		for(int i = 0; i < boutons.size(); i++){
			add(boutons.get(lettres.get(i)));
		}
	}
	
	//************************************************************METHODES
}
