package com.cablesfb.helper;

public class Rounder {
	public static double roundByFourZeroes(double d) {
		d = Math.round(d*10000);
		d /= 10000;
		
		return d;
	}
}
