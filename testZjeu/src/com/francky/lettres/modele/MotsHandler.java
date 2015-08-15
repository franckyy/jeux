package com.francky.lettres.modele;

import java.util.Vector;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;

public class MotsHandler implements ContentHandler {
	Vector<Mot> mots = new Vector<Mot>();
	Mot mot;
	String famille;
	int niveau;
	
	@Override
	public void characters(char[] contenu, int start, int length) throws SAXException {
		String chaineTemp = new String(contenu, start, length);
		if(!chaineTemp.contains("\n")){
			mot.setChaine(chaineTemp);
			System.out.println(mot.toString());
		}
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {
		
		//création d'un Mot à chaque fois que je rencontre la balise <mots> et ajout de ce mot dans la Vector<Mot> mots
		if(localName.equals("mots")){
			
			//initialisation des attributs de Mot
			for(int i = 0; i < atts.getLength(); i++){
				if (atts.getQName(i).equals("famille")){
					famille = atts.getValue(i);
				}
			}
		}
		
		//initialisation de la Collection de mots étrangers en initialisant leur attributs
		
		if(localName.equals("listemots")){
			for(int j = 0; j < atts.getLength(); j++){
				switch (atts.getQName(j).toLowerCase().trim()){
				case "niveau":
					niveau = Integer.parseInt(atts.getValue(j));
					break;
				}
			}
		}
		
		if(localName.equals("mot")){
			mot = new Mot();
			mot.setFamille(famille);
			mot.setNiveau(niveau);
			mots.add(mot);
			for(int j = 0; j < atts.getLength(); j++){
				switch (atts.getQName(j).toLowerCase().trim()){
				case "lang":
					mot.setLang(atts.getValue(j));
					break;
				case "genre":
					mot.setGenre(Boolean.parseBoolean(atts.getValue(j)));
					break;
				case "pluriel":
					mot.setPluriel(atts.getValue(j));
					break;
				}
			}
		}
	}
	
	//GETTERS & SETTERS
	public Vector<Mot> getMots() {return mots;}

	
	
	
	//Autres méthodes non utilisées
	@Override
	public void endDocument() throws SAXException {
//		System.out.println("fin document");
	}
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
//		System.out.println("balise fermante : " + localName );
	}
	@Override
	public void endPrefixMapping(String arg0) throws SAXException {}
	@Override
	public void ignorableWhitespace(char[] arg0, int arg1, int arg2) throws SAXException {}
	@Override
	public void processingInstruction(String arg0, String arg1) throws SAXException {}
	@Override
	public void setDocumentLocator(Locator arg0) {}
	@Override
	public void skippedEntity(String arg0) throws SAXException {}
	@Override
	public void startDocument() throws SAXException {
//		System.out.println("debut du document");
	}
	@Override
	public void startPrefixMapping(String arg0, String arg1) throws SAXException {}
	
}
