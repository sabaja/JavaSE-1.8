package com.java_IO.NIO2;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class CreareUnFileInAppendMode {

	public static void main(String[] args) {
		// instazio il file
		File file = new File("/home/sabaja/Scrivania/Dev-space/Input_Ouput/FileAppend.txt");

		// prendo la stringa da scrivere
		String str = "Creato da Jacopo";

		try (FileWriter fw = new FileWriter(file, true);
				BufferedWriter bw = new BufferedWriter(fw);
				PrintWriter out = new PrintWriter(bw)) {
			// creo un outputstream bufferizzato con il nuovo file in CREATE
			// (se esiste già lo apre) e APPEND
			out.println(str);
			System.out.println("done");

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void alternativeMethod() {
		try {
			Files.write(Paths.get("/home/sabaja/Scrivania/Dev-space/Input_Ouput/FileAppend.txt"), "the text".getBytes(), StandardOpenOption.APPEND);
		} catch (IOException e) {
			// exception handling left as an exercise for the reader
		}
	}

	public static void olderJava() {
		try {
			PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("/home/sabaja/Scrivania/Dev-space/Input_Ouput/FileAppend.txt", true)));
			out.println("the text");
			out.close();
		} catch (IOException e) {
			// exception handling left as an exercise for the reader
		}
	}

}
