package com.expressionlambda;

public class Lambda_ThisKeyword_Using {

	private String string = "Variabile d'istanza della classe";
	
	public void printVariable(){
		String string = "variabile del metodo corrente";
		new Thread(() -> System.out.println(string)).start();//variabile del metodo corrente
		
		new Thread(() -> System.out.println(this.string)).start(); //Variabile d'istanza della classe
	}
	
	public static void main(String[] args) {
		Lambda_ThisKeyword_Using l = new Lambda_ThisKeyword_Using();
		l.printVariable();

	}
	
		
}
