package com.cablesfb.helper;

public class Discounter {
	
	public static double discount(String type, double price) {
		double priceWithDiscount = 0;
		if(type.equalsIgnoreCase("unipolar")) {
			priceWithDiscount = price * 0.5 * 0.95;
		}else if (type.equalsIgnoreCase("subterraneo")) {
			priceWithDiscount = price * 0.5 * 0.95;
		}else if (type.equalsIgnoreCase("desnudo")) {
			priceWithDiscount = price * 0.5 * 0.95;
		}else if (type.equalsIgnoreCase("antihurto")) {
			priceWithDiscount = price * 0.53;
		}else if (type.equalsIgnoreCase("paralelo") || type.equalsIgnoreCase("bipolar")) {
			priceWithDiscount = price * 0.5 * 0.95;
		}else if (type.equalsIgnoreCase("bomba sumergible")) {
			priceWithDiscount = price * 0.5 * 0.95;
		}else if (type.equalsIgnoreCase("preensamblado")) {
			priceWithDiscount = price * 0.53;
		}else if (type.equalsIgnoreCase("vaina chata")) {
			priceWithDiscount = price * 0.5 * 0.95;
		}else if (type.equalsIgnoreCase("aluminio")) {
			priceWithDiscount = price * 0.6;
		}else if (type.equalsIgnoreCase("multipolar")) {
			priceWithDiscount = price * 0.5 * 0.95;
		}else if (type.equalsIgnoreCase("tipo taller")) {
			priceWithDiscount = price * 0.5 * 0.95;
		}else if (type.equalsIgnoreCase("vaamport")) {
			priceWithDiscount = price;
		}
		priceWithDiscount = Rounder.roundByFourZeroes(priceWithDiscount);
		
		return priceWithDiscount;
	}

}
