package com.francky.lettres.modele;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JButton;

import com.francky.lettres.ctrl.Controleur;
import com.francky.lettres.ctrl.Ecouteur;
import com.francky.lettres.vues.panneaux.PanelKeyboard;

public class BoutonsMap {

	//DECLARATIONS
	PanelKeyboard panelkeyboard;
	Controleur ctrl;
	
	private HashMap<Character, JButton> boutons;
	
	private JButton btnA, btnB, btnC, btnD, btnE, btnF, btnG, btnH, btnI, btnJ, btnK, btnL
				, btnM, btnN, btnO, btnP, btnQ, btnR, btnS, btnT, btnU, btnV, btnW
				, btnX, btnY, btnZ, btnHelp;
	
	private JButton btnEsp, btnVide1, btnVide2;

	List<Character> lettres;				//Liste de toutes les lettres de l'alphabet
	
	//CONSTRUCTEUR
	public BoutonsMap(Ecouteur btnListener, PanelKeyboard panelkeyboard, Controleur ctrl) {
		super();
		this.ctrl = ctrl;
		boutons = new HashMap<Character, JButton>();
		this.panelkeyboard = panelkeyboard;
		boutons = remplissageBoutons();
		
		lettres = new ArrayList<Character>();	//instanciation de la liste des lettres
		
		//remplissage de la liste des lettres avec toutes les lettres de l'alphabet
		for(char alphabet = 'A'; alphabet <= 'Z';alphabet++) {
		    lettres.add(alphabet);
		}
		
		lettres.add(' ');
		lettres.add('b');
		lettres.add('c');
		lettres.add('h');
		
		for(int i = 0; i < boutons.size(); i++){
			panelkeyboard.add(boutons.get(lettres.get(i)));
			boutons.get(lettres.get(i)).setBackground(ctrl.COL_FOND_BOUTONS_NON_CLIC);
			boutons.get(lettres.get(i)).setForeground(ctrl.COL_TEXT_BOUTONS_NON_CLIC);
			boutons.get(lettres.get(i)).addActionListener(btnListener);
			boutons.get(lettres.get(i)).setActionCommand("" + lettres.get(i));
		}
		
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
		
		btnEsp = new JButton("Esp.");
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
		boutons.put(' ', btnEsp);
		boutons.put('b', btnVide1);
		boutons.put('c', btnVide2);
		
		return boutons;
	}
	
	public HashMap<Character, JButton> getBoutons() {
		return boutons;
	}
	
	public JButton getJBouton(String btnValue) {	
		return boutons.get(btnValue.charAt(0));
	}

	public void griserBouton(String btnValue, boolean boolGriser) {		
		if(btnValue.equals("all")){
			for(JButton btn : boutons.values()){
				btn.setEnabled(!boolGriser);
				if(boolGriser){
					btn.setBackground(ctrl.COL_FOND_BOUTONS_CLIC);
					btn.setForeground(ctrl.COL_TEXT_BOUTONS_CLIC);
				} else {
					btn.setBackground(ctrl.COL_FOND_BOUTONS_NON_CLIC);
					btn.setForeground(ctrl.COL_TEXT_BOUTONS_NON_CLIC);
				}
			}
		} else {
			this.getJBouton(btnValue).setEnabled(!boolGriser);
			if(boolGriser){
				this.getJBouton(btnValue).setBackground(ctrl.COL_FOND_BOUTONS_CLIC);
				this.getJBouton(btnValue).setForeground(ctrl.COL_TEXT_BOUTONS_CLIC);
			} else {
				this.getJBouton(btnValue).setBackground(ctrl.COL_FOND_BOUTONS_NON_CLIC);
				this.getJBouton(btnValue).setForeground(ctrl.COL_TEXT_BOUTONS_NON_CLIC);
			}
		}
	}

	public void colorerTousBoutons() {
		for(JButton btn : boutons.values()){
			if(btn.isEnabled()){
				btn.setBackground(ctrl.COL_FOND_BOUTONS_NON_CLIC);
				btn.setForeground(ctrl.COL_TEXT_BOUTONS_NON_CLIC);
			} else {
				btn.setBackground(ctrl.COL_FOND_BOUTONS_CLIC);
				btn.setForeground(ctrl.COL_TEXT_BOUTONS_CLIC);
			}
		}
	}
	
	//GETTERS & SETTERS
	public JButton getBtnA() {return btnA;}
	public JButton getBtnB() {return btnB;}
	public JButton getBtnC() {return btnC;}
	public JButton getBtnD() {return btnD;}
	public JButton getBtnE() {return btnE;}
	public JButton getBtnF() {return btnF;}
	public JButton getBtnG() {return btnG;}
	public JButton getBtnH() {return btnH;}
	public JButton getBtnI() {return btnI;}
	public JButton getBtnJ() {return btnJ;}
	public JButton getBtnK() {return btnK;}
	public JButton getBtnL() {return btnL;}
	public JButton getBtnM() {return btnM;}
	public JButton getBtnN() {return btnN;}
	public JButton getBtnO() {return btnO;}
	public JButton getBtnP() {return btnP;}
	public JButton getBtnQ() {return btnQ;}
	public JButton getBtnR() {return btnR;}
	public JButton getBtnS() {return btnS;}
	public JButton getBtnT() {return btnT;}
	public JButton getBtnU() {return btnU;}
	public JButton getBtnV() {return btnV;}
	public JButton getBtnW() {return btnW;}
	public JButton getBtnX() {return btnX;}
	public JButton getBtnY() {return btnY;}
	public JButton getBtnZ() {return btnZ;}
	public JButton getBtnHelp() {return btnHelp;}
	public JButton getBtnVide() {return btnEsp;}
	public JButton getBtnVide1() {return btnVide1;}
	public JButton getBtnVide2() {return btnVide2;}
}
