package com.net;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Da vedere lo stesso funzionamento con i DatagramSocket pagina:
 * https://play.google.com/books/reader?printsec=frontcover&output=reader&id=rjztAgAAQBAJ&pg=GBS.PT707.w.3.2.4.0.1
 * 
 * la classe EchoServer crea un Server attraverso il costruttore della classe
 * ServerSocket, ponendolo in ascolto sulla porta 30000 e consentendo, anche
 * qui, un massimo di 10 client in attesa. I metodi listen e messaggio BYE , con
 * il quale si chiuderà la connessione, legge il prossimo messaggio e lo invia
 * tale e quale, come echo, al client. Inoltre, in questo esempio il server di
 * echo non rimarrà in attesa all’infinito, ma una volta terminato il processing
 * con il client, terminerà anch’esso.
 * 
 * @author sabaja
 *
 */
public class EchoServer {

	private Socket socket;
	private ServerSocket server_socket;
	private ObjectOutputStream output_stream;
	private ObjectInputStream input_stream;

	public EchoServer() throws IOException, ClassNotFoundException {
		server_socket = new ServerSocket(30000, 10);
		try {
			listen();
			createStreams();
			initProcessing();
		} finally {
			close();
		}
	}

	private void listen() throws IOException {
		showMessage("SERVER -> IN ATTESA DI CONNESSIONI...");
		socket = server_socket.accept(); // pongo il server in attesa di
											// connessioni
		showMessage("SERVER -> CONNESSO CON " + socket.getInetAddress() + " ALLA PORTA REMOTA " + socket.getPort()
				+ " E ALLA PORTA LOCALE " + socket.getLocalPort());
	}

	private void createStreams() throws IOException {
		output_stream = new ObjectOutputStream(socket.getOutputStream());// inviare
																			// dat
																			// al
																			// client
																			// /scrittura
		output_stream.flush();
		input_stream = new ObjectInputStream(socket.getInputStream());// riceviamo
																		// dati
																		// dal
																		// client
																		// /lettura
		showMessage("SERVER -> STREAM CREATI");
	}

	private void initProcessing() throws IOException, ClassNotFoundException {
		String client_msg = "";
		sendDataToClient("SERVER -> Ciao digita BYE per terminare...");
		do {
			client_msg = (String) input_stream.readObject();
			showMessage("CLIENT -> " + client_msg);
			sendDataToClient("SERVER ECHO: -> " + client_msg);
		} while (!client_msg.trim().equals("BYE"));
	}

	private void sendDataToClient(String msg) throws IOException {
		output_stream.writeObject(msg);
		output_stream.flush();
	}

    private void close() throws IOException {
        showMessage("SERVER -> CHIUSURA CONNESSIONE SOCKET");
        if (output_stream != null && input_stream != null && socket != null) {
            output_stream.close();
            input_stream.close();
            socket.close();
        }
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        new EchoServer();
    }


    private void showMessage(String msg) {
        System.out.println(msg);
    }

}
