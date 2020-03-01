package com.net;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
/**
 * 
 * @author sabaja
 * server che ritorna la data e l’ora corrente ( time of day o datetime service , 
 * solitamente in ascolto alla porta 13 )
 * NOTA: 
 * Quando un server accetta una connessione, avrà la sua porta locale con il valore 
 * della porta a cui si è collegato (per esempio 29999 ), che sarà la corrispondente 
 * porta remota del client, mentre la sua porta remota (per esempio 50055 ) sarà 
 * uguale alla porta locale del client al fine di consentire la comunicazione e 
 * rendere disponibile la sua porta locale ( 29999 ) per altre connessioni.
 */
public class DateTimeServer {
	private ServerSocket server_socket;
	private Socket socket;
	private ObjectOutputStream output_stream;
	private ObjectInputStream input_stream;

	public DateTimeServer() throws IOException, ClassNotFoundException {
		try {
			// Creo un server socket sulla porta 29999 con um massimo di 10
			// clients
			server_socket = new ServerSocket(29999, 10);
			
			while (true) {
				listen();
				createStreams();
				initProcessing();
			}
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

	/**
	 * 
	 * createStreams: ottiene dall’oggetto socket , tramite il suo metodo getOutputStream ,
	 * uno stream di output attraverso cui inviamo dei byte al client, e tramite il suo
	 * metodo getInputStream uno stream di input attraverso il quale riceviamo dei byte
	 * dal client. Al fine di ricevere o inviare i dati a un più alto livello, rispetto
	 * a quello dei byte, l’oggetto stream di output è utilizzato come argomento del
	 * costruttore della classe ObjectOutputStream per creare un oggetto che consentirà
	 * di inviare dati primitivi e oggetti serializzabili (come le stringhe), mentre
	 * l’oggetto stream di input è utilizzato come argomento della classe ObjectInputStream
	 * per creare un oggetto che consentirà, invece, di ricevere i predetti dati.
	 * @throws IOException
	 */
	private void createStreams() throws IOException {
		output_stream = new ObjectOutputStream(socket.getOutputStream());//inviare dat al client /scrittura
		output_stream.flush();
		input_stream = new ObjectInputStream(socket.getInputStream());//riceviamo dati dal client /lettura
		showMessage("SERVER -> STREAM CREATI");
	}

	/**
	 * 
	 * initProcessing : stampa il messaggio di richiesta del client, ottenuto mediante 
	 * il metodo readObject dell’oggetto input_stream , e inoltra la risposta del server 
	 * rappresentata dalla data e dall’ora corrente tramite il metodo writeObject 
	 * dell’oggetto output_stream .
	 * @throws IOException
	 * @throws ClassNotFoundException
	 *  
	 */
	private void initProcessing() throws IOException, ClassNotFoundException {
		String client_msg = "";
		client_msg = (String) input_stream.readObject();//
		showMessage(client_msg);
		sendDataToClient("SERVER -> " + getDayTime());
	}

	private void showMessage(String msg) {
		System.out.println(msg);
	}
	
	/**
	 * sendDataToClient(String msg)
	 * tramite un objectoutputstream inviamo dati al client 
	 * @param msg
	 * @throws IOException
	 */
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

    private String getDayTime() {
        return LocalDateTime.now().toString();
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        new DateTimeServer();
    }

}