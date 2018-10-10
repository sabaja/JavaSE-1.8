package com.javaUtil;

import java.text.NumberFormat;
import java.util.Locale;

public class Valuta {

	public static void main(String[] args) {
		printValuta();

	}
	
	static public void printValuta(){
		
		double value = 9898768178.99;
		
		Locale localeItaly = Locale.ITALY;
		Locale localeJapan = Locale.JAPAN;
		Locale localeUsa = Locale.US;
		Locale localeUk = Locale.UK;
		
		NumberFormat it = NumberFormat.getCurrencyInstance(localeItaly);
		NumberFormat jp = NumberFormat.getCurrencyInstance(localeJapan);
		NumberFormat us = NumberFormat.getCurrencyInstance(localeUsa);
		NumberFormat uk = NumberFormat.getCurrencyInstance(localeUk);
		
		System.out.println(it.format(value));
		System.out.println(jp.format(value));
		System.out.println(us.format(value));
		System.out.println(uk.format(value));
	}

}
