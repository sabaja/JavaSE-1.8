package com.io;

import java.io.*;

public class RawByteCopy_with_FileInputStreamFileOutputStream {

	public static void main(String[] args) {
		String destination = "/home/sabaja/Scrivania/Dev-space/Input_Ouput/Destination.txt";
		String source = "/home/sabaja/Scrivania/Dev-space/Input_Ouput/Source.txt";
		UitilCopyStream.copyString(source, destination);
		System.out.println(destination);
	}

}

class UitilCopyStream {
	//source e destination son i path dei file da copiare e incollare
	static void copyString(String source, String destination) {
		try (InputStream inputStream = new FileInputStream(source);
			 OutputStream outputStream = new FileOutputStream(destination)) {
			byte[] bufferedBytes = new byte[16];//byte che sono bufferizzati cioè il contenuto di source 
			int bytesRead = 0; //numero byte letti
			while ((bytesRead = inputStream.read(bufferedBytes)) >= 0) {//fin quando non raggiunge la fine   
				outputStream.write(bufferedBytes, 0, bytesRead);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
