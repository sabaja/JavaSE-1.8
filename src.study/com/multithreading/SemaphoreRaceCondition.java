package com.multithreading;


import java.util.concurrent.Semaphore;
 
/**
 * @author .com
 * 
 */
 
public class SemaphoreRaceCondition {
    private static final int MAX_CONCURRENT_THREADS = 2;
    private final Semaphore AdminLOCK = new Semaphore(MAX_CONCURRENT_THREADS, true);
    
    public void StartTest() {
        for (int i = 0; i < 10; i++) {
            PersonInRace personInRace = new PersonInRace();
            personInRace.start();
        }
    }
    
    public class PersonInRace extends Thread {
        @Override
        public void run() {
            try {
                
                // Acquire Lock
                AdminLOCK.acquire();
            } catch (InterruptedException e) {
                System.out.println("received InterruptedException");
                return;
            }
            System.out.println("Thread " + this.getId() + " start using 's car - Acquire()");
            try {
                sleep(1000);
            } catch (Exception e) {
                
            } finally {
                
                // Release Lock
                AdminLOCK.release();
            }
            System.out.println("Thread " + this.getId() + " stops using 's car -  Release()\n");
        }
    }
    
    public static void main(String[] args) {
        SemaphoreRaceCondition test = new SemaphoreRaceCondition();
        test.StartTest();
        
    }
}