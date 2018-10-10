package com.dateApiRegexUtilExercises;

import java.util.ResourceBundle;

import com.dateApiRegexUtilExercises.Dictionary;
import com.dateApiRegexUtilExercises.Languages;

import java.util.Locale;

public class PropertiesUtil {
	
	/**
	 * Properties file name.
	 * the fully qualified name for the resource
	 * ./languages/dictionary
	 */
	private static final String FILENAME = "c.com.languages.dictionary";

	/**
	 * Resource bundle.
	 */
	private static ResourceBundle bundle;

	/**
	 * default prende il vocabolario Italiano
	 * 
	 */
	static{
		PropertiesUtil.bundle = ResourceBundle.getBundle(FILENAME, Locale.getDefault());
	}

	public static String getFilename() {
		return FILENAME;
	}

	public static void setBundle(ResourceBundle bundle) {
		PropertiesUtil.bundle = bundle;
	}
	
	/**
	 * per impostare un altra lingua al resource bundle
	 * @param languages
	 */
	public static void setLenguageBundle(Languages languages) {
		PropertiesUtil.bundle = ResourceBundle.getBundle(FILENAME, new Locale(languages.getName()));
	}
	
	public static ResourceBundle getBundle() {
		return bundle;
	}
}