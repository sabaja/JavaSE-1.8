package com.multithreading;

import java.util.Random;

public class ThreadClass_and_RunnableInterface {

	public static void main(String[] args) {
		DoJobRun job = new DoJobRun(); 
		// creo il primo thread 
		Thread nt = new Thread(job, "***THREAD 1***");
		nt.start();
		DoJobTh thread_2 = new DoJobTh(); // creo il secondo thread 
		thread_2.setName("***THREAD 2***"); 
		thread_2.start(); 
		System.out.println("Thread principale (" + Thread.currentThread().getName() + ") finito");
	}

}

class DoJobRun implements Runnable 
// run tramite l'interfaccia Runnable 
{ 
	private String msg = " sarà occupato per i prossimi ";

	public void run() 
	{ 
		for (int i = 0; i < 5; i++) {
			try { 
				int ms = 6000; // 6 secondi
				System.out.println(Thread.currentThread().getName() + msg + ms + " millisecondi");
				Thread.sleep(ms); // metti in timed waiting il thread // per n millisecondi }  } } }
			}
			catch (InterruptedException ex) {}
		}
	}
}
		
class DoJobTh extends Thread // run tramite la classe Thread 
{ 
	private String msg = " sarà occupato per i prossimi ";
	
	@Override
	public void run() {
		for (int i = 0; i < 5; i++) 
		{ 
			try { 
				Random r = new Random(); 
				int ms = r.nextInt(3000); // random tra 0 e 3 secondi 
				System.out.println(getName() + msg + ms + " millisecondi"); 
				Thread.sleep(ms); // metti in timed waiting il thread // per n millisecondi 
			} 
			catch (InterruptedException ex) {} 
		}
	}
}