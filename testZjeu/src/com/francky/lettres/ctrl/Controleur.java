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
import com.francky.lettres.vues.panneaux.PanelScore;

public class Controleur {
	
	//*********************************************************************************************************************
	//***********************************************************DECLARATIONS
	//*********************************************************************************************************************
	
	
	FenetrePrincipale fenetreprincipale;
	MotDAO motdao = new MotDAO("mots.xml", this);
	Vector<Mot> mots = null;		//contenant de tous les objets Mot
	Vector<Mot> motsLang = null;		//contenant de tous les objets Mot relatif au language choisi
	public boolean debug;			//variable pour le débuggage
	private int randomNum;			//variable contenant le numéro trouvé par le random
	
	/*
	 * Gestion du score et des statistiques
	 */
	private int score;				//le score ...
	private int motsTrouves;		//le nombre de mots qui ont été trouvés
	private int nbreLettres;		//le nbre de lettres qui ont été trouvées
	private int nbreLettresUtilisees;	//le nbre de lettres qui ont été utilisées
	private int nbreEssais;			//nombres de fois que l'on clique sur une lettre
	private int niveau;				//le niveau de difficulté du jeu
	private String language = "FR";		//langue des mots du jeu
	
	/*
	 * Message d'information
	 */
	public String message;
	
	/*
	 * Gestion du mot à rechercher et à cacher
	 */
	private String strMot;										//le mot qui a été choisi au hasard
	private Vector<String> strMots;								// liste de mots type String
	private ArrayList<Character> listeLettres;					//le tableau contenant toute les lettres du mot choisi au hasard
	private List<Character> listeLettresTrouvees;				//Liste des lettres qui ont été trouvées
	private Character lettreTrouvee;							//dernière lettre trouvée
	private String strMotTemp;									//le mot en majuscule pour ne pas avoir les accents, cela permet de comparer avec les lettres écrites sur les JButton
	private ListenerClavier keyListener;
	
	/*
	 * Définition de la palette des couleurs pour le jeu 
	 */
	private String COLOR_THEME = "THEME3";	//THEME1 à THEME5
	public Color COL_FOND;
	public Color COL_FOND_BOUTONS_CLIC;
	public Color COL_TEXT_BOUTONS_CLIC;
	public Color COL_FOND_BOUTONS_NON_CLIC;
	public Color COL_TEXT_BOUTONS_NON_CLIC;
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
	 * Définition des scores
	 */
	private int GAIN_MOT_TROUVE = 100;
	
	/*
	 * Messages
	 */
	public String FELICIT_MOT_TROUVE = "BRAVO !";
	
	Ecouteur ecouteur;

	//*********************************************************************************************************************
	//***********************************************************CONSTRUCTEUR
	//*********************************************************************************************************************
	
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

