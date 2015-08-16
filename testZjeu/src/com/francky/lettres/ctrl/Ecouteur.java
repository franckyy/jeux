package com.francky.lettres.ctrl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Ecouteur implements ActionListener {

	//DECLARATIONS
	Controleur ctrl;
	
	//CONSTRUCTEUR
	public Ecouteur(Controleur ctrl) {
		super();
		this.ctrl = ctrl;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()){
		case "A":
		case "B":
		case "C":
		case "D":
		case "E":
		case "F":
		case "G":
		case "H":
		case "I":
		case "J":
		case "K":
		case "L":
		case "M":
		case "N":
		case "O":
		case "P":
		case "Q":
		case "R":
		case "S":
		case "T":
		case "U":
		case "V":
		case "W":
		case "X":
		case "Y":
		case "Z":
			ctrl.btnGriseur(e.getActionCommand(), true);
			ctrl.searchLetter(e.getActionCommand());
			break;
		case "h":
			break;
		case "THEME1":
		case "THEME2":
		case "THEME3":
		case "THEME4":
		case "THEME5":
			ctrl.resetColors(e.getActionCommand());
			ctrl.colorerTousBoutons();
			ctrl.rafraichiJeu();
			break;
		case "LANG_FR":
			ctrl.setLanguage("FR");
			ctrl.chargementMotsLang();
			break;
		case "LANG_EN":
			ctrl.setLanguage("EN");
			ctrl.chargementMotsLang();
			break;
		case "LANG_VN":
			ctrl.setLanguage("VN");
			ctrl.chargementMotsLang();
			break;
		}
	}

}
