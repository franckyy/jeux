package com.francky.lettres.ctrl;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Vector;

import com.francky.lettres.modele.BoutonsMap;
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
	private BoutonsMap btnMap;		//Instance de BoutonsMap qui va m'aider à récupérer les btn pour les griser / dégriser
	
	/*
	 * Gestion du score et des statistiques
	 */
	private int score;				//le score ...
	private int motsTrouves;		//le nombre de mots qui ont été trouvés
	private int nbreLettres;		//le nbre de lettres qui ont été trouvées
	private int nbreEssais;			//nombres de fois que l'on clique sur une lettre
	private int niveau;				//le niveau de difficulté du jeu
	
	/*
	 * Message d'information
	 */
	public String message;
	
	/*
	 * Gestion du mot à rechercher et à cacher
	 */
	private String mot;											//le mot qui a été choisi au hasard
	private ArrayList<Character> listeLettres;					//le tableau contenant toute les lettres du mot choisi au hasard
	private List<Character> listeLettresTrouvees;				//Liste des lettres qui ont été trouvées
	private Character lettreTrouvee;							//dernière lettre trouvée

	private ListenerClavier keyListener;
	
	/*
	 * Définition de la palette des couleurs pour le jeu 
	 */
	private String COLOR_THEME = "THEME3";	//THEME1 à THEME5
	public Color COL_FOND;
	public Color COL_TEXTE_1;
	public Color COL_TEXTE_2;
	public Color COL_MOT;
	public Color COL_GRAPH;
	
	
	Ecouteur ecouteur;
	
	//***********************************************************CONSTRUCTEUR
	
	public Controleur() {
		//************************************Initialisations
		resetGame();
		
		resetColors(COLOR_THEME);		
		
		keyListener = new ListenerClavier(this);
		
		choixNouveauMot();
		
		//************************************création de la fenêtre
		fenetreprincipale = new FenetrePrincipale(this);

		fenetreprincipale.addKeyListener(keyListener);
		fenetreprincipale.setVisible(true);
		fenetreprincipale.requestFocus();
	}
	//***********************************************************METHODES
	
	//recherche d'une lettre cliquée dans le mot
	public void searchLetter(Character lettreClic) {
		for(Character lettre : mot.toCharArray()) {
			if(lettre.equals(lettreClic)){
				message = "lettre trouvée !";
				setNbreLettres(++nbreLettres);
				setNbreEssais(++nbreEssais);
				listeLettresTrouvees.add(lettreClic);
				lettreTrouvee = lettreClic;
				int gain = scoreLettreTrouvee(lettreClic);
				System.out.println("le gain : " + gain);
				
				//générer et ré afficher le mot caché
				listeLettres = setlisteLettres(listeLettres);
				fenetreprincipale.repaint();
				break;
			} else {
				message = "lettre non trouvée !";
				setNbreEssais(++nbreEssais);}
		}
	}
	
	
	//Choix d'un nouveau mot parmi la liste, on retire le mot choisi de la liste,
	//on génère le tableau de lettres du mot choisi, on génère le mot caché 
	private void choixNouveauMot() {
		
		randomNum = randomNum();
		
		mot = mots.get(randomNum).getChaine();
		
		//on enleve de la liste le mot qui a été tiré au hazard pour qu'il ne soit pas choisi au hasard plusieurs fois
		mots.remove(randomNum);
		
		//lorsque le nouveau mot a été choisi, il faut remplir un tableau de Character avec des underscores
		for(int rank = 0; rank < mot.length(); rank++){
			listeLettres.add('_');
		}
	}
	
	//réinitialisation des couleurs
	public void resetColors(String theme) {
		//initialisation des couleurs - a l'avenir il faudra aller chercher le theme dans un .xml
		CouleurThemes coul = new CouleurThemes(theme);
		
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
		setNbreEssais(0);
		setNiveau(1);
		listeLettres = new ArrayList<Character>();
		lettreTrouvee = ' ';
		ecouteur = new Ecouteur(this);
		btnMap = new BoutonsMap(ecouteur);
		listeLettresTrouvees = new ArrayList<Character>();
		mots = motdao.chargerMots();
	}
	
	//génération d'un entier au hasard compris entre 0 et les nombre de mots contenus dans la liste des mots en .xml
	private int randomNum(){
		Random rand = new Random();
		return rand.nextInt((mots.size()));
	}
	
	//création du mot qui sera affiché (on montre les lettres qui ont déjà été trouvées) prend en paramètre la liste
	private ArrayList<Character> setlisteLettres(ArrayList<Character> listeLettres){	
		for (int rank = 0; rank < listeLettres.size(); rank++){
			if(listeLettres.get(rank).equals('_')){
				if(lettreTrouvee.equals(mot.charAt(rank))){
					listeLettres.set(rank, lettreTrouvee);
				}
			}
		}		
		
		return listeLettres;
	}
	
	//griseur de boutons
	public void btnGriseur(String btnValue) {
		btnMap.griserBouton(btnMap.getJBouton(btnValue));
	}
	
	//repaint
	public void rafraichiJeu() {
		fenetreprincipale.modifieBackgrounds();
		
	}
	
	//modifieScore
	public void modifieScore(int ajout) {
		setScore(getScore() + ajout);
	}
	
	//******************METHODES POUR LE SCORE
	private int scoreLettreTrouvee(Character lettre){
		int gain = 0;
		//On accorde un nombre de points par lettre trouvée
		//ce nbre de points est similaire aux points du scrabble
		switch(lettre){
		case 'Z':
		case 'Y':
		case 'X':
		case 'W':
		case 'K':
			gain = 10;
			break;
		case 'Q':
		case 'J':
			gain = 8;
			break;
		case 'V':
		case 'H':
		case 'F':
			gain = 4;
			break;
		case 'P':
		case 'C':
		case 'B':
			gain = 3;
			break;
		case 'M':
		case 'G':
		case 'D':
			gain = 2;
			break;
		case 'U':
		case 'T':
		case 'S':
		case 'R':
		case 'O':
		case 'N':
		case 'L':
		case 'I':
		case 'E':
		case 'A':
			gain = 1;
			break;
		}
		
		//multiplier par un coeff en fonction du nombre d'essais
		//1er essai = X2O
		//2eme essai = X18
		//3eme essai = X16
		//4eme essai = X14
		//5eme essai = X12 .... etc
		int coeff = 1;
		int essais = getNbreEssais();
		switch(essais){
		case 1:
			coeff = 20;
			break;
		case 2:
			coeff = 18;
			break;
		case 3:
			coeff = 16;
			break;
		case 4:
			coeff = 14;
			break;
		case 5:
			coeff = 12;
			break;
		case 6:
			coeff = 10;
			break;
		case 7:
			coeff = 9;
			break;
		case 8:
			coeff = 8;
			break;
		case 9:
			coeff = 7;
			break;
		case 10:
			coeff = 6;
			break;
		case 11:
			coeff = 5;
			break;
		case 12:
			coeff = 4;
			break;
		case 13:
			coeff = 3;
			break;
		case 14:
			coeff = 2;
			break;
		case 15:
			coeff = 1;
			break;
		case 16:
			coeff = 1;
			break;
		case 17:
			coeff = 1;
			break;
		case 18:
			coeff = 1;
			break;
		case 19:
			coeff = 1;
			break;
		case 20:
			coeff = 1;
			break;
		}
		
		return gain * coeff;
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

	public List<Character> getListeLettres() {return listeLettres;}

	public int getNbreEssais() {return nbreEssais;}
	public void setNbreEssais(int nbreEssais) {this.nbreEssais = nbreEssais;}
}
