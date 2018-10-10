package com.dateApiRegexUtilExercises;

public enum Languages {
	English("en"), Italian("it"), Spanish("es");
	private String name;
	
	private Languages(String name){
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

}