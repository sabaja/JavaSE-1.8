package com.multithreading;

class WareHouse{
	private int numberOfProducts;
	private int idNumber;
	public synchronized void put(int idNumber) {
		this.idNumber = idNumber;
		numberOfProducts++;
		printSituation("Produced " + idNumber);
	}

	public synchronized int get() {
		numberOfProducts--;
		printSituation("Consumed " + idNumber);

		return idNumber;
	}

	private synchronized void printSituation(String msg) {
		System.out.println(msg +"\n" + numberOfProducts + " Product in Warehouse");
	}
}

class Producer implements Runnable {
	private WareHouse wareHouse;
	public Producer(WareHouse wareHouse) {
		this.wareHouse = wareHouse;
		new Thread(this, "Producer").start();
	}

	public void run() {
		for (int i = 1; i <= 10; i++) {
			wareHouse.put(i);
		}
	}
}

class Consumer implements Runnable {
	private WareHouse whareHouse;
	public Consumer(WareHouse wareHouse) {
		this.whareHouse = wareHouse;
		new Thread(this, "Consumer").start();
	}

	public void run() {
		for (int i = 0; i < 10;) {
			i = whareHouse.get();
		}
	}
}

public class IdealEconomy_withoutFlag_FILO {
	public static void main(String args[]) {
		WareHouse wareHouse = new WareHouse();
		new Producer(wareHouse);
		new Consumer(wareHouse);
	}
}