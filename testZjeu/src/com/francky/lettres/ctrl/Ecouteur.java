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
			//griser les boutons lorsqu'ils ont �t� cliqu�s
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
			System.out.println(e.getActionCommand() + " key pressed");
			ctrl.btnGriseur(e.getActionCommand());
			ctrl.searchLetter(e.getActionCommand().charAt(0));
			break;
		case "h":
			System.out.println("Help key pressed");
			break;
		case "THEME1":
		case "THEME2":
		case "THEME3":
		case "THEME4":
		case "THEME5":
			System.out.println("menuItem " + e.getActionCommand());
			break;
		}
	}

}