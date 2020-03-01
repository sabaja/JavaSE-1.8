package com.SQL_JDBC_20_30_40_41_42;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**
 * 
 * @author sabaja
 * Attraverso la tecnologia JNDI 
 * https://it.wikipedia.org/wiki/Java_Naming_and_Directory_Interface
 * allo scopo di ottenere un'istanza dell'oggetto DataSource 
 * (DataSource sono lo standard Java da utilizzare con le Applicazioni Java EE)
 * per sfruttare un connectionPool direttamente da un server Java EE
 * Dopo Connection le procedure di interrogazione non cambiano 
 * 
 */
public class DATASOURCE_JAVA_EE_jdbc_2_0 {
	public static void main(String[] args) {
		try {
			InitialContext context = new InitialContext();
			DataSource source = (DataSource) context.lookup("jdbc:mysql://localhost:3306/usersdb?autoReconnect=true&autoReconnect=true&useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
			Connection conn = source.getConnection("root","Rinaldi1976");
			Statement stmt = conn.createStatement();
	        ResultSet res = stmt.executeQuery("SELECT * FROM autore");
	        while(res.next()){
	        	System.out.printf("%d | %s | %s", res.getInt(1), res.getString(2), res.getString(3));
	        }
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		}
	}

}
