package com.francky.lettres.vues.panneaux;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

import com.francky.lettres.ctrl.Controleur;
import com.francky.lettres.vues.FenetrePrincipale;

public class PanelScore extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5355849929954014794L;


	//************************************************************DECLARATIONS
	/*
	 * Définition de la hauteur et de la largeur
	 */
	private static final int SCORE_HEIGTH = PanelKeyboard.KEYBOARD_HEIGTH;
	public static final int SCORE_WIDTH = 200;
		
	/**
	 * The number of rows and columns in the preview window. Set to
	 * 5 because we can show any piece with some sort of padding.
	 */
	private static final int TILE_COUNT = 5;
	
	/**
	 * The center x of the next piece preview box.
	 */
	private static final int SQUARE_CENTER_X = 130;
	
	/**
	 * The center y of the next piece preview box.
	 */
	private static final int SQUARE_CENTER_Y = 65;
	
	
	/**
	 * The number of pixels used on a small insets (generally used for categories).
	 */
	private static final int SMALL_INSET = 20;
	
	/**
	 * The number of pixels used on a large insets.
	 */
	private static final int LARGE_INSET = 40;
	
	/**
	 * The y coordinate of the stats category.
	 */
	private static final int STATS_INSET = 20;
	
	/**
	 * The y coordinate of the controls category.
	 */
	private static final int SCORE_INSET = 220;
	
	/**
	 * The number of pixels to offset between each string.
	 */
	private static final int TEXT_STRIDE = 25;
	
	/**
	 * The small font.
	 */
	private static final Font SMALL_FONT = new Font("Arial", Font.BOLD, 12);
	
	/**
	 * The large font.
	 */
	private static final Font LARGE_FONT = new Font("Tahoma", Font.BOLD, 14);
	/**
	 * The color to draw the text and preview box in.
	 */

	//Contrôleur
	private Controleur ctrl;
	
	//************************************************************CONSTRUCTEUR
	public PanelScore(Controleur ctrl) {
		this.ctrl = ctrl;
		
		setPreferredSize(new Dimension(SCORE_WIDTH, SCORE_HEIGTH));
		setBackground(ctrl.COL_FOND);
		
	}
	

	//************************************************************METHODES
	//modifier le background du panel
	public void modifieBackgroundColor() {
		setBackground(ctrl.COL_FOND);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		/*
		 * This variable stores the current y coordinate of the string.
		 * This way we can re-order, add, or remove new strings if necessary
		 * without needing to change the other strings.
		 */
		int offset;
		
		/*
		 * Draw the "Statistiques" category.
		 */

		g.setColor(ctrl.COL_TEXTE_1);
		g.setFont(LARGE_FONT);
		g.drawString("Statistiques", SMALL_INSET, offset = STATS_INSET);
		g.drawString("Niveau", LARGE_INSET, offset += TEXT_STRIDE);
		g.setColor(ctrl.COL_TEXTE_2);
		g.setFont(SMALL_FONT);
		g.drawString("" + ctrl.getNiveau(), LARGE_INSET, offset += TEXT_STRIDE);
		g.setColor(ctrl.COL_TEXTE_1);
		g.setFont(LARGE_FONT);
		g.drawString("Mots trouvés", LARGE_INSET, offset += TEXT_STRIDE);
		g.setColor(ctrl.COL_TEXTE_2);
		g.setFont(SMALL_FONT);
		g.drawString("" + ctrl.getMotsTrouves(), LARGE_INSET, offset += TEXT_STRIDE);
		g.setColor(ctrl.COL_TEXTE_1);
		g.setFont(LARGE_FONT);
		g.drawString("Nombre de lettres", LARGE_INSET, offset += TEXT_STRIDE);
		g.setColor(ctrl.COL_TEXTE_2);
		g.setFont(SMALL_FONT);
		g.drawString("" + ctrl.getNbreLettres(), LARGE_INSET, offset += TEXT_STRIDE);
		
		/*
		 * Draw the "Score" category.
		 */
		g.setColor(ctrl.COL_TEXTE_1);
		g.setFont(LARGE_FONT);
		g.drawString("Score", SMALL_INSET, offset = SCORE_INSET);

		g.setColor(ctrl.COL_TEXTE_2);
		g.setFont(SMALL_FONT);
		g.drawString("" + ctrl.getScore(), LARGE_INSET, offset += TEXT_STRIDE);
	}
}
