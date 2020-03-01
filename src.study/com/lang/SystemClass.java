package com.lang;

import java.io.*;
import java.util.Arrays;
import java.util.List;

public class SystemClass {

	public static void main(String[] args) {		
		PrintStream stdout = System.out;
		PrintStream stderr = System.err;
		PrintStream psOut, psErr;
		List<PrintStream> listSream; 
		try {
			psOut = new PrintStream(new File("fileOut.txt"));
			psErr = new PrintStream(new File("fileErr.txt"));
			listSream = Arrays.asList(psOut,psErr);
			System.setOut(listSream.get(0));
			System.out.println("Inside file");
			System.setErr(listSream.get(1));
			long i = 1000000;
			while(--i > 0){
				System.err.println("Inside file " + i);
				
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		//System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
		System.setOut(stdout);
		System.setErr(new PrintStream(new FileOutputStream(FileDescriptor.err)));
		System.out.println("her");
		System.err.println("err");
	}
}
