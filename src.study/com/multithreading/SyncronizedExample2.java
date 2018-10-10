package com.multithreading;


import java.time.LocalTime;

class CallMe{
	/* inserisco il modificatore qui se voglio che call sia sempre syncronizzato*/
	/*synchronized*/ public void call(String msg){
		System.out.print("[" + msg);
		try{
			Thread.sleep(1000);//un secondo di pausa
		}
		catch(InterruptedException e){
			e.printStackTrace();
		}
		System.out.println("]");
	}
}

class Caller implements Runnable{
	private CallMe target;
	private String str;
	private final int sec;
	public Caller(CallMe t, String s){
		target = t;
		str = s;
		LocalTime time = LocalTime.now();
		sec = time.getSecond(); 
		new Thread(this, "Caller").start();
	}
	
	@Override
	public void run() {
		/*con synchronized(oggettoDaSynchronizzare) il chiamante decide 
		 * alleatoriamente di synchronizzare il metodo call dell oggetto target
		 * posso decidere per i secondi pari di synchronizzare per i dispari no
		 * es: */
		
		if (sec % 2 == 0) {
			synchronized (target) {
				target.call(str + "  " + LocalTime.now() + " ");
			}
		}
		else
			target.call(str + "  " + LocalTime.now() + " ");
	}		
	
}

public class SyncronizedExample2 {
	public static void main(String []args){
		CallMe target = new CallMe();
		new Caller(target, "First");
		new Caller(target, "Second");
		new Caller(target, "third");
		new Caller(target, "and so on");
	}
}