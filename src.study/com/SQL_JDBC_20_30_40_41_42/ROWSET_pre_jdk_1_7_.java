package com.SQL_JDBC_20_30_40_41_42;

import com.sun.rowset.JdbcRowSetImpl;

import javax.sql.rowset.JdbcRowSet;
import java.sql.SQLException;

/**
 * 
 * @author sabaja Pre java 7 bisogna scaricare il jar
 *         com.sun.rowset.JdbcRowSetImpl dal sito:
 *         http://www.java2s.com/Code/Jar/c/Downloadcomsunrowsetjar.htm e poi
 *         aggiungere il jar nel progetto
 *         (http://it.wikihow.com/Aggiungere-un-JAR-a-un-Progetto-Java-in-
 *         Eclipse)
 * 
 */
public class ROWSET_pre_jdk_1_7_ {

	public static void main(String[] args) {
		JdbcRowSetDemo.runRowset();

	}

}

class JdbcRowSetDemo {

	private static JdbcRowSet rowSet = null;

	public static void runRowset(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			rowSet = new JdbcRowSetImpl();//Si usa questa classe per implementare rowSet
			rowSet.setUrl("jdbc:mysql://localhost:3306/articoli?autoReconnect=true&useSSL=false");
			rowSet.setUsername("root");
			rowSet.setPassword("Rinaldi1976");
			rowSet.setCommand("SELECT * FROM autore");
			rowSet.execute();
			
			//L'interfaccia ResultSetMetaData fornisce le informazioni del db
			//estratte dal rowSet
			java.sql.ResultSetMetaData rsm = rowSet.getMetaData();
			int num = rsm.getColumnCount();
			//stampo a video le colonne partire sempre da 1
			for(int i = 1; i <= num ; i++)
				System.out.printf("%-20s\t", rsm.getColumnLabel(i));
			System.out.println();
			
			//Stampo a video il risultato con il metodo toString di Object
			while (rowSet.next()) {
                for (int i = 1; i <= num; i++){ 
                    System.out.printf("%-20s\t", rowSet.getObject(i));
                }
                System.out.println();
			}
            
			//Inserimento di un record nella tabell
			 try {
		            rowSet.moveToInsertRow();
		            
		            rowSet.updateString("nome", "Vurgiun");
		            rowSet.updateString("cognome", "Paraparapà");
		            rowSet.updateString("indirizzo", "Leva le mani dal cu");
		            rowSet.updateString("citta", "sodom");
		            rowSet.updateString("telefono", "999-999-999");
		            rowSet.updateString("email", "jkl@vidoo.us");
		            rowSet.updateString("professione", "antro");
		            rowSet.insertRow();
		            rowSet.first();
		        } catch (SQLException ex) {
		            ex.printStackTrace();
		        }
			 
			 //Delete dell'ultimo record 
			 try {
		            rowSet.last();
		            rowSet.deleteRow();
		            System.out.println("Delete row!");
		        } catch (SQLException ex) {
		            ex.printStackTrace();
		        }
		    
			
		} catch (ClassNotFoundException | SQLException ex) {
			ex.printStackTrace();
		}

	}
	
	public JdbcRowSetDemo() {
	}
}