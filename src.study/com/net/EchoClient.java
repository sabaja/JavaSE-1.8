package com.net;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;


/**
 * Da vedere lo stesso esempio con i DatagramSocket pag:
 * https://play.google.com/books/reader?printsec=frontcover&output=reader&id=rjztAgAAQBAJ&pg=GBS.PT709.w.11.0.131
 * 
 * Gli esempi mostrati finora non consentono l’accesso multiplo e contemporaneo da parte di più client ai nostri server. 
 * Per ovviare a questo limite dobbiamo far uso dei ­thread, per esempio nel seguente modo: 
 * dopo che il metodo accept di un oggetto di tipo ServerSocket ha stabilito la connessione con un client e ha ritornato 
 * un valido oggetto di tipo Socket , lo stesso potrà essere utilizzato all’interno di una classe 
 * che implementerà un oggetto Runnable , passato come argomento al costruttore della classe Thread , 
 * il cui metodo run gestirà la logica di interazione con il client in modo non bloccante.
 *
 */
public class EchoClient {

	private Socket socket;
	private ObjectOutputStream output_stream;
	private ObjectInputStream input_stream;
	
	public EchoClient() throws IOException, ClassNotFoundException {
		super();
		try {
			attemptToConnect();
			createStreams();
			initProcessing();
		}
		finally{
			close();
		}
	
	}
	
	private void attemptToConnect() throws IOException {
		showMessage("CLIENT -> CONNESSIONE VERSO UN SERVER..."); 
		socket = new Socket("localhost", 30000); // connessione al server in ascolto sulla porta 30000 
		showMessage("CLIENT -> CONNESSIONE AVVENUTA VERSO " + socket.getInetAddress() + 
				" ALLA PORTA REMOTA " + socket.getPort() + " E ALLA PORTA LOCALE " + socket.getLocalPort()); 
	}
	
	private void createStreams() throws IOException {
		output_stream = new ObjectOutputStream(socket.getOutputStream());
		output_stream.flush();
		input_stream = new ObjectInputStream(socket.getInputStream());
		showMessage("CLIENT -> STREAM CREATI");
	}

	private void initProcessing() throws IOException, ClassNotFoundException {
		// messaggio di benvenuto del server 
		String server_msg = (String) input_stream.readObject(); 
		showMessage(server_msg); 
		do // interazione con il server 
		{ 
			sendDataToServer(readFromInput()); 
			server_msg = (String) input_stream.readObject(); 
			showMessage(server_msg); 
		} while (!server_msg.contains("BYE"));
	}

	
	private void showMessage(String msg) {
		System.out.println(msg);
	}

	private void sendDataToServer(String msg) throws IOException {
		output_stream.writeObject(msg);
		output_stream.flush();
	}
	
	private String readFromInput() { 
		Scanner in = new Scanner(System.in); 
		return in.nextLine(); 
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
        new EchoClient();
    }
}
