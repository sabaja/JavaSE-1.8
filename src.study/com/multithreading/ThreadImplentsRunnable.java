package com.multithreading;

import java.time.LocalTime;

public class ThreadImplentsRunnable implements Runnable{
	private Thread master;
	private Thread son;
	
	public ThreadImplentsRunnable() {
		master = Thread.currentThread();
		master.setName("MasterThread");
		master.setPriority(10);
		son = new Thread(this, "SonThread");//runnable object and name
		son.setPriority(5);
		System.out.println("MasterThread created and started");
		System.out.println("SonThread created");
		son.start();
		int i = 0;
		while (i < 3) {
			try {
				if(0 == i){
					Thread.sleep(3000);
					System.out.println("Master ended " + LocalTime.now());
				}
				else{
					Thread.sleep(5000);
					System.out.println("Master ended " + LocalTime.now());
				}
			} catch (InterruptedException e) {
				System.out.println("Master interruped");
			}
			System.out.println("Master restart " + LocalTime.now());
			++i;
		}
	}
	
	@Override
	public void run() {
		int time = 0;
		try {
			while (time <= 2) {
				System.out.println("time " + son.toString() + " " + (time + 1) );
				for (int i = 5; i >= 0; i--) {
					System.out.println(i + " " +  LocalTime.now());
					Thread.sleep(1000);
				}
				++time;
			}
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
		
		
	}
	public static void main(String[] args) {
		new ThreadImplentsRunnable();
	}
	
	

}

