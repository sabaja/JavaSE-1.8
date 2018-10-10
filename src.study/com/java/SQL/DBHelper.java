package com.java.SQL;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/*Meglio usare le properties*/

public class DBHelper{
	public static void main(String []args){
	DBHelper_.dbtype = "MySQL"; 
	//DBHelper_.output = ta_result;
	DBHelper_.loadDriver();
	String ip = getDBProperty("ip");
	String port = getDBProperty("port");
	String dbname = getDBProperty("dbname");
	String uname = getDBProperty("uname");
	String pwd =getDBProperty("pwd");
	DBHelper_.doConnection(ip, port, dbname, uname, pwd);
	DBHelper_.closeConnection();
	
	}
	
	public static String getDBProperty(String key){
		Properties properties = new Properties();
		final String strPrFile = "/home/sabaja/Scrivania/workspace/Prj_JavaSE-1.8/src.study/com/java/SQL/propertiesAutoriDB.properties";
		String value = "";
		try {
			properties.load(new FileInputStream(new File(strPrFile)));
			value = properties.getProperty(key);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return value;
	}
}

class DBHelper_ {
	public static String dbtype; // tipo di RDBMS
	//public static JTextArea output; // output error field
	private static Connection conn; //

	private static String drivers_name[] = { 
			"com.mysql.jdbc.Driver", // MySQL 5.6
			"oracle.jdbc.OracleDriver", // Oracle 12.1
			"com.ibm.db2.jcc.DB2Driver", // DB2 10.5
			"com.microsoft.sqlserver.jdbc.SQLServerDriver", // SQL Server 2012
	};
	private static String url_format[] = { 
			"jdbc:mysql://", 
			"jdbc:oracle:thin:@", 
			"jdbc:db2://", 
			"jdbc:sqlserver://"
	};

	public static void loadDriver() {
		String driver = "";
		switch (dbtype) {
		case "MySQL":
			driver = drivers_name[0];
			break;
		case "Oracle":
			driver = drivers_name[1];
			break;
		case "DB2":
			driver = drivers_name[2];
			break;
		case "SQL Server":
			driver = drivers_name[3];
			break;
		default:
			break;
		}
		try {
			Class.forName(dbtype);
		} catch (ClassNotFoundException ex) {
			System.out.println(ex.getMessage());
		}
	}

	public static Connection doConnection(String url, String port, String dbname, String u_name, String pwd) {
		String complete_url = "";
		switch (dbtype) {
		//String url = "jdbc:mysql://localhost:3306/MySqlDB?autoReconnect=true&useSSL=false";
		case "MySQL":
			complete_url = url_format[0] + url + ":" + port + "/" + dbname;
			break;
		case "Oracle":
			complete_url = url_format[1] + url + ":" + port + ":" + dbname;
		case "DB2":
			complete_url = url_format[2] + url + ":" + port + "/" + dbname;
		case "SQL Server":
			complete_url = url_format[3] + url + ":" + port + ";" + "databaseName=" + dbname;
		default:
			break;
		}
		try {
			conn = DriverManager.getConnection(complete_url, u_name, pwd);
			System.out.println("Connessione effettuata con successo!!!");
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
		return conn;
	}

	public static void closeConnection() { // connessione al database
		try {
			if (conn != null) {
				conn.close();
				System.out.println("Disconnessione effettuata con successo!!!");
			}
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
	}
}
