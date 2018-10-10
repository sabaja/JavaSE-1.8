package com.collections.streamAPI;

import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.file.StandardCopyOption.*;

import java.io.IOException;

public class CopyDiUnFileTramiteFiles {

	public static void main(String[] args) {
		Path source = Paths.get("/home/sabaja/Scrivania/Dev-space/Input_Ouput/Source.txt");
		Path target = Paths.get("/home/sabaja/Scrivania/Dev-space/Input_Ouput/Target.txt");
		try {
			//Copia il file sostituendo il file, 
			//copiando anche gli attributi (scrittura, lettura..etc) 
			//e se fosse un collegamento a un file
			//copia solo il link
			Files.copy(source, target, REPLACE_EXISTING, COPY_ATTRIBUTES , LinkOption.NOFOLLOW_LINKS);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
