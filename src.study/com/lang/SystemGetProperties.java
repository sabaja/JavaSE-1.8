package com.lang;

import java.io.*;
import java.util.Properties;

public class SystemGetProperties {

	public static void main(String[] args) {
		String os_arc = System.getProperty("os.arch");
		String os_name = System.getProperty("os.name");
		String os_version = System.getProperty("os.version");
		System.out.println(os_arc + " " + os_name + " " + os_version);
		
		Properties properties = System.getProperties();
		try {
			System.setOut(new PrintStream(new File("System.log")));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		properties.list(System.out);//stampa su file
		System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
		properties.list(System.out);//stampa a video7
		Properties ptemp = new Properties();
		ptemp.setProperty("default.cpp.compiler", "gcc -std=14");
		ptemp.setProperty("user.properties.path", "/media/jacoposa/73308a4b-f492-4f8b-a0d1-d6db85725a47/home/jacoposa/Scrivania/Devspace/Java4");
		ptemp.list(System.out);
		String temp = ptemp.getProperty("default.cpp.compiler");
		ptemp.setProperty("default.cpp.compiler", temp.replace("gcc -std=14", "gcc-std:14++"));
		ptemp.list(System.out);
	}
	

}
