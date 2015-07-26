package com.francky.lettres.ctrl;

import com.francky.lettres.vues.FenetrePrincipale;
import com.francky.lettres.vues.panneaux.PanelKeyboard;

public class Controleur {

	//DECLARATIONS
	FenetrePrincipale fenetreprincipale;
	
	//CONSTRUCTEUR
	public Controleur() {
		//création de la fenêtre
		fenetreprincipale = new FenetrePrincipale();
		
		
		
		fenetreprincipale.setVisible(true);
	}
	
	//METHODES
}
