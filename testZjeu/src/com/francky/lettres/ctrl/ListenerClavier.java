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
