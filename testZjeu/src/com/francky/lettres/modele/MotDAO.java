package com.francky.lettres.modele;

import java.io.File;
import java.io.IOException;
import java.util.Vector;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class MotDAO {

	//DECLARATIONS
	File motsXML;
	public MotDAO(String filename) {
		super();
		motsXML = new File(filename);
	}
	
	public Vector<Mot> chargerMots(){
		Vector<Mot> mots = new Vector<Mot>();
		
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(motsXML);
			NodeList nl = doc.getElementsByTagName("mot");
			
			for(int i = 0; i < nl.getLength(); i++){
				Element el = (Element)nl.item(i);
				mots.add(new Mot(el.getTextContent(), Boolean.parseBoolean(el.getAttribute("genre")), Boolean.parseBoolean(el.getAttribute("composed"))));
			}
			
		} catch (SAXException | IOException | ParserConfigurationException e) {e.printStackTrace();}
		
		return mots;
	}
}
