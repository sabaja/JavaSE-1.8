package com.multithreadsExercises;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


/**
 * 
 * @author sabaja
 *
 * La classe @CollaborazioneScritturaLetturasuFile si occupa
 * di scrive e leggere contemporaneamente su file tramite
 * l'istanza dell'oggetto MangeIo la quale per @Manage.i:private int volte di seguito
 * scriverà su file e successivamente leggerà 
 * 
 */
public class CollaborazioneScritturaLetturasuFile {

	public static void main(String[] args) {
		WriteRead io = new WriteRead();
		ManageIO manageIO = new ManageIO(io);
		manageIO.setI(4);
		Thread t1 = new Thread(manageIO);

		t1.start();
		
		/**
		 * Schedulo l'esecuzione dell'oggetto manageIo ogni 2 sec 
		 * Con un pool di 2 thread attivi.
		 * 
		 */
		manageIO.setI(10);//per 10 volte
		ScheduledExecutorService stp = Executors.newScheduledThreadPool(2);
		stp.schedule(manageIO, 2, TimeUnit.SECONDS);
		Instant start = Instant.now();
		System.out.println("Inizio conteggio " + Instant.now());
		stp.shutdown();//uccido lo scheduler altrimenti rimane attivo
		//prendo il tempo che intercorre dalla shutdown tramite
		//un oggetto Instant start e uno end 

		try {
			
			stp.awaitTermination(5, TimeUnit.SECONDS);
			Instant end = Instant.now();
			System.out.println("stop conteggio " + Instant.now() + " tempo intercorso millisec: " + 
							  ChronoUnit.MILLIS.between(start, end));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

/**
 * 
 * @author sabaja
 * classe di gestione dei thread.
 * Il metodo run lancia i metodi sincronizzati writeFile()
 * e readFile() i quali lavorano in concorrenza.
 */
class ManageIO implements Runnable {

	private WriteRead io;
	private int i;
	
	public ManageIO(WriteRead io) {
		this.io = io;
	}

	public int getI() {
		return i;
	}

	public void setI(int i) {
		this.i = i;
	}

	@Override
	public void run() {
		 int n = 0;
		while (n++ < i) {
			io.writeFile();
			io.readFile();
		}
	}
}

class WriteRead {

	private static int iD_time = 1;
	private volatile boolean flagIO = false;
	private static File name = new File("prova.txt");

	/**
	 * Il metodo  sincronizzato @writeFile
	 * è governato tramite la variabile volatile boolean @flagIO,
	 * la quale è in stato false se il file non è stato scritto 
	 * e true se è stato scritto.
	 * Se @flagIO è true (quindi scritto) il metodo si blocca
	 * in attesa della lettura. Viceversa procederà a una nuova scrittura
	 * sovrascrivendo il file esistente.
	 */
	public synchronized void writeFile() {
		if (flagIO) {
			try {
				super.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		try {
			System.out.println("Writing file " + iD_time + "° time @" + LocalDateTime.now());
			FileOutputStream prova = new FileOutputStream(name);
			PrintStream scrivi = new PrintStream(prova);

			for (int i = 0; i < 10; i++) {
				scrivi.print("Test-" + (iD_time) + (i) + " ");
			}

			flagIO = true;
			super.notifyAll();
		} catch (IOException e) {
			System.out.println("Errore: " + e);
			System.exit(1);
		}
	}

	/**
	 * Il metodo  sincronizzato @readFile
	 * è governato tramite la variabile volatile boolean @flagIO,
	 * la quale è in stato false se il file non è stato scritto 
	 * e true se è stato scritto.
	 * Se @flagIO è in stato true (quindi scritto) il metodo procede alla lettura.
	 * Viceversa aspetta una nuova scrittura che avverà tramite @writeFile
	 */
	public synchronized void readFile() {
		if (!flagIO) {
			try {
				super.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		if (name.isFile()) {
			try {
				System.out.println("Reading file " + iD_time + "° time @" + LocalDateTime.now());
				BufferedReader input = new BufferedReader(new FileReader(name));
				StringBuffer buffer = new StringBuffer();
				String text;
				while ((text = input.readLine()) != null)
					buffer.append(text + "\n");
				input.close();

				System.out.println(buffer.toString());
				iD_time++;
				flagIO = false;
				super.notifyAll();
			} catch (IOException ioException) {
			}
		}
	}

}