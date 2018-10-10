package com.java.SQL;


import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class IndipendezaDB_ConFileDiProperties {


	private static  Properties properties;
	///home/sabaja/Scrivania/workspace/Prj_JavaSE-1.8/src.study/com/java/SQL/propertiesDB.properties
	private static final String propertiesFile = "/home/sabaja/Scrivania/workspace/Prj_JavaSE-1.8/src.study/com/java/SQL/propertiesDB.properties";
	private static String query = "select * from Authors";
	private static String driver = "";
	private static String url = "";
	
	//carico staticament il file di properties con i dati necessari alla connesione e definisco le variabili dirver e url
	static{
		properties = new Properties();
		try {
			properties.load(new FileInputStream(propertiesFile));
			driver = properties.getProperty("jdbcDriver");
			url = properties.getProperty("jdbcUrl");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) {
		try {
			Connection con = null;
			Statement cmd = null;
			ResultSet res = null;
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, properties.getProperty("user"), properties.getProperty("password"));
			cmd = con.createStatement();
			res = cmd.executeQuery(query);
			while(res.next()){
				
				System.out.printf("%d| %s | %s | %s | %s\n", 
						res.getInt("id_author"), 
						res.getString("name"),
						res.getString("surname"),
						res.getString("telephone"),
						res.getString("email"));				
				
			}
				
			
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

	}

}
