package com.io;

import java.io.*;

public class DataStream_PerLeggereEScrivere_indipendentePIattaforma {
	public static void main(String[] args) throws IOException {
		String[] first_names = {"Alba", "Mario", "Alfredo", "Michele"};
		String[] last_names = {"Chiara", "Rossi", "Alcala", "Mazza"};
		int[] ages = {39, 33, 44, 66};
		String file = "/home/sabaja/Scrivania/Dev-space/Input_Ouput/Anagrafica.bin";
		try (DataOutputStream dos = new DataOutputStream(
				new BufferedOutputStream(new FileOutputStream(file)))) {
			for (int i = 0; i < 4; i++) {
				// scrivo in un formato binario i valori seguenti
				dos.writeUTF(first_names[i]);
				dos.writeChar('\t');
				dos.writeUTF(last_names[i]);
				dos.writeChar('\t');
				dos.writeInt(ages[i]);
			}
		}
		System.out.println("Scrittura completata!");
		try (DataInputStream dis = new DataInputStream(
				new BufferedInputStream(new FileInputStream(file)))) {
			while (true) // leggo i valori binari
			{
				System.out.print(dis.readUTF());
				System.out.print(dis.readChar());
				System.out.print(dis.readUTF());
				System.out.print(dis.readChar());
				System.out.print(dis.readInt() + "\n");
			}
		} catch (EOFException exc) {
			System.out.println("Lettura completata!");
		}
	}
}
