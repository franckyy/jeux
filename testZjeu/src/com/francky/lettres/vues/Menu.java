package com.francky.lettres.vues;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import com.francky.lettres.ctrl.Controleur;
import com.francky.lettres.ctrl.Ecouteur;

public class Menu extends JMenuBar{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4451367752330474585L;
	
	JMenu menuAffichage, ssmenuThemCoul;
	JMenuItem itemTheme1, itemTheme2, itemTheme3, itemTheme4, itemTheme5;
	Ecouteur menuListener;
	
	public Menu(FenetrePrincipale fenetreprincipale, Controleur ctrl) {
		menuListener = new Ecouteur(ctrl);
		menuAffichage = new JMenu("Affichage");

		this.add(menuAffichage);
		
		ssmenuThemCoul = new JMenu("Themes couleurs");
		menuAffichage.add(ssmenuThemCoul);
		
		itemTheme1 = new JMenuItem("th�me1");
		itemTheme2 = new JMenuItem("th�me2");
		itemTheme3 = new JMenuItem("th�me3");
		itemTheme4 = new JMenuItem("th�me4");
		itemTheme5 = new JMenuItem("th�me5");
		
		ssmenuThemCoul.add(itemTheme1);
		ssmenuThemCoul.add(itemTheme2);
		ssmenuThemCoul.add(itemTheme3);
		ssmenuThemCoul.add(itemTheme4);
		ssmenuThemCoul.add(itemTheme5);

		itemTheme1.addActionListener(menuListener);
		itemTheme1.setActionCommand("THEME1");
		itemTheme2.addActionListener(menuListener);
		itemTheme2.setActionCommand("THEME2");
		itemTheme3.addActionListener(menuListener);
		itemTheme3.setActionCommand("THEME3");
		itemTheme4.addActionListener(menuListener);
		itemTheme4.setActionCommand("THEME4");
		itemTheme5.addActionListener(menuListener);
		itemTheme5.setActionCommand("THEME5");
		
		fenetreprincipale.setJMenuBar(this);
	}

}