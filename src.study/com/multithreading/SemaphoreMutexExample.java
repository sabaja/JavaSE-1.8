package com.multithreading;
 
import java.util.LinkedList;
import java.util.concurrent.Semaphore;
 
/**
 * @author .com
 * https://crunchify.com/what-is-java-semaphore-and-mutex-java-concurrency-multithread-explained-with-example/
 */
 
public class SemaphoreMutexExample {
    static Object Lock = new Object();
    static LinkedList<String> list = new LinkedList<String>();
    
    // Semaphore maintains a set of permits.
    // Each acquire blocks if necessary until a permit is available, and then takes it.
    // Each release adds a permit, potentially releasing a blocking acquirer.
    static Semaphore semaphore = new Semaphore(0);
    static Semaphore mutex = new Semaphore(1);
    
    // I'll producing new Integer every time
    static class Producer extends Thread {
        public void run() {
            
            int counter = 1;
            try {
                while (true) {
                    String threadName = Thread.currentThread().getName() + counter++;
                    
                    mutex.acquire();
                    list.add(threadName);
                    System.out.println("Producer is prdoucing new value: " + threadName);
                    mutex.release();
                    
                    // release lock
                    semaphore.release();
                    Thread.sleep(500);
                }
            } catch (Exception x) {
                x.printStackTrace();
            }
        }
    }
    
    // I'll be consuming Integer every stime
    static class Consumer extends Thread {
        String consumerName;
        
        public Consumer(String name) {
            this.consumerName = name;
        }
        
        public void run() {
            try {
                
                while (true) {
                    
                    // acquire lock. Acquires the given number of permits from this semaphore, blocking until all are
                    // available
                    // process stops here until producer releases the lock
                    semaphore.acquire();
                    
                    // Acquires a permit from this semaphore, blocking until one is available
                    mutex.acquire();
                    String result = "";
                    for (String value : list) {
                        result = value + ",";
                    }
                    System.out.println(consumerName + " consumes value: " + result + " list.size(): "
                            + list.size() + "\n");
                    mutex.release();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    public static void main(String[] args) {
        new Producer().start();
        new Consumer("Me").start();
        new Consumer("Google").start();
        new Consumer("Yahoo").start();
    }
}
