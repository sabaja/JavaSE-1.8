package com.multithreading;

public class FirstThreadExamples {

	public static void main(String[] args) {
		Thread t = Thread.currentThread();
		t.setName("thread exe");
		t.setPriority(1);
		int time = 0;
		try {
			while (time <= 2) {
				System.out.println("time " + t.toString() + " " + (time + 1) );
				for (int i = 5; i >= 0; i--) {
					System.out.println(i);
					t.join(1000);//blocca il traad principale fin
								 //quando t non ha eseguito il suo compito 
								//a differenza di sleep non è statico
				}
				++time;
			}
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
	}

}
