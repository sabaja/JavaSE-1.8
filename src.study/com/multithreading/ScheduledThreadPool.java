package com.multithreading;

import java.time.LocalDateTime;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * http://www.journaldev.com/2340/java-scheduler-scheduledexecutorservice-scheduledthreadpoolexecutor-example
 * @author sabaja
 *
 */
public class ScheduledThreadPool {

	public static void main(String[] args) throws InterruptedException {
		ScheduledExecutorService scheduledThreadPool = Execs.newScheduledThreadPool(5);
		
		
		//schedule to run after sometime
		System.out.println("Current Time = "+ LocalDateTime.now());
		for(int i=0; i<3; i++){
			Thread.sleep(1000);
			WorkerThread worker = new WorkerThread("do heavy processing");
			scheduledThreadPool.schedule(worker, 5, TimeUnit.SECONDS);
		}
		
		//add some delay to let some threads spawn by scheduler
		Thread.sleep(10000);
		
		scheduledThreadPool.shutdown();
		while(!scheduledThreadPool.isTerminated()){
			//wait for all tasks to finish
		}
		System.out.println("Finished all threads");
	}

}

class WorkerThread implements Runnable {

	private String command;

	public WorkerThread(String s) {
		this.command = s;
	}

	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName() + " Start. Time = " + LocalDateTime.now());
		processCommand();
		System.out.println(Thread.currentThread().getName() + " End. Time = " + LocalDateTime.now());
	}

	private void processCommand() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String toString() {
		return this.command;
	}
}

class Execs{
	
	public static ScheduledExecutorService 	newScheduledThreadPool(int sizePool){
		
		return new ScheduledThreadPoolExecutor(sizePool);
	}
}