	//*********************************************************************************************************************
	//***********************************************************METHODES
	//*********************************************************************************************************************
	
	
	//¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤    
	//**********************************************METHODES POUR REINITIALISER LE JEU
	//¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤  
	
	
	//¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤    réinitialisation des couleurs
	public void resetColors(String theme) {
		//initialisation des couleurs - a l'avenir il faudra aller chercher le theme dans un .xml
		CouleurThemes coul = new CouleurThemes(theme);
		
		COL_FOND = coul.getColComplementaire_5();
		COL_FOND_BOUTONS_CLIC = coul.getColComplementaire_3();
		COL_TEXT_BOUTONS_CLIC = coul.getColPrimaire_3();
		COL_FOND_BOUTONS_NON_CLIC = coul.getColPrimaire_5();
		COL_TEXT_BOUTONS_NON_CLIC = coul.getColComplementaire_4();
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
	
	
	//¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤    remise à zéro des paramètres d'initialisation
	private void resetGame() {
		debug = true;
		setScore(0);
		setMotsTrouves(0);
		setNbreLettres(0);
		setNbreEssais(0);
		setNbreLettresUtilisees(0);
		setNiveau(1);
		chargementMots();
		chargementMotsLang();
		listeLettres = new ArrayList<Character>();
		lettreTrouvee = ' ';
		ecouteur = new Ecouteur(this);
//		btnMap = new BoutonsMap(ecouteur);
		listeLettresTrouvees = new ArrayList<Character>();
	}
	

	//¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤    
	//**********************************************METHODES DE GESTION DES LETTRES ET DES MOTS
	//¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤  
	
	
	//¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤    recherche d'une lettre cliquée dans le mot
	public void searchLetter(String lettreClic) {

		//incrémenter le nombre de lettres utilisées ou cliquées
		setNbreLettresUtilisees(++nbreLettresUtilisees);
			
		strMotTemp = "";
		
		StringBuilder strBdMotTemp = new StringBuilder();
		
			for(int rank = 0; rank < strMot.length(); rank++){
				// il faut adapter les lettres du mot avec les lettres sans accents
				if(("" + strMot.charAt(rank)).equals("é") || ("" + strMot.charAt(rank)).equals("è") || ("" + strMot.charAt(rank)).equals("ê")){
					strBdMotTemp.append("e");
				} else if(("" + strMot.charAt(rank)).equals("à") || ("" + strMot.charAt(rank)).equals("â")){
					strBdMotTemp.append("a");
				} else if(("" + strMot.charAt(rank)).equals("ô")){
					strBdMotTemp.append("o");
				} else if(("" + strMot.charAt(rank)).equals("ç")){
					strBdMotTemp.append("c");
				} else if(("" + strMot.charAt(rank)).equals("î") || ("" + strMot.charAt(rank)).equals("ï")){
					strBdMotTemp.append("i");
				} else {
					strBdMotTemp.append(strMot.charAt(rank));
				}
			}
			
			strMotTemp = strBdMotTemp.toString();
			
			if(strMotTemp.toUpperCase().contains(lettreClic)){
				message = "lettre trouvée !";
				
				setNbreLettres(++nbreLettres);		//incrémentation du nombre de lettres trouvées
				setNbreEssais(++nbreEssais);		//incrémentation du nombre de fois que l'on a cliqué une lettre
				listeLettresTrouvees.add(lettreClic.charAt(0));		//ajout de la lettre dans la liste des lettres trouvées
				lettreTrouvee = lettreClic.charAt(0);		//sauvegarde de la lettre trouvée
				
				int gain = scoreLettreTrouvee(lettreClic.charAt(0));	//calcul du gain suite au clic sur une lettre présente dans le mot
				setScore(gain);
				
				
				//générer et ré afficher le mot caché
				listeLettres = setlisteLettres(listeLettres);
				
				//vérification pour savoir si le mot à été trouvé. 
				//si listeLettre ne contient pas  '_' alors le mot a été trouvé.
				if(!listeLettres.contains('_')){
					//si mot trouvé

					//incrémenter nbre de mots trouvés
					setMotsTrouves(++motsTrouves);
					
					//affichage de la dernière lettre pendant 900 millisecondes
					fenetreprincipale.panelAffichDerniereLettre(900);
				}
			} else {
				message = "lettre non trouvée !";
				setNbreEssais(++nbreEssais);	//incrémentation du nombre de fois que l'on a cliqué une lettre
			}		

			fenetreprincipale.repaint();
	}
	

	//¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤    
	//Choix d'un nouveau mot parmi la liste, on retire le mot choisi de la liste,
	//on génère le tableau de lettres du mot choisi, on génère le mot caché 
	private void choixNouveauMot() {
		
		randomNum = randomNum();
		
		strMot = motsLang.get(randomNum).getChaine();	//on récupère la chaine du mot choisi
		char[] charMot = strMot.toCharArray();
		//lorsque le nouveau mot a été choisi, il faut remplir un tableau de Character avec des underscores
		for(int rank = 0; rank < strMot.length(); rank++){
			boolean nonLettre = false;		//pour repérer les caractères qui ne sont pas des lettres
			
			if(("" + charMot[rank]).equals(' ') || ("" + charMot[rank]).equals('-')){
				nonLettre = true;
				listeLettres.add((charMot[rank]));
			}
			if (!nonLettre){
				listeLettres.add('_');
			}
		}
		
		//on enleve de la liste le mot qui a été tiré au hazard pour qu'il ne soit pas choisi au hasard plusieurs fois
		motsLang.remove(randomNum);
				
	}
	
	
	//¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤    génération d'un entier au hasard compris entre 0 et les nombre de mots 
	//contenus dans la liste des mots en .xml
	private int randomNum() {
		Random rand = new Random();
		int numRank = 0;
		
		try {
			numRank =  rand.nextInt((motsLang.size()));
		}
		catch (IllegalArgumentException argEx){System.out.println("Illegal Argument Exception captée");
		stopGame();
		}
		catch (ArrayIndexOutOfBoundsException bounds) {System.out.println("Array Index Out Of Bounds Exception captée");}
		finally {
			//reset game
		}
		
		return numRank;
	}	
	
	
	//¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤    création du mot qui sera affiché (on montre les lettres qui ont déjà été trouvées)
	//prend en paramètre la liste
	private ArrayList<Character> setlisteLettres(ArrayList<Character> listeLettres){	
		for (int rank = 0; rank < listeLettres.size(); rank++){
			if(listeLettres.get(rank).equals('_') && !listeLettres.get(rank).equals(' ')){
				if(lettreTrouvee.toString().toLowerCase().equals(("" + strMotTemp.charAt(rank)))){
					listeLettres.set(rank, strMot.charAt(rank));
				}
			}
		}
		
		return listeLettres;
	}

	
	//¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤    chargement des mots
	private void chargementMots() {
		mots = motdao.chargerMots();
	}
	
	
	//¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤	Chargement de la liste de mots appartenants au language choisi
	public void chargementMotsLang(){
		motsLang = new Vector<Mot>();		//réinitialisation de la liste a chaque chargement
		for(int rangMot = 0; rangMot < mots.size(); rangMot++){		//on regarde tous les mots un par un
			if(mots.get(rangMot).getLang().toUpperCase().equals(getLanguage())){		//si la langue du mot pointé est égale au language choisi 
				//on intègre ce lot dans la liste des mots relatifs au language choisi
				motsLang.add(mots.get(rangMot));
			}
		}
	}
	
	
	//¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤    
	//**********************************************METHODES D'AFFICHAGES
	//¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤  
	
	
	//¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤    méthode qui est appelée depuis le panelaffichage lorsque l'affichage de la dernière lettre est fini
	public void apresPanelAffichDernierelettre() {
		//remise à zéro du nombre de lettres cliquées
		
		//affichage BRAVO !!!
		fenetreprincipale.motTrouveBool(true);
		//rafraichissement du panel score
		fenetreprincipale.repaintPanelScore();
		listeLettres.clear(); 	//on vide la liste des lettres avant de remplir
		//on remplit la liste des lettres avec le mot de félicitation
		for(int i = 0; i < FELICIT_MOT_TROUVE.length(); i++){
			listeLettres.add(FELICIT_MOT_TROUVE.charAt(i));
		}
		//on demande l'affichage du mot de félicitation (avec clignotment codé dans panelAffichage) pendant xxx millisecondes
		fenetreprincipale.PanelAffichFelicit(3000);
	}
	
	
	//¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤    méthode appelée depuis Ecouteur pour réafficher le panelaffichage
	private void repaintPanelAffich() {
		fenetreprincipale.repaintPanelAffich();
	}
	
	//¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤    méthode appelée depuis Ecouteur pour réafficher le panelaffichage
	private void repaintPanelScore() {
		fenetreprincipale.repaintPanelScore();
	}
	
	//¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤    méthode appelée depuis la méthode hideText() de panelaffichage lorsque la félicitation a fini de clignoter
	public void apresMotTrouve() {
		//le gain du mot trouvé est ajouté au score
		int gain = scoreMotTrouve();
		setScore(gain);
		
		//On repasse le booléen motTrouvé à false pour que le panelAffichage affiche le mot normalement
		fenetreprincipale.motTrouveBool(false);
		//on remet à zéro la liste des lettres du mot à afficher
		listeLettres.clear();
		//on remet à zéro le nombre d'essais
		nbreEssais = 0;
		//on dégrise les touches
		btnGriseur("all", false);
		//on remet les compteur du nombre de lettres trouvées à zéro
		setNbreLettresUtilisees(0);
		//nouveau mot
		choixNouveauMot();
		repaintPanelScore();
		repaintPanelAffich();
	}

	
	//¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤    méthode en chantier quiservira à terminer le jeu ou à le redémarrer
	//il faut demander au joueur s'il veut recommencer.	
	public void stopGame() {
		//enregistrer score si high score
		//afficher mot de fin
		//demander à taper sur une touche pour recommencer
		
		//ou fermer le jeu

		System.exit(0);
	}
	
	
	//¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤    griseur de boutons
	public void btnGriseur(String btnValue, boolean boolGriser) {
		fenetreprincipale.griserBouton(btnValue, boolGriser);
	}
	
	
	//¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤    repaint général qui modifie les couleurs de fond
	public void rafraichiJeu() {
		fenetreprincipale.modifieBackgrounds();
	}
	
	
	//¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤    
	//**********************************************METHODES POUR LE SCORE
	//¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤    
	
	
	//¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤    modifieScore
	public void modifieScore(int ajout) {
		setScore(getScore() + ajout);
	}
	
	//¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤    calcule le score suite à un mot trouvé
	private int scoreMotTrouve(){
		int gain = 0;		
		//vérification des lettres en doubles dans le mot
		//si lettres doubles, le nombre d'essais pour trouver le mot sans se tromper diminue d'autant plus
		int nbreLettresDoubles = 0;
		for(int i = 0; i < strMot.length(); i++){
			for(int j = i + 1; j < strMot.length(); j++) {
				if(Character.toString(strMot.charAt(i)).equals(Character.toString(strMot.charAt(j)))){
					nbreLettresDoubles++;
					break;
				}
			}
		}
		
		//si on trouve toutes les lettres sans se tromper -> ultra bonus 100pts
		if (nbreEssais == strMot.length() - nbreLettresDoubles){
			gain = GAIN_MOT_TROUVE + 20;
		}
		
		int reste = nbreEssais - strMot.length();
		
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
	
	
	//¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤¤    calcule le score suite à une lettre trouvée
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
	

	//*********************************************************************************************************************
	//***********************************************************GETTERS & SETTERS
	//*********************************************************************************************************************
	
	
	public int getScore() {return score;}
	public void setScore(int gain) {this.score += gain;}

	public int getMotsTrouves() {return motsTrouves;}
	public void setMotsTrouves(int motsTrouves) {this.motsTrouves = motsTrouves;}

	public int getNbreLettres() {return nbreLettres;}
	public void setNbreLettres(int nbreLettres) {this.nbreLettres = nbreLettres;}

	public int getNiveau() {return niveau;}
	public void setNiveau(int niveau) {this.niveau = niveau;}

	public String getStrMot() {return strMot;}
	public void setStrMot(String strMot) {this.strMot = strMot;}

	public List<Character> getListeLettres() {return listeLettres;}

	public int getNbreEssais() {return nbreEssais;}
	public void setNbreEssais(int nbreEssais) {this.nbreEssais = nbreEssais;}

	public int getNbreLettresUtilisees() {return nbreLettresUtilisees;}
	public void setNbreLettresUtilisees(int nbreLettresUtilisees) {this.nbreLettresUtilisees = nbreLettresUtilisees;}

	public void repaintPanelKeyboard() {fenetreprincipale.repaintPanelKeyboard();}
	public void colorerTousBoutons() {fenetreprincipale.colorerTousBoutons();}

	public Vector<String> getStrMots() {return strMots;}
	public void setStrMots(Vector<String> strMots) {this.strMots = strMots;}

	public String getLanguage() {return language;}
	public void setLanguage(String language) {this.language = language.toUpperCase();}
}
