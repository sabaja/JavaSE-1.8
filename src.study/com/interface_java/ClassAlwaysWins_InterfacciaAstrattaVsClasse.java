package com.interface_java;

/**
 * Il metodo nella classe vince e sovrascrive metodo
 * 
 * @author sabaja
 *
 */
public class ClassAlwaysWins_InterfacciaAstrattaVsClasse implements Concrete_6 {

	public static void main(String[] args) {
		ClassAlwaysWins_InterfacciaAstrattaVsClasse exe = new ClassAlwaysWins_InterfacciaAstrattaVsClasse();
		exe.print();
	}

	@Override
	public void print() {
		System.out.println("Class wins");
	}
}

interface Concrete_6 {

	public default void print() {
		System.out.println("Concrete_6");
	}

}
