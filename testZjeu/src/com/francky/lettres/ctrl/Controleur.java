package com.francky.lettres.ctrl;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Vector;

import com.francky.lettres.modele.CouleurThemes;
import com.francky.lettres.modele.Mot;
import com.francky.lettres.modele.MotDAO;
import com.francky.lettres.vues.FenetrePrincipale;

public class Controleur {

	//***********************************************************DECLARATIONS
	FenetrePrincipale fenetreprincipale;
	MotDAO motdao = new MotDAO("mots.xml");
	Vector<Mot> mots = null;		//contenant de tous les objets Mot
	public boolean debug;			//variable pour le débuggage
	private int randomNum;			//variable contenant le numéro trouvé par le random
	/*
	 * Gestion du score et des statistiques
	 */
	private int score;
	private int motsTrouves;
	private int nbreLettres;
	private int niveau;
	
	/*
	 * Gestion du mot à rechercher et à cacher
	 */
	private String mot;									//le mot qui a été choisi au hasard
	private StringBuilder motCache;							//le mot transformé pour affichage
	private char[] tabLettres;							//le tableau contenant toute les lettres du mot choisi au hasard
	private List<Character> listeLettresTrouvees;		//Liste des lettres qui ont été trouvées
	/*
	 * Définition de la palette des couleurs pour le jeu 
	 */
	
	public Color COL_FOND;
	public Color COL_TEXTE_1;
	public Color COL_TEXTE_2;
	public Color COL_MOT;
	public Color COL_GRAPH;
	
	//***********************************************************CONSTRUCTEUR
	public Controleur() {
		//************************************Initialisations
		resetGame();
		
		resetColors();		
		
		List<Character> listeLettresTrouvees = null;
		
		mots = motdao.chargerMots();
		
		randomNum = randomNum();
		
					//affichage dans console pour debug
					if (debug){
						for(Mot m : mots){
							System.out.println("mot : " + m.getChaine() + ", genre : " + m.isGenre() + ", composed : " + m.isComposed());
						}		
						System.out.println("randomNum = " + randomNum);
						System.out.println("le mot est : " + mots.get(randomNum).getChaine());
					}
		
		mot = mots.get(randomNum).getChaine();
		
		setTabLettres(motToLettersTab(mot));
		motCache = motCache(tabLettres);
		
		//************************************création de la fenêtre
		fenetreprincipale = new FenetrePrincipale(this);
		
		fenetreprincipale.setVisible(true);
	}
	
	private char[] motToLettersTab(String mot2) {
		char[] resultTab = mot.toCharArray();		
		return resultTab;
	}

	//***********************************************************METHODES
	//réinitialisation des couleurs
	private void resetColors() {
		//initialisation des couleurs - a l'avenir il faudra aller chercher le theme dans un .xml
		CouleurThemes coul = new CouleurThemes("theme1");
		
		COL_FOND = coul.getColComplementaire_5();
		COL_TEXTE_1 = coul.getColPrimaire_3();
		COL_TEXTE_2 = coul.getColPrimaire_2();
		COL_MOT = coul.getColPrimaire_3();
		COL_GRAPH = coul.getColPrimaire_1();
	}
	
	//remise à zéro des paramètres d'initialisation
	private void resetGame() {
		debug = true;
		setScore(0);
		setMotsTrouves(0);
		setNbreLettres(0);
		setNiveau(1);
	}
	
	//génération d'un entier au hasard compris entre 0 et les nombre de mots contenus dans la liste des mots en .xml
	private int randomNum(){
		Random rand = new Random();
		return rand.nextInt((mots.size()));
	}
	
	//création du mot qui sera affiché (on montre les lettres qui ont déjà été trouvées)
	private StringBuilder motCache(char[] tabLettres){
		motCache = new StringBuilder();
				
		for(Character caract : tabLettres){
			if(listeLettresTrouvees == null) {
				caract = '_';
			} else {
				//comparer les lettres contenues dans tabLettres aux lettres contenues dans listeLettresTrouvees
			}
			
			motCache.append(caract);
		}
		
		return motCache;
	}
	
	//***********************************************************GETTERS & SETTERS
	public int getScore() {return score;}
	public void setScore(int score) {this.score = score;}

	public int getMotsTrouves() {return motsTrouves;}
	public void setMotsTrouves(int motsTrouves) {this.motsTrouves = motsTrouves;}

	public int getNbreLettres() {return nbreLettres;}
	public void setNbreLettres(int nbreLettres) {this.nbreLettres = nbreLettres;}

	public int getNiveau() {return niveau;}
	public void setNiveau(int niveau) {this.niveau = niveau;}

	public String getMot() {return mot;}
	public void setMot(String mot) {this.mot = mot;}

	public char[] getTabLettres() {return tabLettres;}
	public void setTabLettres(char[] tabLettres) {this.tabLettres = tabLettres;}

	public StringBuilder getMotCache() {return motCache;}
	public void setMotCache(StringBuilder motCache) {this.motCache = motCache;}
}
