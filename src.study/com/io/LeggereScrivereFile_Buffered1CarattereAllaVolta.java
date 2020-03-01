package com.io;

import java.io.*;

//legge e scrive

public class LeggereScrivereFile_Buffered1CarattereAllaVolta {

	public static void main(String[] args) throws IOException { 
		try (BufferedReader br = new BufferedReader( 
				new FileReader("/home/sabaja/Scrivania/Dev-space/Input_Ouput/InData.txt")); 
				BufferedWriter bw = new BufferedWriter(
						new FileWriter("/home/sabaja/Scrivania/Dev-space/Input_Ouput/OutData.txt"))){ 
					int c; 
					while ((c = br.read()) != -1) 
						// legge un carattere alla volta 
						bw.write(c); // scrive un carattere
					}
		}
}

