package com.java.NET;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class URLData {

	public static void main(String[] args) {
		String spec = "http://orangina.eu/";
		try {
			URL url = new URL(spec);

			URLConnection openConn = url.openConnection();
//			ATTENZIONE 
//			Il metodo openConnection crea un oggetto di tipo URLConnection ma non attua alcuna 
//			connessione verso l’host remoto. Infatti, la connessione deve essere effettuata 
//			esplicitamente invocando il metodo connect() 
//			se non si utilizzano dei metodi che l’attuano automaticamente, come per es:
//			getInputStream , getOutputStream , getHeaderField e così via.			
			
			System.out.println("CONTENT ENCODING: " + openConn.getContentEncoding());
			System.out.println("CONTENT LENGHT: " + openConn.getContentLength());
			System.out.println("CONTENT TYPE: " + openConn.getContentType());
			
			//Leggo l'html
			try (BufferedReader br = new BufferedReader(new InputStreamReader(openConn.getInputStream()))){
				String data = null;
				while ((data = br.readLine()) != null){
					System.out.println(data);
				}
			}
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}

	}

}
