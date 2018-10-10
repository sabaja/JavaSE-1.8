package com.multithreading;

class Click implements Runnable {
	private volatile long click = 0L;// non necessario se già dichiarata
										// volatile la variabile runnig (da java
										// 5 la JVM garantisce tutte le
										// variabili d'istanza come volatile se
										// una è già dichiarata volatile)
	private Thread t;
	private volatile boolean running = true;

	public Click(int p) {
		t = new Thread(this);
		t.setPriority(p);
	}

	public long getClick() {
		return click;
	}

	public void run() {
		while (running) {
			click++;
		}
		System.out.println("Fine " + Thread.currentThread());
	}

	public void stopThread() {
		running = false;
	}

	public void startThread() {
		t.start();
	}
}

public class PreemptiveNonPreemtiveExample {
	public static void main(String args[]) {
		Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
		Click hi = new Click(Thread.NORM_PRIORITY + 2);
		Click lo = new Click(Thread.NORM_PRIORITY - 2);
		lo.startThread();
		hi.startThread();
		try {
			Thread.sleep(10000);
		} catch (Exception e) {
		}
		lo.stopThread();
		hi.stopThread();
		System.out.println(lo.getClick() + " vs. " + hi.getClick());
	}
}