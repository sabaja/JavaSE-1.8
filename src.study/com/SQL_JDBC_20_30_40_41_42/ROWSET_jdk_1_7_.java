package com.SQL_JDBC_20_30_40_41_42;

import javax.sql.RowSetEvent;
import javax.sql.RowSetListener;
import javax.sql.rowset.JdbcRowSet;
import javax.sql.rowset.RowSetProvider;
import java.sql.SQLException;



/**
 * 
 * @author sabaja
 * 
 *         Advantage of RowSet - The advantages of using RowSet are given below:
 *         It is easy and flexible to use It is Scrollable and Updatable by default
 *         
 *         The instance of RowSet is the java bean component because it has properties and java bean notification mechanism. 
 *         It is introduced since JDK 5.
 *         
 *         It is the wrapper of ResultSet. It holds tabular data like ResultSet but it is easy and flexible to use.
 *         The implementation classes of RowSet interface are as follows:
 *         JdbcRowSet
 *         CachedRowSet
 *         WebRowSet
 *         JoinRowSet
 *         FilteredRowSet
 *         Let's see how to create and execute RowSet.
 *		   http://www.javatpoint.com/jdbc-rowset
 */

public class ROWSET_jdk_1_7_ {

	public static void main(String[] args) throws Exception {
		RowSetExample.rowSetCretion();
		//RowSetExampleWithEventHandling.rowSetCretion();

	}
}

/**
 * 
 * @author sabaja
 *	Qui creiamo il rowSet in stile jdk_1_7 Tramite la classe {@link RowSetProvider}  un API che permette di ottenere
 *	un rowset caricato sul Driver passato al DriverManager
 *
 */
class RowSetFactory{
	public static JdbcRowSet build() throws ClassNotFoundException, SQLException{
		Class.forName("com.mysql.jdbc.Driver");

		// Creating and Executing RowSet
		// It is the new way to get the instance of JdbcRowSet since JDK 7.
		JdbcRowSet rowSet = RowSetProvider.newFactory().createJdbcRowSet();
		rowSet.setUrl("jdbc:mysql://localhost:3306/articoli?autoReconnect=true&useSSL=false");
		rowSet.setUsername("root");
		rowSet.setPassword("Rinaldi1976");
		return rowSet;

	}
}


class RowSetExample{
	public static void rowSetCretion() throws Exception {
		JdbcRowSet rowSet = RowSetFactory.build();
		rowSet.setCommand("select * from autore;");
		rowSet.execute();
		//L'interfaccia ResultSetMetaData fornisce le informazioni del db
		//estratte dal rowSet
		java.sql.ResultSetMetaData rsm = rowSet.getMetaData();
		int num = rsm.getColumnCount();
		//stampo a video le colonne partire sempre da 1
		for(int i = 1; i <= num ; i++)
			System.out.printf("%-20s\t", rsm.getColumnLabel(i));

		while (rowSet.next()) {
			// Generating cursor Moved event
			System.out.print(rowSet.getInt(1));
			System.out.print("\t" + rowSet.getString(2));
			System.out.print("\t" + rowSet.getString(3));
			System.out.print("\t" + rowSet.getString(4));
			System.out.print("\t" + rowSet.getString(5));
			System.out.print("\t" + rowSet.getString(6));
			System.out.print("\t" + rowSet.getString(7));
			System.out.print("\t" + rowSet.getString(8) + "\n");
		}
		
		/*
		 * Si può fare anche così per stampare
		 * while (rowSet.next()) {
                for (int i = 1; i <= num; i++) {
                    System.out.printf("%-20s\t", rowSet.getObject(i));
                }
                System.out.println();             
		 */
		
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
		
	}
}


class RowSetExampleWithEventHandling {
	public static void rowSetCretion() throws Exception {
		
		JdbcRowSet rowSet = RowSetFactory.build();
		rowSet.setCommand("select * from autore;");
		rowSet.execute();

		// Adding Listener and moving RowSet
		rowSet.addRowSetListener(new MyListener());

		while (rowSet.next()) {
			// Generating cursor Moved event
			System.out.println("Id: " + rowSet.getString(1));
			System.out.println("Name: " + rowSet.getString(2));
			System.out.println("Last: " + rowSet.getString(3));
		}

	}
}

/**
 * 
 * @author sabaja
 * {@link RowSetListener} E' un interfaccia che notifica quando il rowSet subisce un'azione
 * 
 */
class MyListener implements RowSetListener {
	@Override
	public void cursorMoved(RowSetEvent event) {
		System.out.println("Cursor Moved...");
	}
	
	@Override
	public void rowChanged(RowSetEvent event) {
		System.out.println("Cursor Changed...");
	}

	@Override
	public void rowSetChanged(RowSetEvent event) {
		System.out.println("RowSet changed...");
	}
}
