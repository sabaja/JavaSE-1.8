package com.dateApiRegexUtilExercises;

public enum Dictionary {
	Book("book"), Table("table"), Flower("flower");
	private String name;
	
	private Dictionary(String name){
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
}