package com.miscellaneus;

import java.util.Scanner;

public class ScannerInputTest {

	public static void main(String[] args) {
		System.out.println("enter a number:");
		int num = 0;
		Scanner in = new Scanner(System.in);
		num = in.nextInt();
		while(num < 0 || num > 5){
			System.out.println("error: ");
			num = in.nextInt();
		}
		
		

	}

}
