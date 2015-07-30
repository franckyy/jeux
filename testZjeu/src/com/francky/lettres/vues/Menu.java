package com.francky.lettres.vues;

import javax.swing.JMenu;
import javax.swing.JMenuBar;

public class Menu extends JMenuBar{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4451367752330474585L;
	
	JMenu menu1, sousmenu1;
	
	public Menu(FenetrePrincipale fenetreprincipale) {
		menu1 = new JMenu("Affichage");

		this.add(menu1);
		
		sousmenu1 = new JMenu("Themes couleurs");
		
		menu1.add(sousmenu1);
		
		sousmenu1.add("theme1");
		sousmenu1.add("theme2");
		sousmenu1.add("theme3");
		sousmenu1.add("theme4");
		sousmenu1.add("theme5");
		
		fenetreprincipale.setJMenuBar(this);
	}
}
