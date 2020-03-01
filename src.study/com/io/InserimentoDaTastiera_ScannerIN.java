package com.io;

import java.util.Scanner;

/**
 * 
 * @author sabaja
 * La classe Scanner è più lenta rispetto all'inserimento da tastiera
 * tramite new BufferedReader(new InpustreamReader(System.in)))
 * ma permette di controllare + accuratamente l'input
 */

public class InserimentoDaTastiera_ScannerIN {
	public static void main(String[] args) {
		ScannerIN.scan();
	}
}

class ScannerIN {

	public static void scan() {
		System.out.println("enter a number between 0 and 5:");
		int num = 0;
		Scanner in = new Scanner(System.in);
		num = in.nextInt();
		while(num < 0 || num > 5){
			System.out.println("error!\nenter a number between 0 and 5: ");
			num = in.nextInt();
		}
		System.out.println("Bye");
		in.close();//essenziale chiudere lo stream
		

	}

}