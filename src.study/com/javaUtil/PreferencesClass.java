package com.javaUtil;

import java.util.prefs.Preferences;

class PreferencesClass {

	public static void main(String[] args) {
		PreferenceClassTest p = new PreferenceClassTest();
		p.printPreferences();
		p.changeNode();
		p.printPreferences();
	}

}

class PreferenceClassTest{
	private Preferences preference;
	private String key1 = "value.string";
	private String key2 = "value.int";
	private String key3 = "value.boolean";
	private String key4 = "value.double";	
	
	public PreferenceClassTest(){
		this.init();
	}
	
	public void init(){
		preference = Preferences.userRoot();	
		this.preference.put(key1, "String");
		this.preference.putInt(key2, 100);
		this.preference.putBoolean(key3, false);
		this.preference.putDouble(key4, 23);
		
	}
	public void changeNode(){//cambia il nodo partendo dalla root sempre
		preference = preference.userRoot().node(this.getClass().getName());
	}
	
	public void printPreferences(){
		System.out.println("node: " + (preference.name() == "" ? "root" : preference.name()));
		System.out.println("key1: " + preference.get(key1, "Default"));
		System.out.println("key2: " + preference.getInt(key2, 0));
		System.out.println("key3: " + preference.getBoolean(key3, true));
		System.out.println("key4: " + preference.getDouble(key4,0D));
		System.out.println();
	}
	
	
}