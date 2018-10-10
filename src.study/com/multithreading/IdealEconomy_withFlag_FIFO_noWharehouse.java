package com.multithreading;

class WhareHouse{
	private int id = 0;
	private int numOfProducts = 0;
	private volatile boolean empty = true;
	
	synchronized void put(int id){
		if(!empty){
			try {
				wait();
			} 
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		this.id = id;
		numOfProducts++;
		printSituation("produced");
		empty = false;
		notify();
	}
	
	synchronized int get(){
		if(empty){
			try {
				wait();
			} 
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		numOfProducts--;
		printSituation("consumed");
		empty = true;
		notify();
		return id;
	}
	
	synchronized void printSituation(String str){
		System.out.println(id + " product " + str + 
				"\n" + numOfProducts + " in wharehouse");
	}
}

class Producer1 implements Runnable {
	private WhareHouse whareHouse;
	
	public Producer1(WhareHouse whareHouse) {
		this.whareHouse = whareHouse;
		new Thread(this).start();
	}
	
	@Override
	public void run() {
		for(int i = 1 ; i <= 10 ; i++){
			whareHouse.put(i);
		}
		
	}
}

class Consumer1 implements Runnable{
	private WhareHouse whareHouse;
	
	public Consumer1(WhareHouse whareHouse){
		this.whareHouse = whareHouse;
		new Thread(this).start();
	}

	@Override
	public void run() {
		for (int i = 1; i <= 10; ) {
			i = whareHouse.get();	
		}
	}
}

public class IdealEconomy_withFlag_FIFO_noWharehouse {
	public static void main(String []args){
		WhareHouse whareHouse = new WhareHouse();
		new Consumer1(whareHouse);
		new Producer1(whareHouse);

	}

}
