package com.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
/**
 * 
 * @author sabaja
 *	In questa classe non utilizziamo i decoratori
 *	cosi otteniamo un copia perfetta di file bit a bit
 *
 */
public class CopiadiBackupdiUnFile_FileExample {
	
	public static void main(String []args){
		String path = "/home/sabaja/Scrivania/Dev-space/Input_Ouput";
		File inFile = new File(path, File.separator + "Infile");
		File outFile = new File(path, File.separator + "Outfile");
		outFile.setWritable(true);//da java 6 è possibile settare il file
		outFile.setExecutable(true, true);//
		try (FileInputStream fis = new FileInputStream(inFile);
			 FileOutputStream fos = new FileOutputStream(outFile)) {
			int istream = 0;
			byte[] buffBytes = new byte[1024];
			while ((istream = fis.read(buffBytes)) > -1)
				fos.write(buffBytes, 0, istream);
			System.out.println("File di buckup scritto " + outFile.getName() + " @Path " +
					outFile.getParent() + "\nfreeSpace: " + outFile.getFreeSpace() +
					" Total space: " + outFile.getTotalSpace() + " usable space: " +
					outFile.getUsableSpace());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
