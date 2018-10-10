package com.multithreading;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierExample {

	public static void main(String[] args) {
		final CyclicBarrier cb = new CyclicBarrier(3, new Runnable() {
			
			@Override
			public void run() {
				System.out.println("Everybody is present, let's party!");
			}
		});
		Party party = new Party(cb); 
		new Thread(party, "Juliet").start();
		new Thread(party, "Ron").start();
		new Thread(party, "Margaret").start();
	}

}

class Party implements Runnable{
	private CyclicBarrier partyPlace;
	
	public Party(CyclicBarrier partyPlace){
		this.partyPlace = partyPlace;
	}

	@Override
	public void run() {
		try{
		System.out.println(Thread.currentThread().getName() + " is arrived "
				+ "at the party " + partyPlace.getNumberWaiting());
		partyPlace.await();
		}
		catch(InterruptedException |BrokenBarrierException e){
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() + " is celebrating at the party");
	}
}
