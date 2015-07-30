package com.francky.lettres.ctrl;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ListenerClavier implements KeyListener {

	Controleur ctrl;
	
	public ListenerClavier(Controleur ctrl) {
		this.ctrl = ctrl;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		System.out.println("key pressed : " + e.getKeyChar());
		switch(e.getKeyChar()){
		case 'A':
			//griser les boutons lorsqu'ils ont été cliqués
		case 'B':
		case 'C':
		case 'D':
		case 'E':
		case 'F':
		case 'G':
		case 'H':
		case 'I':
		case 'J':
		case 'K':
		case 'L':
		case 'M':
		case 'N':
		case 'O':
		case 'P':
		case 'Q':
		case 'R':
		case 'S':
		case 'T':
		case 'U':
		case 'V':
		case 'W':
		case 'X':
		case 'Y':
		case 'Z':
			System.out.println(e.getKeyChar() + " key pressed");
			ctrl.btnGriseur("" + e.getKeyChar());
			ctrl.searchLetter("" + e.getKeyChar());
			break;
			
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		System.out.println("key pressed : " + e.getKeyChar());

	}

	@Override
	public void keyTyped(KeyEvent e) {

		System.out.println("key typed : " + e.getKeyChar());
	}

}
