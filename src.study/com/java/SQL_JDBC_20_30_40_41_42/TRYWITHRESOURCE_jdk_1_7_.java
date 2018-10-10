package com.java.SQL_JDBC_20_30_40_41_42;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**
 * 
 * @author sabaja
 * Piu facile di sicuro
 */
public class TRYWITHRESOURCE_jdk_1_7_ {

    public static void main(String args[]) {
        selectFromDB();
    }
    public static void selectFromDB() {
		//dalla versione JDK 1.6 è possibile omettere il comando:
		//Class.forName("com.mysql.jdbc.Driver");
		//Infatti mediante il "Service Provider" o "Java Standard Exension Mechanism"
		//E' possibile rendere il DriverManager.getConnection() responsabile di trovare
		//il giusto driver a runtime - Il driver caricato però deve contenere il file META-INF/services/java.sql.Driver
		//esempio: nel archivio jar di mysql è presente un file INDEX.LIST dove contente l'idirizzo del Driver
		//oppure (non ne sono certo ) nel file MANIFEST.MF c'è la riga di comando Export-Package: com.mysql.jdbc;
		//questo permette di omettere il diver cosi:
		
		//Class.forName("com.mysql.jdbc.Driver");
    	
    	
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/articoli?autoReconnect=true&useSSL=false", 
        		"root", "Rinaldi1976");
        Statement stmt = conn.createStatement();
        ResultSet res = stmt.executeQuery("SELECT * FROM autore")) {
            while (res.next()) {
                System.out.printf("%s : %s (%s)\n", res.getString(2), res.getString(3),
						res.getString(7));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } 
    }
	
}
