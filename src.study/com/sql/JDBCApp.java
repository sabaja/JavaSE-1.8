package com.sql;

import java.sql.*;





public class JDBCApp {

	public static void main(String[] args) {
		String driver = "com.mysql.jdbc.Driver";
		Connection conn = null;
		try {
			//Connettiamo il driver MySql Class.forName carica in memoria il driver
			//Si carica in memoria al DriverManger grazie ad un inizializzatore statico del tutto trasparente
			
			Class.forName(driver).newInstance();//new instance serve per sopperire ad alcuni problemi
			
			//"jdbc:mysql://localhost:3306/MySqlDB?autoReconnect=true&useSSL=false"
//			Questa stringa è scritta in un formato particolare è costituita 
//			da più parti separate dal segno di due punti ( : ), 
//			dove la prima parte indica il protocollo di comunicazione ( jdbc ), 
//			la seconda indica il sotto-protocollo di comunicazione ( mysql ) 
//			e la terza indica l’indirizzo e il nome del database da gestire.
			//autoReconnect=true&useSSL=false serve a disabilitare SSL and also suppress the SSL errors.
			String url = "jdbc:mysql://localhost:3306/MySqlDB?autoReconnect=true&useSSL=false";
			
			//Il metodo getConnection() ha come secondo e terzo argomento si indicano, rispettivamente, il 
			//nome utente e la password necessari per accedere alla base dati.*/
			
			conn = DriverManager.getConnection(url, "root", "Rinaldi1976");
			
			//creiamo un oggetto  statement per interrogare/aggiornare il db
			Statement cmd = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);

			//query da eseguire
			String query = "SELECT * FROM Authors";
			
			//Otteniamo un serultSet con tutti in dati della query
			ResultSet res = cmd.executeQuery(query);
			
			//Stampiamo il risultato
			while(res.next()){
				
				System.out.printf("%d| %s | %s | %s | %s\n", 
						res.getInt("id_author"), 
						res.getString("name"),
						res.getString("surname"),
						res.getString("telephone"),
						res.getString("email"));
			}
			//chiudo le connessioni			
			res.close();//resultSet per interrogare
			cmd.close();//Statemt oggetto per interfacciarsi al DB/Tabelle 
			conn.close();//connessione per connettersi al DB
		
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
			e.printStackTrace();
		}

	}

}
