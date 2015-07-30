package com.francky.lettres.vues;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import com.francky.lettres.ctrl.Controleur;
import com.francky.lettres.vues.panneaux.PanelAffichage;
import com.francky.lettres.vues.panneaux.PanelKeyboard;
import com.francky.lettres.vues.panneaux.PanelScore;

public class FenetrePrincipale extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -221472257022120727L;
	
	//********************************************************DECLARATIONS
	private Controleur ctrl;
	Menu menu;
	PanelAffichage panelaffichage;
	PanelScore panelscore;
	PanelKeyboard panelkeyboard;
	
	public FenetrePrincipale(Controleur ctrl) {
		//construction de la fenêtre
		super("Lettres");
		setLayout(new BorderLayout());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		
		this.ctrl = ctrl;
		
		
		// ajout d'un menu
		menu = new Menu(this, ctrl);
		/*
		 * Initialisation des panneaux
		 */
		//création du clavier
		panelkeyboard = new PanelKeyboard(ctrl);
		//ajout du clavier à la fenêtre
		add(panelkeyboard, BorderLayout.CENTER);
		
		//Création du panneau de score
		panelscore = new PanelScore(ctrl);
		add(panelscore, BorderLayout.EAST);
		
		//création du panneau d'affichage des mots
		panelaffichage = new PanelAffichage(ctrl);
		add(panelaffichage, BorderLayout.NORTH);
		
		pack();
		setLocationRelativeTo(null);
	}
	
	//demande la modification de la couleur de fond pour tous les panneaux
	public void modifieBackgrounds() {
		panelaffichage.modifieBackgroundColor();
		panelkeyboard.modifieBackgroundColor();
		panelscore.modifieBackgroundColor();
	}
}
