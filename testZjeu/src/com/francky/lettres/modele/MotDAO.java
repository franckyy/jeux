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
				
				reader.setContentHandler(new MotsHandler());
				
				reader.parse(new InputSource(new FileInputStream("mots_2.xml")));
				
			} catch (SAXException | IOException e) {e.printStackTrace();}
			
//					
//			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
//			DocumentBuilder db = dbf.newDocumentBuilder();
//			Document doc = db.parse(motsXML);
//			NodeList nl = doc.getElementsByTagName("mot");
//			
//			for(int i = 0; i < nl.getLength(); i++){
//				Element el = (Element)nl.item(i);
//				mots.add(new Mot(el.getTextContent(), Integer.parseInt(el.getAttribute("niveau")), Boolean.parseBoolean(el.getAttribute("genre"))));
//			}
//			
//		catch (SAXException | IOException | ParserConfigurationException e) {
//			ctrl.stopGame();
//			e.printStackTrace();
//		}
		
		return mots;
	}
}
