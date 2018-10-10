package com.java_IO.NIO2;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.stream.Stream;

/**
 * http://stackoverflow.com/questions/1844688/read-all-files-in-a-folder
 * 
 * @author sabaja
 *
 */
public class ComeLeggereIlConetutoDiUnaCartella {

	public static void main(String[] args) {
//		final File folder = new File("/home/sabaja/Scrivania");
//		listFilesForFolder(folder);
		
		readPath();		
	}

	/**
	 * 
	 * @param folder
	 * 
	 * Pre java 8 in modo ricorsivo
	 */
	public static void listFilesForFolder(final File folder) {
		for (final File fileEntry : folder.listFiles()) {
			if (fileEntry.isDirectory()) {
				listFilesForFolder(fileEntry);
			} else {
				System.out.println(fileEntry.getParentFile()+ System.lineSeparator()+ fileEntry.getName());
			}
		}
	}

	/**
	 * The example uses try-with-resources pattern recommended in API guide. It
	 * ensures that no matter circumstances the stream will be closed
	 * 
	 * Legge tutta la cartella e segue le sotto cartelle esempio java 8
	 */
	public static void readRecursivePath() {
		try (Stream<Path> paths = Files.walk(Paths.get("/home/sabaja/Scrivania"))) {
			paths.forEach(filePath -> {
				if (Files.isRegularFile(filePath)) {
					System.out.println(filePath);
				}
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void readPath(){
		Path path = Paths.get("/home/sabaja/Scrivania/Prova");
		System.out.println(path.getFileName());
		
	}
	
}
