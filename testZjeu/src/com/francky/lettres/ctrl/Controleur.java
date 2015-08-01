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
//	private BoutonsMap btnMap;		//Instance de BoutonsMap qui va m'aider � r�cup�rer les btn pour les griser / d�griser
	
	/*
	 * Gestion du score et des statistiques
	 */
	private int score;				//le score ...
	private int motsTrouves;		//le nombre de mots qui ont �t� trouv�s
	private int nbreLettres;		//le nbre de lettres qui ont �t� trouv�es
	private int nbreLettresUtilisees;	//le nbre de lettres qui ont �t� utilis�es
	private int nbreEssais;			//nombres de fois que l'on clique sur une lettre
	private int niveau;				//le niveau de difficult� du jeu
	
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
	public Color COL_MOT_TROUVE1;
	public Color COL_MOT_TROUVE2;
	public Color COL_MOT_TROUVE3;
	public Color COL_MOT_TROUVE4;
	public Color COL_MOT_TROUVE5;
	public Color COL_MOT_TROUVE6;
	public Color COL_MOT_TROUVE7;
	public Color COL_MOT_TROUVE8;
	public Color COL_MOT_TROUVE9;
	public Color COL_GRAPH;
	
	/*
	 * D�finition des scores
	 */
	private int GAIN_MOT_TROUVE = 100;
	
	/*
	 * Messages
	 */
	public String FELICIT_MOT_TROUVE = "BRAVO !";
	
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
	//***********************************************************METHODES
	
	//recherche d'une lettre cliqu�e dans le mot
	public void searchLetter(String lettreClic) {

		//incr�menter le nombre de lettres utilis�es ou cliqu�es
		setNbreLettresUtilisees(++nbreLettresUtilisees);
		
			if(mot.contains(lettreClic)){
				message = "lettre trouv�e !";
				
				setNbreLettres(++nbreLettres);		//incr�mentation du nombre de lettres trouv�es
				setNbreEssais(++nbreEssais);		//incr�mentation du nombre de fois que l'on a cliqu� une lettre
				listeLettresTrouvees.add(lettreClic.charAt(0));		//ajout de la lettre dans la liste des lettres trouv�es
				lettreTrouvee = lettreClic.charAt(0);		//sauvegarde de la lettre trouv�e
				
				int gain = scoreLettreTrouvee(lettreClic.charAt(0));	//calcul du gain suite au clic sur une lettre pr�sente dans le mot
				setScore(gain);
				
				
				//g�n�rer et r� afficher le mot cach�
				listeLettres = setlisteLettres(listeLettres);
				
				//v�rification pour savoir si le mot � �t� trouv�. 
				//si listeLettre ne contient pas  '_' alors le mot a �t� trouv�.
				if(!listeLettres.contains('_')){
					//si mot trouv�
					
					//incr�menter nbre de mots trouv�s
					setMotsTrouves(++motsTrouves);
					//remise � z�ro du nombre de lettres cliqu�es
					
					//affichage BRAVO !!!
					fenetreprincipale.motTrouveBool(true);
					listeLettres.clear(); 	//on vide la liste des lettres avant de remplir
					
					//on remplit la liste des lettres avec le mot de f�licitation
					for(int i = 0; i < FELICIT_MOT_TROUVE.length(); i++){
						listeLettres.add(FELICIT_MOT_TROUVE.charAt(i));
					}
					//on demande l'affichage du mot de f�licitation (avec clignotment cod� dans panelAffichage) pendant xxx millisecondes
					fenetreprincipale.PanelAffichFelicit();
					
					//le gain du mot trouv� est ajout� au score
					gain = scoreMotTrouve();
					setScore(gain);
					
					//On repasse le bool�en motTrouv� � false pour que le panelAffichage affiche le mot normalement
					fenetreprincipale.motTrouveBool(false);
					//on remet � z�ro la liste des lettres du mot � afficher
					listeLettres.clear();
					//on remet � z�ro le nombre d'essais
					nbreEssais = 0;
					//on d�grise les touches
					btnGriseur("all", true);
					//on remet les compteur du nombre de lettres trouv�es � z�ro
					setNbreLettresUtilisees(0);
					//nouveau mot
					choixNouveauMot();
					repaintPanelAffich();
				}
			} else {
				message = "lettre non trouv�e !";
				setNbreEssais(++nbreEssais);	//incr�mentation du nombre de fois que l'on a cliqu� une lettre
			}		

			fenetreprincipale.repaint();
	}
	
	
	private void repaintPanelAffich() {
		fenetreprincipale.repaintPanelAffich();
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
	
	private void stopGame() {
		//enregistrer score si high score
		//afficher mot de fin
		//demander � taper sur une touche pour recommencer
	}

	//r�initialisation des couleurs
	public void resetColors(String theme) {
		//initialisation des couleurs - a l'avenir il faudra aller chercher le theme dans un .xml
		CouleurThemes coul = new CouleurThemes(theme);
		
		COL_FOND = coul.getColComplementaire_5();
		COL_TEXTE_1 = coul.getColPrimaire_3();
		COL_TEXTE_2 = coul.getColPrimaire_2();
		COL_MOT = coul.getColPrimaire_3();
		COL_MOT_TROUVE1 = coul.getColComplementaire_1();
		COL_MOT_TROUVE2 = coul.getColComplementaire_2();
		COL_MOT_TROUVE3 = coul.getColComplementaire_3();
		COL_MOT_TROUVE4 = coul.getColComplementaire_4();
		COL_MOT_TROUVE5 = coul.getColPrimaire_1();
		COL_MOT_TROUVE6 = coul.getColPrimaire_2();
		COL_MOT_TROUVE7 = coul.getColPrimaire_3();
		COL_MOT_TROUVE8 = coul.getColPrimaire_4();
		COL_MOT_TROUVE9 = coul.getColPrimaire_5();
		COL_GRAPH = coul.getColPrimaire_1();
		
	}
	
	//remise � z�ro des param�tres d'initialisation
	private void resetGame() {
		debug = true;
		setScore(0);
		setMotsTrouves(0);
		setNbreLettres(0);
		setNbreEssais(0);
		setNbreLettresUtilisees(0);
		setNiveau(1);
		chargementMots();
		listeLettres = new ArrayList<Character>();
		lettreTrouvee = ' ';
		ecouteur = new Ecouteur(this);
//		btnMap = new BoutonsMap(ecouteur);
		listeLettresTrouvees = new ArrayList<Character>();
	}
	
	//chargement des mots
	private void chargementMots() {
		mots = motdao.chargerMots();
	}
	
	//g�n�ration d'un entier au hasard compris entre 0 et les nombre de mots contenus dans la liste des mots en .xml
	private int randomNum() {
		Random rand = new Random();
		int numRank = 0;
		
		try {
			numRank =  rand.nextInt((mots.size()));
		}  
		catch (IllegalArgumentException argEx){System.out.println("Illegal Argument Exception capt�e");}
		catch (ArrayIndexOutOfBoundsException bounds) {System.out.println("Array Index Out Of Bounds Exception capt�e");}
		finally {
			//reset game
		}
		
		return numRank;
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
	public void btnGriseur(String btnValue, boolean boolGriser) {
		fenetreprincipale.griserBouton(btnValue, boolGriser);
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
	private int scoreMotTrouve(){
		int gain = 0;
		System.out.println("nbreEssais : " + nbreEssais + " / longueur mot : " + mot.length());
		
		//v�rification des lettres en doubles dans le mot
		//si lettres doubles, le nombre d'essais pour trouver le mot sans se tromper diminue d'autant plus
		int nbreLettresDoubles = 0;
		for(int i = 0; i < mot.length(); i++){
			for(int j = i + 1; j < mot.length(); j++) {
				if(Character.toString(mot.charAt(i)).equals(Character.toString(mot.charAt(j)))){
					nbreLettresDoubles++;
					break;
				}
			}
		}
		
		//si on trouve toutes les lettres sans se tromper -> ultra bonus 100pts
		if (nbreEssais == mot.length() - nbreLettresDoubles){
			gain = GAIN_MOT_TROUVE + 20;
		}
		
		int reste = nbreEssais - mot.length();
		
		switch(reste){
		case 1:
			gain = GAIN_MOT_TROUVE - 2;
			break;
		case 2:
			gain = GAIN_MOT_TROUVE - 4;
			break;
		case 3:
			gain = GAIN_MOT_TROUVE - 8;
			break;
		case 4:
			gain = GAIN_MOT_TROUVE - 15;
			break;
		case 5:
			gain = GAIN_MOT_TROUVE - 25;
			break;
		case 6:
			gain = GAIN_MOT_TROUVE - 40;
			break;
		case 7:
			gain = GAIN_MOT_TROUVE - 70;
			break;
		case 8:
			gain = GAIN_MOT_TROUVE - 90;
			break;
		}
		
		return gain;
	}
	
	private int scoreLettreTrouvee(Character lettre){
		int gain = 0;
		//On accorde un nombre de points par lettre trouv�e
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
	public void setScore(int gain) {this.score += gain;}

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

	public int getNbreLettresUtilisees() {return nbreLettresUtilisees;}
	public void setNbreLettresUtilisees(int nbreLettresUtilisees) {this.nbreLettresUtilisees = nbreLettresUtilisees;}
}
