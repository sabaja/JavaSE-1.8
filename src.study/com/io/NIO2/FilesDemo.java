package com.io.NIO2;

import java.io.IOException;
import java.nio.file.*;

import static java.lang.System.out;
import static java.nio.file.StandardCopyOption.*;

public class FilesDemo {
	public static void main(String[] args) throws IOException {
		Path path = Paths.get("/home/sabaja/Scrivania/Dev-space/Input_Ouput/InData.txt");
		Path other_path = Paths.get("/home/sabaja/Scrivania/Dev-space/Input_Ouput/Infile.txt");
		Path link_path = Paths.get("/home/sabaja/Scrivania/Dev-space/Input_Ouput/Infile_link"); // symbolic link
		Path copy_path = Paths.get("/home/sabaja/Scrivania/Dev-space/Input_Ouput/InData.txt"); // test
																		// esistenza
																		// file
		String ex_1 = Files.exists(path) ? "esiste" : "non esiste";
		String ex_2 = Files.exists(other_path) ? "esiste" : "non esiste";
		out.format("1-Il file %s %s. \n", path, ex_1);
		out.format("2-Il file %s %s. \n", other_path, ex_2);

		// test se due path localizzano lo stesso file
		boolean path_eq = Files.isSameFile(other_path, link_path);
		out.format("3-Il file %s è lo stesso del file %s [%b] %n", other_path, link_path, path_eq);
		// cancello un file che non esite!
		try{
			Files.delete(Paths.get("/home/sabaja/Scrivania/Dev-space/Input_Ouput/Dacanc"));
		} catch (NoSuchFileException fse) {
			out.format("Cancellazione fallita: il file %s non esiste. %n", fse.getFile());
		}
		try // copio il file InData.txt in C:\MY_JAVA_CLASSES
		{
			Files.copy(other_path, copy_path, COPY_ATTRIBUTES);
			out.format("%s copiato correttamente in %s %n", other_path, copy_path);
		} catch (FileAlreadyExistsException fae) {
			out.println("Copia fallita: il file è già esistente!");
		}
		try // sposto il file InData.txt in C:\MY_JAVA_SOURCE sovrascrivendolo!
		{
			Files.move(copy_path, other_path, ATOMIC_MOVE, REPLACE_EXISTING);
			out.format("%s spostato correttamente in %s %n", copy_path, other_path);
		} catch (AtomicMoveNotSupportedException am) {
			out.println("Il filesytem non supporta lo spostamento 'atomico'.");
		}
	}
}