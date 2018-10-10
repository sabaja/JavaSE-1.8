package com.dateApiRegexUtilExercises;


import java.util.ArrayList;

import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.dateApiRegexUtilExercises.Dictionary;
import com.dateApiRegexUtilExercises.Languages;


public class StringUtils {
	
	public static String translate(Languages languages, Dictionary dictionary) {
		// Passa in input la lingua e il filename della property relativa alla
		// lingua e prende il bundle tramite ResourceBundle.getBundle(..,..)
		// che a sua volta è l'input del medoto setBundle della classe
		// PropertiesUtil che setta a livello statico la variabile bundle
		PropertiesUtil.setBundle(ResourceBundle.getBundle(PropertiesUtil.getFilename(), new Locale(languages.getName())));
		ResourceBundle bundle = PropertiesUtil.getBundle();
		String word = null;
		if (bundle != null) {
			word = bundle.getString(dictionary.getName());
		}
		return word;
	}

	/**
	 * Cerca una combinazione di regular expression passata in input nella
	 * stringa str passata anche lei in input e restituisce una lista caricata
	 * con le parole trovate.
	 * 
	 * @param regex
	 * @param str
	 * @return
	 */
	public static List<String> findByRegex(String regex, String str) {
		List<String> list = new ArrayList<>();
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(str);
		
		while (matcher.find()) {
				list.add(matcher.group());
		}
		return list;
	}	
}