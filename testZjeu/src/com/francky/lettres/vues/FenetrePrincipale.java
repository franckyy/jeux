package com.francky.lettres.vues;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import com.francky.lettres.vues.panneaux.PanelKeyboard;

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
		add(panelkeyboard);
		
		pack();
		setLocationRelativeTo(null);
	}
}
