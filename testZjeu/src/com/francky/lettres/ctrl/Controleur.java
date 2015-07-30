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
	public boolean debug;			//variable pour le d�buggage
	private int randomNum;			//variable contenant le num�ro trouv� par le random
	private BoutonsMap btnMap;		//Instance de BoutonsMap qui va m'aider � r�cup�rer les btn pour les griser / d�griser
	
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
	 * Gestion du mot � rechercher et � cacher
	 */
	private String mot;											//le mot qui a �t� choisi au hasard
	private ArrayList<Character> listeLettres;					//le tableau contenant toute les lettres du mot choisi au hasard
	private List<Character> listeLettresTrouvees;				//Liste des lettres qui ont �t� trouv�es
	private Character lettreTrouvee;							//derni�re lettre trouv�e

	private ListenerClavier keyListener;
	
	/*
	 * D�finition de la palette des couleurs pour le jeu 
	 */
	private String COLOR_THEME = "THEME3";	//THEME1 � THEME5
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
		
		//************************************cr�ation de la fen�tre
		fenetreprincipale = new FenetrePrincipale(this);

		fenetreprincipale.addKeyListener(keyListener);
		fenetreprincipale.setVisible(true);
		fenetreprincipale.requestFocus();
	}
	
	private char[] motToLettersTab(String mot2) {
		char[] resultTab = mot.toCharArray();		
		return resultTab;
	}

	//***********************************************************METHODES
	
	//recherche d'une lettre cliqu�e dans le mot
	public void searchLetter(Character lettreClic) {
		for(Character lettre : mot.toCharArray()) {
			if(lettre.equals(lettreClic)){
				message = "lettre trouv�e !";
				listeLettresTrouvees.add(lettreClic);
				lettreTrouvee = lettreClic;
				
				//g�n�rer et r� afficher le mot cach�
				listeLettres = setlisteLettres(listeLettres);
				fenetreprincipale.repaint();
				break;
			} else {
			}
		}
	}
	
	
	//Choix d'un nouveau mot parmi la liste, on retire le mot choisi de la liste,
	//on g�n�re le tableau de lettres du mot choisi, on g�n�re le mot cach� 
	private void choixNouveauMot() {
		
		randomNum = randomNum();
		
		mot = mots.get(randomNum).getChaine();
		
		//on enleve de la liste le mot qui a �t� tir� au hazard pour qu'il ne soit pas choisi au hasard plusieurs fois
		mots.remove(randomNum);
		
		//lorsque le nouveau mot a �t� choisi, il faut remplir un tableau de Character avec des underscores
		for(int rank = 0; rank < mot.length(); rank++){
			listeLettres.add('_');
		}
	}
	
	//r�initialisation des couleurs
	public void resetColors(String theme) {
		//initialisation des couleurs - a l'avenir il faudra aller chercher le theme dans un .xml
		CouleurThemes coul = new CouleurThemes(theme);
		
		COL_FOND = coul.getColComplementaire_5();
		COL_TEXTE_1 = coul.getColPrimaire_3();
		COL_TEXTE_2 = coul.getColPrimaire_2();
		COL_MOT = coul.getColPrimaire_3();
		COL_GRAPH = coul.getColPrimaire_1();
		
	}
	
	//remise � z�ro des param�tres d'initialisation
	private void resetGame() {
		debug = true;
		setScore(0);
		setMotsTrouves(0);
		setNbreLettres(0);
		setNiveau(1);
		listeLettres = new ArrayList<Character>();
		lettreTrouvee = ' ';
		ecouteur = new Ecouteur(this);
		btnMap = new BoutonsMap(ecouteur);
		listeLettresTrouvees = new ArrayList<Character>();
		mots = motdao.chargerMots();
	}
	
	//g�n�ration d'un entier au hasard compris entre 0 et les nombre de mots contenus dans la liste des mots en .xml
	private int randomNum(){
		Random rand = new Random();
		return rand.nextInt((mots.size()));
	}
	
	//cr�ation du mot qui sera affich� (on montre les lettres qui ont d�j� �t� trouv�es) prend en param�tre la liste
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
}
