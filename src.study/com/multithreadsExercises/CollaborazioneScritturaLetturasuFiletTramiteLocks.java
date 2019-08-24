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
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


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
public class CollaborazioneScritturaLetturasuFiletTramiteLocks {

	public static void main(String[] args) {
		Buffer io = new Buffer();
		LockManageIO manageIO = new LockManageIO(io);
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
class LockManageIO implements Runnable {

	private Buffer io;
	private int i;
	
	
	public LockManageIO(Buffer io) {
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
			io.LockwriteFile();
			io.LockreadFile();
		}
	}
}

class Buffer {

	private static int iD_time = 1;
	private volatile boolean flagIO = false;
	private static File name = new File("prova.txt");
	private Lock object_lock; // lock 
	private Condition write_condition; // condizioni per il lock 
	private Condition read_condition; // condizioni per il lock
	
	
	public Buffer(){
		/**
		 * La classe ReentrantLock fornisce un costruttore in overloading che accetta come argomento un valore
		 * booleano che indica se si desidera o meno avere una politica di ottenimento del lock definita fairness,
		 * con cui si garantisce (se l’argomento è true ) che il prossimo thread che otterrà il lock sarà quello da più tempo in attesa.
		 * */
		this.object_lock = new ReentrantLock(true);//creo un oggetto per il lock
		
		//Creo gli oggetti condizione
		this.write_condition = this.object_lock.newCondition();
		this.read_condition = this.object_lock.newCondition();
	}
	
	
	//non c'è più bisogno della parola chiave synch 
	//ci pensera l'oggetto reetrant
	
	public /*synchronized*/ void LockwriteFile() {
		object_lock.lock(); // acquisisco il lock sull'oggetto
		if (flagIO) {
			try {
				//super.wait();
				write_condition.await(); //aspetto! svolge la stessa funzione di wait()
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		try {
			System.out.println("Writing file " + iD_time + "° time @" + LocalDateTime.now());
			FileOutputStream prova = new FileOutputStream(name);
			PrintStream scrivi = new PrintStream(prova);

			for (int i = 0; i < 10; i++) {
				scrivi.print("TestScientist-" + (iD_time) + (i) + " ");
			}

			flagIO = true;
			//super.notifyAll();
			write_condition.signalAll();//notifico
		} catch (IOException e) {
			System.out.println("Errore: " + e);
			System.exit(1);
		} finally{
			object_lock.unlock(); /* rilascia il lock */ 
		}
		
	}

	//non c'è più bisogno della parola chiave synch 
	//ci pensera l'oggetto reetrant
	
	public /*synchronized*/ void LockreadFile() {
		object_lock.lock(); // acquisisco il lock sull'oggetto
		if (!flagIO) {
			try {
//				super.wait();
				read_condition.await();//aspetto! svolge la stessa funzione di wait()
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
//				super.notifyAll();
				read_condition.signalAll(); //notifico
			} catch (IOException ioException) {
				System.out.println("Errore: " + ioException);
			} finally {
				object_lock.unlock();
			}
			
		}
	}

}