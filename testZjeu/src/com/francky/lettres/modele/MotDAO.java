package com.francky.lettres.modele;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Vector;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import com.francky.lettres.ctrl.Controleur;

public class MotDAO {

	//DECLARATIONS
	File motsXML;
	Controleur ctrl;
	
	//CONSTRUCTEUR
	public MotDAO(String filename, Controleur ctrl) {
		super();
		motsXML = new File(filename);
		this.ctrl = ctrl;
	}
	
	public Vector<Mot> chargerMots(){
		Vector<Mot> mots = new Vector<Mot>();
		

			XMLReader reader;
			try {
				reader = XMLReaderFactory.createXMLReader();
				MotsHandler mh = new MotsHandler();
				
				reader.setContentHandler(mh);
				
				reader.parse(new InputSource(new FileInputStream("mots_2.xml")));
				mots = mh.getMots();
			} catch (SAXException | IOException e) {e.printStackTrace();}
		
		return mots;
	}
}
