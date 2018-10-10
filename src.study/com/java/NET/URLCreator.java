package com.java.NET;

import java.net.MalformedURLException;
import java.net.URL; 
/**
 * 
 * @author sabaja
 * Utilizzo degli URL Un URL è rappresentato dalla classe URL 
 * che fornisce i seguenti costruttori principali. 
 * 
 * public URL(String spec) throws MalformedURLException crea un URL 
 * come specificato dal parametro spec .
 * In pratica questo costruttore permette di creare oggetti che
 * rappresentano URL assoluti. 
 * 
 * public URL(URL context, String spec) throws MalformedURLException
 * crea un URL relativamente a un altro. 
 * Infatti, la stringa spec contiene generalmente l’indicazione 
 * di una risorsa il cui percorso sarà risolto a partire dall’URL 
 * indicato dalla stringa context . 
 * 
 * URL(String protocol, String host, String file) throws MalformedURLException
 * crea un URL specificando il nome del protocollo, 
 * l’host di connessione e il file da reperire. 
 * 
 * public URL(String protocol, String host, int port, String file) 
 * throws MalformedURLException 
 * crea un URL specificando il nome del protocollo da usare, 
 * il nome dell’host, la porta di connessione e il file da reperire.
 * 
 *
 */

public class URLCreator { 
	public static void main(String args[]) throws MalformedURLException { 
		URL a_url = new URL("http://www.pellegrinoprincipe.c om:80/JAVASCRIPT/elaJa_V0.1/index.html"); 
		URL r_url = new URL(a_url, "about/index.html"); 
		URL param_url = new URL("http", "www.pellegrinoprincipe.com", "index.html"); // ottieni le singole parti dell'URL 
		System.out.println("[PROTOCOLLO: " + r_url.getProtocol() + "] [HOST: " + r_url.getHost() + "] [AUTHORITY: " + r_url.getAuthority() + "] [PATH: " + r_url.getPath() + "]"); 
		}  
}