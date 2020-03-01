package com.interfaces;

/**
 * Caso che si eredita da 1 interfaccia astratta e una concreata
 * 
 * @author sabaja
 *
 */
public class ConflittoMetodoAstrattoInterfaccaConcreta implements Abstract_1, Concrete_3 {

	public static void main(String[] args) {
		ConflittoMetodoAstrattoInterfaccaConcreta exe = new ConflittoMetodoAstrattoInterfaccaConcreta();
		exe.print();
	}

	// Override obbligatorio
	@Override
	public void print() {
		// Opzione 1 Override metodo
		System.out.println("Override");
		// Opzione 2 richiamare il metodo della superclasse Concreta all'interno
		// dell'override
		Concrete_3.super.print();
	}

}

interface Abstract_1 {
    void print();

}

interface Concrete_3 {
    default void print() {
        System.out.println("Concrete_3");
    }

}