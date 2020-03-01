package com.SQL_JDBC_20_30_40_41_42;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static java.sql.DriverManager.getConnection;

/**
 * @author sabaja con jdbc 3.0 abbiamo la possibilità di utilizzare i ResultSet
 * dinamicamente. Con un ResultSet è possibile ottenere i dati e
 * modificarli al volo in modalità connessa
 */
public class ResulsetAggiornabiliJdbc30 {

    public static void main(String[] args) {
        Connection conn = null;
        final String driver = "com.mysql.jdbc.Driver";
        try {
            Object instance = Class.forName(driver).newInstance();
            String url = "jdbc:mysql://localhost:3306/articoli?autoReconnect=true&useSSL=false";
            conn = getConnection(url, "root", "R!nald!1976");
            conn.setAutoCommit(false);
            // Otteniamo il resultSet rs scrollable e sensitivo al cambiamento,
            // oggetto aggiornabile
            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stmt.executeQuery("SELECT * FROM articoli.rivista where id_rivista >=3;");
            int len = rs.getFetchSize();

            System.out.println("Updating a result set ...");
			
			
			// Aggiornamento di un campo
			// Move to BFR position so while-loop works properly
			rs.beforeFirst();
			// STEP 7: Extract data from result set
			while (rs.next()) {
				// Retrieve by column name
				String nome = "++ ";
				String temp = rs.getString("nome");
				nome += temp;
				rs.updateString("nome", nome);
				rs.updateRow();
			}
			conn.commit();

			// Inserimento di un record
			// Insert a record into the table.
			// Move to insert row and add column data with updateXXX()
			System.out.println("Inserting a new record...");
			rs.moveToInsertRow();
			rs.updateString("nome", "For people devs");
			rs.updateString("editore", "Nebraska");
			rs.updateInt("nr", 40);
			// Commit row
			rs.insertRow();
			conn.commit();
			
			//chiusura
			rs.close();
			stmt.close();
			conn.close();

		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

	}

}
