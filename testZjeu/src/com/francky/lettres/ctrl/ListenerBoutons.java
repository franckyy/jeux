package com.francky.lettres.ctrl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ListenerBoutons implements ActionListener {

	//DECLARATIONS
	Controleur ctrl;
	
	//CONSTRUCTEUR
	public ListenerBoutons(Controleur ctrl) {
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
			System.out.println(e.getActionCommand() + " key pressed");
			ctrl.searchLetter(e.getActionCommand().toCharArray()[0]);
			break;
		case "AIDE":
			System.out.println("Help key pressed");
			break;
			
		}
	}

}
