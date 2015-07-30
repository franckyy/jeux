package com.francky.lettres.vues.panneaux;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.francky.lettres.ctrl.Controleur;
import com.francky.lettres.ctrl.Ecouteur;
import com.francky.lettres.ctrl.ListenerClavier;
import com.francky.lettres.modele.BoutonsMap;

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
	public static final int KEY_MARGE = 5;
	private static final int KEY_PANEL_MARGE_TOP = 5;
	private static final int KEY_PANEL_MARGE_BOTTOM = 5;
	private static final int KEY_PANEL_MARGE_LEFT = 5;
	private static final int KEY_PANEL_MARGE_RIGHT = 5;
	
	/*
	 * Calcul de la hauteur du panneaux clavier
	 * nombre de colonnes * hauteur d'une colonne + marge
	 */
	public static final int KEYBOARD_HEIGTH = ROWS_NUMBER * KEY_HEIGHT + KEY_MARGE;
	public static final int KEYBOARD_WIDTH = COLS_NUMBER * KEY_WIDTH + KEY_MARGE;
	
	/*
	 * Déclaration des boutons du clavier et d'une HashMap
	 */
	
	HashMap<Character, JButton> boutons;	//HashMap qui associe un JButton à une lettre de l'alphabet
	BoutonsMap bm;							//Création d'une instance de la classe BoutonsMap
	List<Character> lettres;				//Liste de toutes les lettres de l'alphabet
	
	/*
	 * Instanciation du controleur
	 */
	private Controleur ctrl;
	private Ecouteur btnListener;

	
	//************************************************************CONSTUCTEUR
	public PanelKeyboard(Controleur ctrl) {
		this.ctrl = ctrl;
		this.btnListener = new Ecouteur(ctrl);
		
		setPreferredSize(new Dimension(KEYBOARD_WIDTH, KEYBOARD_HEIGTH));
		setBackground(ctrl.COL_FOND);
		
		//paramétrage du layout du clavier
		setLayout(new GridLayout(ROWS_NUMBER, COLS_NUMBER, KEY_MARGE, KEY_MARGE));

		//Construction des boutons du clavier
		bm = new BoutonsMap();					//Instanciation de BoutonsMap
		
		boutons = bm.getBoutons();				//chargement de la hashmap des boutons
		lettres = new ArrayList<Character>();	//instanciation de la liste des lettres
			
		//remplissage de la liste des lettres avec toutes les lettres de l'alphabet
		for(char alphabet = 'A'; alphabet <= 'Z';alphabet++) {
		    lettres.add(alphabet);
		}
		
		lettres.add('a');
		lettres.add('b');
		lettres.add('c');
		lettres.add('h');
		
		for(int i = 0; i < boutons.size(); i++){
			add(boutons.get(lettres.get(i)));
			boutons.get(lettres.get(i)).addActionListener(btnListener);
			boutons.get(lettres.get(i)).setActionCommand("" + lettres.get(i));
		}
		
		//définition d'une marge autour du panneau clavier
		setBorder(new EmptyBorder(KEY_PANEL_MARGE_TOP, KEY_PANEL_MARGE_LEFT, KEY_PANEL_MARGE_BOTTOM, KEY_PANEL_MARGE_RIGHT));
	}
	
	//************************************************************METHODES
	//modifier le background du panel
	public void modifieBackgroundColor() {
		setBackground(ctrl.COL_FOND);
	}
}
