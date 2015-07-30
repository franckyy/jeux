package com.francky.lettres.modele;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JButton;
import javax.xml.crypto.dsig.keyinfo.KeyValue;

public class BoutonsMap {

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

		boutons = new HashMap<Character, JButton>();
		boutons = remplissageBoutons();
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
	
	public HashMap<Character, JButton> getBoutons() {
		return boutons;
	}
	
	public JButton getJBouton(String btnValue) {		
		System.out.println("grisage boutons");
		return boutons.get(btnValue.charAt(0));
	}

	public void griserBouton(JButton jBouton) {
//		jBouton.setEnabled(false);
		this.btnA.setEnabled(false);
	}
}
