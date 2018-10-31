package com.miscellaneus;

public class EreditarietaMultipla_Interface_5_DiamondProblem {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

interface Soloist{
	default void solo(){
		System.out.println("A B C D E F G");
	}
}


interface RockSoloist{
	default void solo(){
		System.out.println("A B C D E F G");
	}
}

interface BluesSoloist{
	default void solo(){
		System.out.println("A B C D E F G");
	}
}
