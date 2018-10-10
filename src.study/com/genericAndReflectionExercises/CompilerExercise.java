package com.genericAndReflectionExercises;

import java.io.IOException;

public class CompilerExercise{ 

	public static void main(String []args) throws IOException, InterruptedException{
		String str = "TestMoneta_chapter11_JavaLang.java";
		Runtime runtime = Runtime.getRuntime();
		Process process = runtime.exec("javac " + str);
		final int exitValue = process.waitFor();
		System.out.println(exitValue == 0 ? str + " compilato!" :
		"Impossibile compilare " + str);
		
	}

} 