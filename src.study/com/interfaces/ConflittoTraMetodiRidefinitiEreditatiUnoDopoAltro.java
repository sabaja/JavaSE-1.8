package com.interfaces;

//Nessun conflitto e 
//in questo caso la classe eredita il metodo più specializzato
public class ConflittoTraMetodiRidefinitiEreditatiUnoDopoAltro implements Concrete_4, Concrete_5 {

	public static void main(String[] args) {
		ConflittoTraMetodiRidefinitiEreditatiUnoDopoAltro exe = new ConflittoTraMetodiRidefinitiEreditatiUnoDopoAltro();
		exe.print();
	}

}

interface Concrete_4 {
    default void print() {
        System.out.println("Concrete 4");
    }
}

interface Concrete_5 extends Concrete_4 {
    @Override
    default void print() {
        System.out.println("Concrete 5");
    }
}