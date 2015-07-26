package com.francky.lettres.ctrl;

import com.francky.lettres.vues.FenetrePrincipale;
import com.francky.lettres.vues.panneaux.PanelKeyboard;

public class Controleur {

	//DECLARATIONS
	FenetrePrincipale fenetreprincipale;
	
	//CONSTRUCTEUR
	public Controleur() {
		//cr�ation de la fen�tre
		fenetreprincipale = new FenetrePrincipale();
		//cr�ation du clavier
		PanelKeyboard panelkeyboard = new PanelKeyboard(fenetreprincipale);
		//ajout du clavier � la fen�tre
		fenetreprincipale.add(panelkeyboard);
		
		
		fenetreprincipale.setVisible(true);
	}
	
	//METHODES
}
