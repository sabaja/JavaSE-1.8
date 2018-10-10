package com.java.net.ClientServer;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 
 * @author sabaja 
 * Scriviamo un semplice server che implementa Runnable che si mette in ascolto sulla
 * porta 9999 e restituisce, ai client che si connettono, una stringa di
 * saluto per poi interrompere la comunicazione:
 */
class Server implements Runnable {
	private ServerSocket server = null;

	public Server() {

		try {
			server = new ServerSocket(9999);// stanziando un ServerSocket con a
											// porta 9999
			System.out.println("Server avviato, in ascolto sulla " + "porta " + server.getLocalPort());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
	while(true){//Inizia un ciclo infinito che però	si blocca sul metodo accept()
			try {
				/* Una volta ricevuta una connessione da parte di un client, 
				 * il metodo accept() viene eseguito e restituisce il socket
				 * che rappresenta il client con tutte le informazioni necessarie
				 */
				Socket socket = server.accept();
				OutputStream os = socket.getOutputStream();//canale di output verso il client
				/*
				 * Poi, per comodità, decoriamo questo OutputStream con un
				 * BufferedWriter che, mettendoci a disposizione il metodo 
				 * write() , consente di inviare il messaggio al client in 
				 * modo molto semplice
				 */
				BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));
				bw.write("Ciao client " + socket.getInetAddress() + " sono il server!");
				System.out.println("Messaggio spedito a "
				+ server.getInetAddress());
				bw.close();
				server.close();
				break;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

class Client implements Runnable {
	private InetAddress host = null;
	private Socket s = null;

	public Client() {
		try {
			host = InetAddress.getLocalHost();
			s = new Socket(host, 9999);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
			System.out.println(br.readLine());
			br.close();
			s.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}


public class ClientServerMonoThread{
	public static void main(String []args){
		new Thread(new Server()).start();
		new Thread(new Client()).start();
	}
}