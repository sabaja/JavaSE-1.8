package com.java_IO.NIO2;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;


/**
 * 
 * @author sabaja
 *	Per attraversare un albero di file e directory dobbiamo 
 *	per prima cosa scrivere una classe che implementa l’interfaccia 
 *	FileVisitor , dando una definizione di tutti i metodi citati, 
 *	oppure definire una classe che estende la classe java.nio.file.SimpleFileVisitor, 
 *	che ha già implementato l’interfaccia FileVisitor fornendo 
 *	un comportamento di default per tutti i suoi metodi, sovrascrivendo, 
 *	di questi ultimi, solo quelli per cui vogliamo un comportamento 
 *	personalizzato. Infine dobbiamo utilizzare il metodo walkFileTree 
 *	della classe Files (package java.nio.file ) che si occuperà di 
 *	attraversare il percorso indicato utilizzando per ogni 
 *	file o directory incontrato l’oggetto “visitatore” specificato.
 *
 */
public class FileVisitorExample {

	public static void main(String[] args) {
		Path path = Paths.get("Input_Ouput/InData.txt");
		try {
			Files.walkFileTree(path, new FileVisitor<Path>() {
				public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
					System.out.println("Sto per visitare: " + dir.getFileName());
					return FileVisitResult.CONTINUE;
				}

				public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
					System.out.println(
							"Sto visitando: " + file.getFileName() + " dimensioni: (" + attrs.size() + ") byte");
					return FileVisitResult.CONTINUE;
				}

				public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
					System.out.println("Ho terminato la visita di: " + dir.getFileName());
					return FileVisitResult.CONTINUE;
				}

				public FileVisitResult visitFileFailed(Path file, IOException exc) {
					System.err.println("ERRORE nella visita: " + exc);
					return FileVisitResult.CONTINUE;
				}
			});
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

}
