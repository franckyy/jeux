package com.francky.lettres.modele;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class Mot {

	//***************************************DECLARATIONS
	private String chaine;		//la chaîne de caractères du mot
	private boolean genre;		//true = masculin ou false = féminin
	private int niveau;			//le niveau de difficulté du mot
	private String famille;		//continent, pays, ville, animal, vegetal, objet
	private Vector<HashMap> motLangue;	//contient toutes les collections de mots étrangers avec leurs attributs
	private Map<String, String> motsEtrangers;		//mots étrangers avec leur attributs pour une seule langue
	
	//CONSTRUCTEURS
	public Mot() {super();}
	public Mot(String chaine){
		setChaine(chaine);
	}
	
	public Mot(String chaine, int niveau) {
		setChaine(chaine);
		setNiveau(niveau);
	}
	
	public Mot(String chaine, int niveau, boolean genre) {
		setChaine(chaine);
		setNiveau(niveau);
		setGenre(genre);
	}
	
	public Mot(String chaine, int niveau, boolean genre,String famille) {
		setChaine(chaine);
		setNiveau(niveau);
		setGenre(genre);
		setFamille(famille);
	}

	//METHODES
	public Character getChar(int index) {
		String mot = getChaine();
		char caractere = mot.charAt(index);
		return caractere;
	}
	
	//GETTERS & SETTERS
	public boolean isGenre() {return genre;}
	public void setGenre(boolean genre) {this.genre = genre;}
	
	public String getChaine() {return chaine;}
	public void setChaine(String chaine) {this.chaine = chaine;}
	
	public int getNiveau() {return niveau;}
	public void setNiveau(int niveau) {this.niveau = niveau;}
	
	public String getFamille() {return famille;}
	public void setFamille(String famille) {this.famille = famille;}
	
	public Map<String, String> getMotsEtrangers() {return motsEtrangers;}
	public void setMotsEtrangers(Map<String, String> motsEtrangers) {this.motsEtrangers = motsEtrangers;}
	
	public Vector<HashMap> getMotLangue() {return motLangue;}
	public void setMotLangue(HashMap<String, String> motsEtrangers) {this.motLangue.add(motsEtrangers);}

}
