package com.francky.lettres.vues;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import com.francky.lettres.vues.panneaux.PanelAffichage;
import com.francky.lettres.vues.panneaux.PanelKeyboard;
import com.francky.lettres.vues.panneaux.PanelScore;

public class FenetrePrincipale extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -221472257022120727L;

	public FenetrePrincipale() {
		//construction de la fenêtre
		super("Lettres");
		setLayout(new BorderLayout());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		
		/*
		 * Initialisation des panneaux
		 */
		//création du clavier
		PanelKeyboard panelkeyboard = new PanelKeyboard(this);
		//ajout du clavier à la fenêtre
		add(panelkeyboard, BorderLayout.CENTER);
		
		//Création du panneau de score
		PanelScore panelscore = new PanelScore(this);
		add(panelscore, BorderLayout.EAST);
		
		//création du panneau d'affichage des mots
		PanelAffichage panelaffichage = new PanelAffichage(this);
		add(panelaffichage, BorderLayout.NORTH);
		
		pack();
		setLocationRelativeTo(null);
	}
}
