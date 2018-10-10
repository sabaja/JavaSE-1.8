package com.multithreading;

import java.time.LocalTime;

class CallMe1 {

	private static int count1 = 0;
	private static int count2 = 0; 

	/*
	 * inserisco il modificatore qui se voglio che call sia sempre syncronizzato
	 */
	 synchronized public void call(String msg) {
		System.out.print(++count1 + "[" + msg);
		try {
			Thread.sleep(1000);// un secondo di pausa
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("]" + ++count2);
	}
}

class Caller1 implements Runnable {
	private CallMe1 target;
	private String str;

	public Caller1(CallMe1 t, String s) {
		target = t;
		str = s;
		new Thread(this, "Caller").start();
	}

	@Override
	public void run() {
		/*
		 * con synchronized(oggettoDaSynchronizzare) il chiamante decide
		 * alleatoriamente di synchronizzare il metodo call dell oggetto target
		 * posso decidere per i secondi pari di synchronizzare per i dispari no
		 * es:
		 */

//		synchronized (target) {
			target.call(str);
//		}
	}

}

class SyncronizedExample1 {
	public static void main(String[] args) {
		CallMe1 target = new CallMe1();
		new Caller1(target, "First");
		new Caller1(target, "Second");
		new Caller1(target, "third");
		new Caller1(target, "and so on");
	}
}