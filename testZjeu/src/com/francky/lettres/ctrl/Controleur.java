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
	 * Message d'information
	 */
	public String message;
	
	/*
	 * Gestion du mot à rechercher et à cacher
	 */
	private String mot;											//le mot qui a été choisi au hasard
	private StringBuilder motCache;								//le mot transformé pour affichage
	private char[] tabLettres;									//le tableau contenant toute les lettres du mot choisi au hasard
	private List<Character> listeLettresTrouvees;		//Liste des lettres qui ont été trouvées
	
	/*
	 * Définition de la palette des couleurs pour le jeu 
	 */
	private String COLOR_THEME = "theme1";	//theme1 ou theme2
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
		
		choixNouveauMot();
		
		//************************************création de la fenêtre
		fenetreprincipale = new FenetrePrincipale(this);
		
		fenetreprincipale.setVisible(true);
	}
	
	private char[] motToLettersTab(String mot2) {
		char[] resultTab = mot.toCharArray();		
		return resultTab;
	}

	//***********************************************************METHODES
	
	//recherche d'une lettre cliquée dans le mot
	public void searchLetter(Character lettreClic) {
		for(Character lettre : tabLettres) {
			if(lettre.equals(lettreClic)){
				System.out.println("lettre trouvée !");
				message = "lettre trouvée !";
				listeLettresTrouvees.add(lettreClic);
			} else {
				System.out.println("Lettre non trouvée !");
				message = "lettre non trouvée !";
			}
		}
		
		//générer et ré afficher le mot caché
		motCache = motCache(tabLettres);
		
	}
	
	
	//Choix d'un nouveau mot parmi la liste, on retire le mot choisi de la liste,
	//on génère le tableau de lettres du mot choisi, on génère le mot caché 
	private void choixNouveauMot() {
		
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
		
		
		//on enleve de la liste le mot qui a été tiré au hazard pour qu'il ne soit pas choisi au hasard plusieurs fois
		mots.remove(randomNum);
		
					//affichage dans console pour debug
					if (debug){
						for(Mot m : mots){
							System.out.println("mot : " + m.getChaine() + ", genre : " + m.isGenre() + ", composed : " + m.isComposed());
						}		
					}
		
		setTabLettres(motToLettersTab(mot));
		motCache = motCache(tabLettres);
	}
	
	//réinitialisation des couleurs
	private void resetColors() {
		//initialisation des couleurs - a l'avenir il faudra aller chercher le theme dans un .xml
		CouleurThemes coul = new CouleurThemes(COLOR_THEME);
		
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
		List<Character> listeLettresTrouvees = new ArrayList<Character>();
		listeLettresTrouvees.add('e');
		mots = motdao.chargerMots();
	}
	
	//génération d'un entier au hasard compris entre 0 et les nombre de mots contenus dans la liste des mots en .xml
	private int randomNum(){
		Random rand = new Random();
		return rand.nextInt((mots.size()));
	}
	
	//création du mot qui sera affiché (on montre les lettres qui ont déjà été trouvées) prend en paramètre la liste
	private StringBuilder motCache(char[] tabLettres){
		motCache = new StringBuilder();
				
		for(Character caract : tabLettres){
			if(listeLettresTrouvees == null) {
				caract = '_';
			} else {
				//comparer les lettres contenues dans tabLettres aux lettres contenues dans listeLettresTrouvees
				for(char l : listeLettresTrouvees){
					if(caract.equals(l)){
						caract = l;
					}
						
				}
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
