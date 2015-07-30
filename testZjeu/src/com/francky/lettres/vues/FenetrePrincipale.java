package com.francky.lettres.vues;

import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

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
	private JMenuBar barre;
	private JMenu menu1, sousmenu1;
	
	public FenetrePrincipale(Controleur ctrl) {
		//construction de la fenêtre
		super("Lettres");
		setLayout(new BorderLayout());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		
		this.ctrl = ctrl;
		
		
		// ajout d'un menu
		barre = new JMenuBar();
		menu1 = new JMenu("Affichage");
		barre.add(menu1);
		
		sousmenu1 = new JMenu("Themes couleurs");
		menu1.add(sousmenu1);
		
		sousmenu1.add("theme1");
		sousmenu1.add("theme2");
		sousmenu1.add("theme3");
		sousmenu1.add("theme4");
		sousmenu1.add("theme5");
		
		setJMenuBar(barre);
		/*
		 * Initialisation des panneaux
		 */
		//création du clavier
		PanelKeyboard panelkeyboard = new PanelKeyboard(ctrl);
		//ajout du clavier à la fenêtre
		add(panelkeyboard, BorderLayout.CENTER);
		
		//Création du panneau de score
		PanelScore panelscore = new PanelScore(ctrl);
		add(panelscore, BorderLayout.EAST);
		
		//création du panneau d'affichage des mots
		PanelAffichage panelaffichage = new PanelAffichage(ctrl);
		add(panelaffichage, BorderLayout.NORTH);
		
		pack();
		setLocationRelativeTo(null);
	}
}
