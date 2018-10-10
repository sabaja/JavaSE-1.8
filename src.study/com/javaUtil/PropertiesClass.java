package com.javaUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

public class PropertiesClass {
	private static  Properties properties;
	private static File file;

	static{
		file = new File("/home/sabaja/Scrivania/workspace/Prj_JavaSE-1.8/src_studying/com/javaUtil/propertiesFile.properties");
		properties = new Properties();
	}	
	
	public PropertiesClass() throws IOException{
		loadProperties();
	}
	
	private static void loadProperties() throws IOException{
		InputStream is = null;
		try {
			is = new FileInputStream(file);
			properties.load(is);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void printProperties(){
		if(properties != null){
			properties.list(System.out);
		}
	}
	
	public String getProperty(String key){
		if(key == null){
			if(!properties.containsKey(key)){
				return "";
			}
		}
		return properties.getProperty(key);
	}
	
	public void setProperty(String key, String value){
		if(key != null){
			if(!value.equals(null)){
				if(properties.containsKey(key)){
					properties.setProperty(key, value);
					this.saveProperties();
				}
			}
		}
	}
	
	private void saveProperties(){
		OutputStream os = null;
		try {
			os = new FileOutputStream(file);
			properties.store(os, "");
		} 
		catch (IOException e){
			e.printStackTrace();
		}
	}
}

