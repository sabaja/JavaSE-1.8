package com.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;
import java.util.TreeMap;


/**
 * L'interfaccia PrepareStatement è molto utile per parametrizzare
 * uno statement, molto utile laddove esiste un pezzo di codice che
 * usa gli statemnet uguali, differenti solo per i paramentri
 * 
 * Esempio uso una TreeMap (mappa ordinata) per aggiornare il DB
 */
public class PrepareStatementStmtParamTreeMap {

	public static void main(String[] args) {
		String driver = "com.mysql.jdbc.Driver";
		Connection con = null;
		try {
			Class.forName(driver).newInstance();
			String url = "jdbc:mysql://localhost:3306/articoli?autoReconnect=true&useSSL=false";
			con = DriverManager.getConnection(url,"root","Rinaldi1976");
			con.setAutoCommit(false);
			//Query parametrizzata
			String update = "UPDATE autore set nome = ? where id_autore = ?";
			
			//carico la treemap 
			TreeMap<Integer, String> map = setTreeMap();
			
			//ricavo dalla connesione il prepareStatement
			PreparedStatement preStmt = con.prepareStatement(update);
			int n = 0;
			//Iterazione sulla map per valorizzare i parametri
			for(Map.Entry<Integer, String> i : map.entrySet()){
				
				System.out.println(i.getKey() + " " + i.getValue());
				//tramite il map.entry valorizzo i parametri
				
				preStmt.setString(1, i.getValue());//1 (nome) indica il secondo parametro
				preStmt.setInt(2, i.getKey());//2 (id) indica il primo parametro
				//eseguo l'update
				n += preStmt.executeUpdate();
				con.commit();
			}
			System.out.println(n + " updated rows!");
						
		}catch(ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException e){
			e.printStackTrace();
		}

	}
	
	public static TreeMap<Integer, String> setTreeMap(){
		TreeMap<Integer, String> map = new TreeMap<>();
		map.put(2, "Steve");
		map.put(3, "Hirokichi");
		map.put(5, "Barbara");
		map.put(7, "Allison");
		return map;
	}

}
