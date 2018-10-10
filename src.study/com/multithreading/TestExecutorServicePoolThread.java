package com.multithreading;

import java.util.concurrent.*;

public class TestExecutorServicePoolThread {
	public static void main(String args[]) throws Exception {
		ExecutorService service = Executors.newScheduledThreadPool(5);
		for (int i = 0; i < 1000000; i++) {
			Future<Integer> future = service.submit(new Processo(i));
			System.out.println(" Valore : " + future.get());
		}
		service.shutdownNow();
	}
}

class Processo implements Callable<Integer> {
	private int id;

	public Processo(int id) {
		this.id = id;
	}

	@Override
	public synchronized Integer call() {
		// try {
		// Thread.sleep(500);
		// } catch (InterruptedException e) {
		// e.printStackTrace();
		// }
		System.out.print("ID : " + this.id + ", thread :  " + Thread.currentThread().getName());
		return this.id;
	}
}