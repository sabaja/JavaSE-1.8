package com.pattern;

import java.util.Objects;

class Singleton {
	private static Singleton instance = null; //variabile statica dello stesso tipo della classe
	
	private Singleton(){
		System.out.println("NOW"); //costruttore privato accedibile solo dalla classe
	}

	public static Singleton getInstance(){ //metodo pubblico statico 
		if(instance == null) //if(Objects.isNull(instance) ) //controllo di istanza unica
			instance = new Singleton();
		return instance;
	}

}

class TestSingleton{
	public static void main(String[] args) {
		Singleton.getInstance();
	}
}
	// TODO Auto-generated method stub