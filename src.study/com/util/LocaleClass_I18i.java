package com.util;

import java.io.IOException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

class LocaleClass_I18i {
	public static void main(String []args){
		//NumberFormatClass n = new NumberFormatClass();
		try {
			LocalClassLanguage l = new LocalClassLanguage();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

class NumberFormatClass{
	public NumberFormatClass() {
		NumberFormat nf = NumberFormat.getInstance();//defualt locale
		
		try {
			System.out.println("Italy: " + nf.parse("23,5"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		nf = NumberFormat.getInstance(Locale.ENGLISH);
		
		try {
			System.out.println("English: " + nf.parse("23,6"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		System.out.println("German Doubleformat: " + NumberFormat.getInstance(Locale.GERMANY).format(new Double(123.90D)));
		System.out.println("Chinese Doubleformat: " + NumberFormat.getInstance(Locale.SIMPLIFIED_CHINESE).format(new Double(123.90D)));
		System.out.println("US Doubleformat: " + NumberFormat.getInstance(Locale.US).format(new Double(123.90D)));
		System.out.println("Italy Doubleformat: " + NumberFormat.getInstance(Locale.ITALY).format(new Double(123.90D)));
	}
}

class LocalClassLanguage{
	public LocalClassLanguage() throws IOException {
		PropertiesClass pc = new PropertiesClass();
		System.out.println("language = " + pc.getProperty("language"));
		Locale locale = null;
		String language = pc.getProperty("language");
		if(language != null && !language.equals("")){
			locale = new Locale(language);
		}
		else{
			locale = Locale.getDefault();
			pc.setProperty("language", locale.toString());
		}
		System.out.println("language = " + pc.getProperty("language"));
	}
}