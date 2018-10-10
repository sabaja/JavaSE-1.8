package com.java_IO;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
/**
 * 
 * @author sabaja
 *http://stackoverflow.com/questions/1377279/find-a-line-in-a-file-and-remove-it
 */
public class NonScrivereUnaRigaParticolareInUnFile {

	public static void main(String[] args) throws IOException {
		File input = new File("/home/sabaja/Scrivania/Source_list_bak");
		File output = new File("/home/sabaja/Scrivania/Source_list_bak_temp");

		BufferedReader reader = new BufferedReader(new FileReader(input));
		BufferedWriter writer = new BufferedWriter(new FileWriter(output));

		String currentLine;
		String startC = "#";
		int countReadLine = 0;
		int countWroteLine = 0;

		while ((currentLine = reader.readLine()) != null) {
			++countReadLine;
			String trimmedLine = currentLine.trim();
			if (trimmedLine.startsWith(startC))
				continue;
			writer.write(currentLine + System.getProperty("line.separator"));
			System.out.println(currentLine);
			++countWroteLine;
		}
		reader.close();
		writer.close();
		System.out.println(
				"Read lines: " + countReadLine + "\n" + "Wrote lines: " + countWroteLine + "\n----- End -----");
	}

}
