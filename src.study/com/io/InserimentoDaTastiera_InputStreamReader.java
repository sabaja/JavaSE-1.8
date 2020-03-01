package com.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InserimentoDaTastiera_InputStreamReader {
	public static void main(String []args) throws IOException{
		System.out.println("Please enter a string\n" +
						   "Press Q/q to quit the program\n");
		String str = "";
		try (
				InputStreamReader isr = new InputStreamReader(System.in);
				BufferedReader br = new BufferedReader(isr)
		) {
			str = br.readLine();
			while (str != null) { //diverso da EOF
				if ("Q".equalsIgnoreCase(str)) {
					System.out.println("Exit sucessfully");
					break;
				}
				System.out.printf("you wrote:\n%s\n", str);
				str = br.readLine();
			}
				
		}
		
		return;
	}
}
