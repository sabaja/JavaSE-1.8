package com.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;


public class MultiPartTest {

	public static void main(String[] args) {
		// /home/sabaja/Scrivania/spring-hibernate-workspace/Spring-In-Action_WEBApp/Downloads/
		File file =  new File("/home/sabaja/Immagini/DSC0003.JPG");
		
		try(FileInputStream fileInputStream = new FileInputStream(file)) {
			String fileName = file.getName();
			byte[] bytes = new byte [(int)file.length()];
			Path path = Paths.get("/home/sabaja/Scrivania/Immagini" + File.separator + fileName);
			// CREATE
			Files.write(path, bytes, StandardOpenOption.CREATE);
			System.out.println("DONE");
			
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

}
