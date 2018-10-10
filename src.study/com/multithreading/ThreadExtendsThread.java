package com.multithreading;

import java.time.LocalDateTime;
import java.time.ZoneId;

class ExtendsThread extends Thread {
	@Override
	public void run() {
		int i = 0;
		try {
			while (true) {
				System.out.println(LocalDateTime.now() + " " + LocalDateTime.now(ZoneId.of("America/Barbados")));
				Thread.sleep(2000);
				++i;
				if (i >= 4)
					break;
			}
		} 
		catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class ThreadExtendsThread {
	public static void main(String[] args) {
		ExtendsThread thread = new ExtendsThread();
		thread.setName("Principal");
		thread.setPriority(Thread.MAX_PRIORITY); //massima priorità 10
		thread.start();
	}
}