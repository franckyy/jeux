package com.francky.lettres.vues;

import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

import com.francky.lettres.ctrl.Controleur;
import com.francky.lettres.ctrl.ListenerClavier;
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
	private ListenerClavier keyListener;
	
	public FenetrePrincipale(Controleur ctrl) {
		//construction de la fen�tre
		super("Lettres");
		setLayout(new BorderLayout());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		
		this.ctrl = ctrl;
		keyListener = new ListenerClavier(ctrl);
		
		this.addKeyListener(keyListener);
		
		/*
		 * Initialisation des panneaux
		 */
		//cr�ation du clavier
		PanelKeyboard panelkeyboard = new PanelKeyboard(ctrl);
		//ajout du clavier � la fen�tre
		add(panelkeyboard, BorderLayout.CENTER);
		
		//Cr�ation du panneau de score
		PanelScore panelscore = new PanelScore(ctrl);
		add(panelscore, BorderLayout.EAST);
		
		//cr�ation du panneau d'affichage des mots
		PanelAffichage panelaffichage = new PanelAffichage(ctrl);
		add(panelaffichage, BorderLayout.NORTH);
		
		pack();
		setLocationRelativeTo(null);
	}
}
