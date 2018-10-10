package com.javaUtil;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class ResourceBundleClass {

	public static void main(String[] args) {
		try {
			ResourceBundleClass.getResourceBundle();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public static void getResourceBundle() throws IOException{
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
		
		//Per Internazionalizzare un applicativo si Usa il ResoureceBundle 
		//che lavora con locale in questo modo:
		//Prende i file MyResource_IT , ENG, German, etc che devono esistere già 
		//e li caricica nella variabile resourceAAA, cosi sono disponipili tramite
		//resourceDef.getString(String Key).
		ResourceBundle resourceDef = ResourceBundle.getBundle("MyResource",Locale.getDefault());
		ResourceBundle resourceCHINA = ResourceBundle.getBundle("MyResource",Locale.CHINA);
		ResourceBundle resourceENG = ResourceBundle.getBundle("MyResource",Locale.ENGLISH);
		ResourceBundle resourceGERMAN = ResourceBundle.getBundle("MyResource",Locale.GERMAN);
		


	}

}
