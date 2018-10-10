package com.java.net.ClientServer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collection;
import java.util.Hashtable;
import java.util.Iterator;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 
 * @author sabaja http://forum.ubuntu-it.org/viewtopic.php?t=173851
 * 
 */

public class ClientServerMultiThread {

	private static final int SERVERPORT = 9999;
	private static int port = 10000;
	private static final String host = "127.0.0.127";

	public static void main(String[] args) {
		new Server_T(SERVERPORT, 50, host).start();//Parte il thread del server
		Hashtable<String, Thread> map = getClientMap();//metodo per popolare un hashtable con 5 clients
		Collection<Thread> set = map.values();//genero una collection di thread 
		Iterator<Thread> i = set.iterator();//iterator per iterare sulla collection
		while (i.hasNext()) {
			i.next().start();//Simulazione delle chiamate dei client
		}
	}

	// Thread safe HashTavle
	public static Hashtable<String, Thread> getClientMap() {
		Hashtable<String, Thread> map = new Hashtable<>();
		for (int i = 0; i < 5; i++){
			Client_T cl =  new Client_T(host, SERVERPORT, port++);
			map.put(cl.getName(), cl);
			}
		
		return map;
	}
}

class Server_T extends Thread {
	private ServerSocket server;

	public Server_T(int port, int backlog, String host) {
		try {
			InetAddress adr = InetAddress.getByName(host);// associo un IP fisso
															// al server
			this.server = new ServerSocket(port, backlog, adr);// backlog è il
																// numero
																// massimo delle
																// connessioni
																// in entrata
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		try {
			while (true) { // Inizia un ciclo infinito che però si blocca sul
							// metodo accept()  il server rimane perennemente in attesa di richieste da parte dei client
				synchronized (server) {
					Socket socket = this.server.accept();
					OutputStream os = socket.getOutputStream();// canale di
																// output
																// verso il
																// client
					/*
					 * Poi, per comodità, decoriamo OutputStream con un
					 * BufferedWriter che, mettendoci a disposizione il metodo
					 * write() , consente di inviare il messaggio al client in
					 * modo molto semplice
					 */
					BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));
					bw.write("Ciao client che leggi dalla porta " + socket.getPort() + " sono il server!");
					System.out.println("Messaggio spedito a " + server.getInetAddress());
					bw.close();

				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			try {
				server.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

class Client_T extends Thread {
	private Socket socket;

	// Socket(String host, int port, InetAddress localAddr, int localPort)
	public Client_T(String host, int port, int localPort) {
		try {
			this.socket = new Socket(host, port, InetAddress.getLocalHost(), localPort);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void run() {
		try {
			synchronized (socket) {
				BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				System.out.println(br.readLine());
				br.close();
				socket.close();
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}