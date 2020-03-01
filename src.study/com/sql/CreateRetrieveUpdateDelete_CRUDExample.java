package com.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateRetrieveUpdateDelete_CRUDExample {

	/**
	 * 
	 * @param args
	 * Sono tutte le operazioni di Create Retrieve Upadate e Delete
	 */

	public static void main(String[] args) {
		String driver = "com.mysql.jdbc.Driver";
		Connection con = null;
		try {
			Class.forName(driver).newInstance();
			String url = "jdbc:mysql://localhost:3306/articoli?autoReconnect=true&useSSL=false";
			con = DriverManager.getConnection(url,"root","Rinaldi1976");
			String update1 = "INSERT INTO autore (nome, cognome, indirizzo, citta, telefono, email, professione) " + 
			"VALUES " + 
			"(\"Bart\", \"Mell\", \"Vert street 82\", \"London\", \"8-123-2915095620\", \"ostery@org.com\", \"Pornos Activity\");";
			
			//Per inserire e modificare si usa uno statement direttamente.
			Statement cmd = con.createStatement();
			
			//Insert into 
			//un update restituisce un numero di righe aggiornato
			int n = cmd.executeUpdate(update1);
			System.out.println("Insert Done!");
			
			//Insert into tramite bean Autore
			Autore a = new Autore("Francesca", "Orti", "Via Bari 13", "Châtillon", "0713912371", "eccasls@part.it", "Girls-only");
			String insert = String.format("INSERT INTO autore (nome, cognome, indirizzo, citta, telefono, email, professione) " + 
			"VALUES (\"%s\", \"%s\", \"%s\", \"%s\", \"%s\", \"%s\", \"%s\");", a.getNome(), a.getCognome(),a.getIndirizzo(),a.getCitta(),
			a.getTelefono(),a.getEmail(),a.getProfessione());
			int i = cmd.executeUpdate(insert);
			System.out.println("Insert Done!");
			
			//UpDate
			String update2 = String.format("UPDATE autore SET cognome = \"%s\", nome = \"%s\" WHERE id_autore = 4 ", "Dobois", "Margot");
			int l = cmd.executeUpdate(update2);
			System.out.println("Update Done!");
			
			
		} catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}

	}
}

class Autore{
	private String nome, cognome, indirizzo, citta, telefono, email, professione;

	public Autore(String nome, String cognome, String indirizzo, String citta, String telefono, String email,
			String professione) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.indirizzo = indirizzo;
		this.citta = citta;
		this.telefono = telefono;
		this.email = email;
		this.professione = professione;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public String getCitta() {
		return citta;
	}

	public void setCitta(String citta) {
		this.citta = citta;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getProfessione() {
		return professione;
	}

	public void setProfessione(String professione) {
		this.professione = professione;
	}
	
	
}
