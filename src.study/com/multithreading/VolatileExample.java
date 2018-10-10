package com.multithreading;


class Clicker implements Runnable{
	private volatile boolean flag = true;//Unica area di memoria indispensabile per interventi 
										 //con + thread
	private Thread t;
	private volatile Long click = 0L;
	
	Clicker(int priority){
		t = new Thread(this);
		t.setPriority(priority);
	}
	
	public Long getClick() {
		return click;
	}

	@Override
	public void run() {
		while(flag){
			++click;
		}
	}
	
	public void stratThread(){
		t.start();
	}
	
	public void stopThread(){
		flag = false;
	}
}

class VolatileExample {

	public static void main(String[] args) {
		Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
		Clicker hi = new Clicker(Thread.NORM_PRIORITY + 2);
		Clicker low = new Clicker(Thread.NORM_PRIORITY - 2);
		
		low.stratThread();
		System.out.println("low started");
		hi.stratThread();
		System.out.println("hi started");
		System.out.println("this " + Thread.currentThread().getName() + " thread is going to sleep"
				+ " for 10 seconds");		
		try{
			Thread.sleep(10000);
		}
		catch(InterruptedException e){
			e.printStackTrace();
		}
		hi.stopThread();
		low.stopThread();
		
		System.out.println("hi clicker: " + hi.getClick() + " low: " + low.getClick());
	}

}


