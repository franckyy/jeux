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
	public PanelKeyboard panelkeyboard;
	
	public FenetrePrincipale(Controleur ctrl) {
		//construction de la fen�tre
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
		//cr�ation du clavier
		panelkeyboard = new PanelKeyboard(ctrl);
		//ajout du clavier � la fen�tre
		add(panelkeyboard, BorderLayout.CENTER);
		
		//Cr�ation du panneau de score
		panelscore = new PanelScore(ctrl);
		add(panelscore, BorderLayout.EAST);
		
		//cr�ation du panneau d'affichage des mots
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

	public void griserBouton(String btnValue, boolean boolGriser) {
		panelkeyboard.griserBouton(btnValue, boolGriser);
	}

	public void motTrouveBool(boolean b) {
		panelaffichage.setMotTrouve(b);
	}

	public void panelAffichDerniereLettre(int time) {
		panelaffichage.panelAffichDerniereLettre(time);		
	}
	
	public void PanelAffichFelicit(int time) {
		panelaffichage.panelAffichFelicit(time);
	}

	public void repaintPanelAffich() {
		panelaffichage.repaintPanelAffich();
	}

	public void repaintPanelScore() {
		panelscore.repaintPanelScore();
	}

	public void repaintPanelKeyboard() {
		panelkeyboard.repaintPanelKeyboard();
	}

	public void colorerTousBoutons() {
		panelkeyboard.colorerTousBoutons();
	}
}
