package com.interface_java;



/**
 * 	Caso che si eredita da 2 interfacce concrete
 * @author sabaja
 *
 */
public class ConflittoDueInterfacceConcrete implements Concrete_1, Concrete_2 {

	public static void main(String[] args) {
		ConflittoDueInterfacceConcrete exe = new ConflittoDueInterfacceConcrete();
		exe.print();
	}


	// Override obbligatorio
	@Override
	public void print() {
		// Opzione 1 Override metodo
		System.out.println("Override");
		// Opzione 2 richiamare il metodo della superclasse all'interno
		// dell'override
		Concrete_1.super.print();
		Concrete_2.super.print();
	}
}

interface Concrete_1 {
	public default void print() {
		System.out.println("concrete_1");
	}
}

interface Concrete_2 {
	public default void print() {
		System.out.println("concrete_2");
	}
}
