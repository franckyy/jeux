package com.francky.lettres.modele;

import java.awt.Color;
import java.util.ArrayList;

public class CouleurThemes {

			private static Color ColPrimaire_1, ColPrimaire_2, ColPrimaire_3, ColPrimaire_4, ColPrimaire_5;
			private static Color ColComplementaire_1, ColComplementaire_2, ColComplementaire_3, ColComplementaire_4, ColComplementaire_5;
			
	public CouleurThemes(String theme) {
		
		if (theme.equals("theme1")){
			//Theme 1
			ColPrimaire_1 = new Color(0xD7ECD7);
			ColPrimaire_2 = new Color(0xAED9AE);
			ColPrimaire_3 = new Color(0x80C080);
			ColPrimaire_4 = new Color(0x53A153);
			ColPrimaire_5 = new Color(0x2F812F);

			ColComplementaire_1 = new Color(0xFFE8E8);
			ColComplementaire_2 = new Color(0xFFCCCC);
			ColComplementaire_3 = new Color(0xF0A0A0);
			ColComplementaire_4 = new Color(0xC96767);
			ColComplementaire_5 = new Color(0xA23B3B);
		} else if (theme.equals("theme2")) {
			
		} else {
			//Theme 1
			ColPrimaire_1 = new Color(0xD7ECD7);
			ColPrimaire_2 = new Color(0xAED9AE);
			ColPrimaire_3 = new Color(0x80C080);
			ColPrimaire_4 = new Color(0x53A153);
			ColPrimaire_5 = new Color(0x2F812F);

			ColComplementaire_1 = new Color(0xFFE8E8);
			ColComplementaire_2 = new Color(0xFFCCCC);
			ColComplementaire_3 = new Color(0xF0A0A0);
			ColComplementaire_4 = new Color(0xC96767);
			ColComplementaire_5 = new Color(0xA23B3B);
		}
	}

	public static void setColPrimaire_1(Color colPrimaire_1) {ColPrimaire_1 = colPrimaire_1;}
	public static void setColPrimaire_2(Color colPrimaire_2) {ColPrimaire_2 = colPrimaire_2;}
	public static void setColPrimaire_3(Color colPrimaire_3) {ColPrimaire_3 = colPrimaire_3;}
	public static void setColPrimaire_4(Color colPrimaire_4) {ColPrimaire_4 = colPrimaire_4;}
	public static void setColPrimaire_5(Color colPrimaire_5) {ColPrimaire_5 = colPrimaire_5;}
	public  Color getColPrimaire_1() {return ColPrimaire_1;}
	public  Color getColPrimaire_2() {return ColPrimaire_2;}
	public  Color getColPrimaire_3() {return ColPrimaire_3;}
	public  Color getColPrimaire_4() {return ColPrimaire_4;}
	public  Color getColPrimaire_5() {return ColPrimaire_5;}
	
	public static void setColComplementaire_1(Color colComplementaire_1) {ColComplementaire_1 = colComplementaire_1;}
	public static void setColComplementaire_2(Color colComplementaire_2) {ColComplementaire_2 = colComplementaire_2;}
	public static void setColComplementaire_3(Color colComplementaire_3) {ColComplementaire_3 = colComplementaire_3;}
	public static void setColComplementaire_4(Color colComplementaire_4) {ColComplementaire_4 = colComplementaire_4;}
	public static void setColComplementaire_5(Color colComplementaire_5) {ColComplementaire_5 = colComplementaire_5;}
	public  Color getColComplementaire_1() {return ColComplementaire_1;}
	public  Color getColComplementaire_2() {return ColComplementaire_2;}
	public  Color getColComplementaire_3() {return ColComplementaire_3;}
	public  Color getColComplementaire_4() {return ColComplementaire_4;}
	public  Color getColComplementaire_5() {return ColComplementaire_5;}
}