package com.francky.lettres.modele;

import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JLabel;

public class BoutonsMap {

	/**
	 * 
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JFrame;
 
public class Test {
 
	public static void main(String[] args) {
		final JButton btn = new JButton("Clear");
		final Font fontDefaut = btn.getFont();
		final Font fontEntered = new Font(Font.DIALOG, Font.ITALIC, 10);
 
		JFrame f = new JFrame();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
		btn.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) { }
			@Override
			public void mouseEntered(MouseEvent e) {
				btn.setBackground(Color.RED);
				btn.setFont(fontEntered);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btn.setBackground(null); // d�faut
				btn.setFont(fontDefaut);
			}
			@Override
			public void mousePressed(MouseEvent e) { }
			@Override
			public void mouseReleased(MouseEvent e) { }
		});
 
		f.add(btn);
 
		f.pack();
		f.setVisible(true);
	}
 
}
	 */
	private static final long serialVersionUID = -6725907661307041765L;

	//DECLARATIONS
	private HashMap<Character, JButton> boutons;
	
	JButton btnA, btnB, btnC, btnD, btnE, btnF, btnG, btnH, btnI, btnJ, btnK, btnL
				, btnM, btnN, btnO, btnP, btnQ, btnR, btnS, btnT, btnU, btnV, btnW
				, btnX, btnY, btnZ, btnHelp;
	
	JButton btnVide, btnVide1, btnVide2;
	
	//CONSTRUCTEUR
	public BoutonsMap() {
		super();
	}
	
	//METHODES
	public HashMap<Character, JButton> remplissageBoutons(){
		btnA = new JButton("A");
		btnB = new JButton("B");
		btnC = new JButton("C");
		btnD = new JButton("D");
		btnE = new JButton("E");
		btnF = new JButton("F");
		btnG = new JButton("G");
		btnH = new JButton("H");
		btnI = new JButton("I");
		btnJ = new JButton("J");
		btnK = new JButton("K");
		btnL = new JButton("L");
		btnM = new JButton("M");
		btnN = new JButton("N");
		btnO = new JButton("O");
		btnP = new JButton("P");
		btnQ = new JButton("Q");
		btnR = new JButton("R");
		btnS = new JButton("S");
		btnT = new JButton("T");
		btnU = new JButton("U");
		btnV = new JButton("V");
		btnW = new JButton("W");
		btnX = new JButton("X");
		btnY = new JButton("Y");
		btnZ = new JButton("Z");
		btnHelp = new JButton("AIDE");
		
		btnVide = new JButton("");
		btnVide1 = new JButton("");
		btnVide2 = new JButton("");
		
		boutons = new HashMap<Character, JButton>();
		
		boutons.put('A', btnA);
		boutons.put('B', btnB);
		boutons.put('C', btnC);
		boutons.put('D', btnD);
		boutons.put('E', btnE);
		boutons.put('F', btnF);
		boutons.put('G', btnG);
		boutons.put('H', btnH);
		boutons.put('I', btnI);
		boutons.put('J', btnJ);
		boutons.put('K', btnK);
		boutons.put('L', btnL);
		boutons.put('M', btnM);
		boutons.put('N', btnN);
		boutons.put('O', btnO);
		boutons.put('P', btnP);
		boutons.put('Q', btnQ);
		boutons.put('R', btnR);
		boutons.put('S', btnS);
		boutons.put('T', btnT);
		boutons.put('U', btnU);
		boutons.put('V', btnV);
		boutons.put('W', btnW);
		boutons.put('X', btnX);
		boutons.put('Y', btnY);
		boutons.put('Z', btnZ);
		boutons.put('h', btnHelp);
		boutons.put('a', btnVide);
		boutons.put('b', btnVide1);
		boutons.put('c', btnVide2);
		
		return boutons;
	}
}