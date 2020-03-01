package com.net;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;


/**
 * il client che effettua la connessione al datetime server. In particolare 
 * abbiamo utilizzato il costruttore della classe Socket per creare un oggetto socket 
 * di connessione con il server, che accetta come primo argomento una stringa che 
 * indica il nome host del server e come secondo argomento un intero che indica 
 * il numero della porta remota di connessione. Per il resto il codice appare simile, 
 * come logica, a quello implementato per il server, tranne per la denominazione 
 * differente dei metodi attemptToConnect e sendDataToServer . Output 21.7 Dal Listato 
 * 21.8 Classe ClientToDateTimeServer.
 *  CLIENT -> CONNESSIONE VERSO UN SERVER... 
 *  CLIENT -> CONNESSIONE AVVENUTA VERSO localhost/127.0.0.1 
 *  ALLA PORTA REMOTA 29999 E ALLA PORTA LOCALE 50055 
 *  CLIENT -> STREAM CREATI SERVER -> Tue Dec 31 11:49:04 CET 2013 
 *  CLIENT -> CHIUSURA CONNESSIONE SOCKET
 * @author sabaja
 *
 */
public class ClientToDateTimeServer {

	private Socket socket;
	private ObjectOutputStream output_stream;
	private ObjectInputStream input_stream;

	public ClientToDateTimeServer() throws IOException, ClassNotFoundException {
		try {
			attemptToConnect();
			createStreams();
			initProcessing();
		} finally {
			close();
		}
	}

	private void attemptToConnect() throws IOException {
		showMessage("CLIENT -> CONNESSIONE VERSO UN SERVER..."); // connessione
																	// verso il
																	// server in
																	// ascolto
																	// sulla
																	// porta
																	// 29999
		socket = new Socket("localhost", 29999);
		showMessage("CLIENT -> CONNESSIONE AVVENUTA VERSO " + socket.getInetAddress() + " ALLA PORTA REMOTA "
				+ socket.getPort() + " E ALLA PORTA LOCALE " + socket.getLocalPort());
	}

	private void createStreams() throws IOException {
		output_stream = new ObjectOutputStream(socket.getOutputStream());
		output_stream.flush();
		input_stream = new ObjectInputStream(socket.getInputStream());
		showMessage("CLIENT -> STREAM CREATI");
	}

	private void initProcessing() throws IOException, ClassNotFoundException {
		String server_msg = "...";
		sendDataToServer("CLIENT -> DAMMI IL TEMPO CORRENTE");
		server_msg = (String) input_stream.readObject();
		showMessage(server_msg);
	}

	private void showMessage(String msg) {
		System.out.println(msg);
	}

	private void sendDataToServer(String msg) throws IOException {
		output_stream.writeObject(msg);
		output_stream.flush();
	}

    private void close() throws IOException {
        showMessage("CLIENT -> CHIUSURA CONNESSIONE SOCKET");
        if (output_stream != null && input_stream != null && socket != null) {
            output_stream.close();
            input_stream.close();
            socket.close();
        }
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        new ClientToDateTimeServer();
    }

}
