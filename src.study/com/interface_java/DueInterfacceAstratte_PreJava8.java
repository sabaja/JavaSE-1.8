package com.interface_java;

public class DueInterfacceAstratte_PreJava8 implements Abstract2, Abstract3 {

	public static void main(String[] args) {
		DueInterfacceAstratte_PreJava8 exe = new DueInterfacceAstratte_PreJava8();
		exe.print();
	}

	// Override obbligatorio con definizione dell'unico metodo in comune
	@Override
	public void print() {
		System.out.println("Pre Java 8");
	}

}

interface Abstract2 {
	public abstract void print();
}

interface Abstract3 {
	public abstract void print();
}