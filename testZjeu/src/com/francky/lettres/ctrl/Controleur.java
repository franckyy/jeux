package com.francky.lettres.ctrl;

import java.awt.Color;
import java.util.Random;
import java.util.Vector;

import com.francky.lettres.modele.Mot;
import com.francky.lettres.modele.MotDAO;
import com.francky.lettres.vues.FenetrePrincipale;

public class Controleur {

	//DECLARATIONS
	FenetrePrincipale fenetreprincipale;
	MotDAO motdao = new MotDAO("mots.xml");
	Vector<Mot> mots = null;
	
	/*
	 * Gestion du score et des statistiques
	 */
	private int score;
	private int motsTrouves;
	private int nbreLettres;
	private int niveau;
	
	/*
	 * Gestion du mot à rechercher
	 */
	private String mot;
	
	/*
	 * Définition de la palette des couleurs pour le jeu (1 -> clair, 5 -> foncé)	 
	 */
	private static final Color COL_PRIMAIRE_1 = new Color(0xD7ECD7);
	private static final Color COL_PRIMAIRE_2 = new Color(0xAED9AE);
	private static final Color COL_PRIMAIRE_3 = new Color(0x80C080);
	private static final Color COL_PRIMAIRE_4 = new Color(0x53A153);
	private static final Color COL_PRIMAIRE_5 = new Color(0x2F812F);

	private static final Color COL_COMPLEMENTAIRE_1 = new Color(0xFFE8E8);
	private static final Color COL_COMPLEMENTAIRE_2 = new Color(0xFFCCCC);
	private static final Color COL_COMPLEMENTAIRE_3 = new Color(0xF0A0A0);
	private static final Color COL_COMPLEMENTAIRE_4 = new Color(0xC96767);
	private static final Color COL_COMPLEMENTAIRE_5 = new Color(0xA23B3B);
	
	public final Color COL_FOND = COL_COMPLEMENTAIRE_5;
	public final Color COL_TEXTE_1 = COL_PRIMAIRE_3;
	public final Color COL_TEXTE_2 = COL_PRIMAIRE_2;
	public final Color COL_MOT = COL_PRIMAIRE_3;
	public final Color COL_GRAPH = COL_PRIMAIRE_1;
	
	//CONSTRUCTEUR
	public Controleur() {
		//Initialisations
		setScore(0);
		setMotsTrouves(0);
		setNbreLettres(0);
		setNiveau(1);
		
		mots = motdao.chargerMots();
		
		//affichage dans console pour debug
		for(Mot m : mots){
			System.out.println("mot : " + m.getChaine() + ", genre : " + m.isGenre() + ", composed : " + m.isComposed());
		}
		
		Random rand = new Random();
		int randomNum = rand.nextInt((mots.size()));
		
		System.out.println("randomNum = " + randomNum);
		
		System.out.println("le mot est : " + mots.get(randomNum).getChaine());
		
		mot = mots.get(randomNum).getChaine();

		//création de la fenêtre
		fenetreprincipale = new FenetrePrincipale(this);
		
		
		fenetreprincipale.setVisible(true);
	}
	
	//METHODES
	
	//GETTERS & SETTERS
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
}
