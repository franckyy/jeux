package com.francky.lettres.modele;

import java.util.HashMap;
import java.util.Map;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;

public class MotsHandler implements ContentHandler {

	@Override
	public void characters(char[] contenu, int start, int length) throws SAXException {
		System.out.println("texte :" + new String(contenu, start, length));
	}

	@Override
	public void endDocument() throws SAXException {
		System.out.println("fin document");
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		System.out.println("balise fermante : " + localName );
	}

	@Override
	public void endPrefixMapping(String arg0) throws SAXException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void ignorableWhitespace(char[] arg0, int arg1, int arg2)
			throws SAXException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void processingInstruction(String arg0, String arg1)
			throws SAXException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setDocumentLocator(Locator arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void skippedEntity(String arg0) throws SAXException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void startDocument() throws SAXException {
		System.out.println("debut du document");
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {
		
		//cr�ation d'un Mot � chaque fois que je rencontre la balise <mots>
		if(localName.equals("mots")){
			Mot mot = new Mot();
			
			//initialisation des attributs de Mot
			for(int i = 0; i < atts.getLength(); i++){
				if (atts.getQName(i).equals("famille")){
					mot.setFamille(atts.getValue(i));
				}
			}
			
			//initialisation de la Collection de mots �trangers en initialisant leur attributs
			if(localName.equals("mot")){
				//j'instancie la HashMap qui contiendra tous les mots et attrributs pour une langue
				Map<String, String> collMotsEtrangers = new HashMap<String, String>();
				for(int j = 0; j < atts.getLength(); j++){
					collMotsEtrangers.put(atts.getQName(j), atts.getValue(j));
				}
				
				mot.setMotsEtrangers(collMotsEtrangers);
			}
		}
		
		System.out.println("start element local Name : " + localName);
		
		for(int i = 0; i < atts.getLength(); i++){
			
			System.out.println("attribut : " + atts.getQName(i) + " = " + atts.getValue(i));
		}
	}

	@Override
	public void startPrefixMapping(String arg0, String arg1)
			throws SAXException {
		// TODO Auto-generated method stub
		
	}

}
