package com.francky.lettres.modele;

public class Mot {

	//***************************************DECLARATIONS
	private boolean genre;		//true = masculin ou false = féminin
	private String chaine;		//le mot
	private boolean composed;	//Est-ce un mot composé (exemple : garde-boue)
	
	//CONSTRUCTEURS
	public Mot() {this("mot", true, false);}
	public Mot(String chaine, boolean genre, boolean composed) {
		setChaine(chaine);
		setGenre(genre);
	}

	//GETTERS & SETTERS
	public boolean getGenre() {return genre;}
	public void setGenre(boolean genre) {this.genre = genre;}
	
	public String getChaine() {return chaine;}
	public void setChaine(String chaine) {this.chaine = chaine;}
	
	public boolean isComposed() {return composed;}
	public void setComposed(boolean composed) {this.composed = composed;}
}
