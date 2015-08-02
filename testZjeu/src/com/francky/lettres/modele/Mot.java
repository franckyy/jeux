package com.francky.lettres.modele;

public class Mot {

	//***************************************DECLARATIONS
	private boolean genre;		//true = masculin ou false = f�minin
	private String chaine;		//le mot
	private boolean composed;	//Est-ce un mot compos� (exemple : garde-boue)
	
	//CONSTRUCTEURS
	public Mot() {this("mot", true, false);}
	public Mot(String chaine, boolean genre, boolean composed) {
		setChaine(chaine);
		setGenre(genre);
		setComposed(composed);
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
	
	public boolean isComposed() {return composed;}
	public void setComposed(boolean composed) {this.composed = composed;}
}
