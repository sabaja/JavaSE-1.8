package com.multithreading;

import java.time.LocalDateTime;

import java.util.Timer;
import java.util.TimerTask;


/**
 * Per schedulare si usa Timer e TimerTask ma da jdk 5
 * è meglio usare ScheduledThreadPoolExecutor
 * Java 5.0 introduced the java.util.concurrent package 
 * and one of the concurrency utilities there in is the 
 * ScheduledThreadPoolExecutor which is a thread pool for
 * repeatedly executing tasks at a given rate or delay.
 * It is effectively a more versatile replacement for the Timer/TimerTask
 * combination, as it allows multiple service threads,
 * accepts various time units, and doesn't require subclassing
 *  TimerTask (just implement Runnable). 
 *  Configuring ScheduledThreadPoolExecutor
 *  with one thread makes it equivalent to Timer.
 *  
 *  Qui un esempio di ScheduledThreadPoolExecutor:
 *  http://www.journaldev.com/2340/java-scheduledthreadpoolexecutor-example-to-schedule-tasks-after-delay-and-execute-periodically
 */


public class TimerTaskExample extends TimerTask {

	@Override
	public void run() {
		System.out.println("Start time:" + LocalDateTime.now());
		doSomeWork();
		System.out.println("End time:" + LocalDateTime.now());
	}

	// simulate a time consuming task
	private void doSomeWork() {
		try {

			Thread.sleep(10000);

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void main(String args[]) {

		TimerTask timerTask = new TimerTaskExample();
		// running timer task as daemon thread
		Timer timer = new Timer(true);
		timer.scheduleAtFixedRate(timerTask, 0, 10 * 1000);
		System.out.println("TimerTask begins! :" + LocalDateTime.now());
		// cancel after sometime
		try {
			Thread.sleep(20000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		timer.cancel();
		System.out.println("TimerTask cancelled! :" + LocalDateTime.now());
		try {
			Thread.sleep(30000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}