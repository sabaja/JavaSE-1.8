package com.multithreading;

import java.util.concurrent.locks.*;
import java.time.LocalTime;
import java.util.concurrent.*;


/**
 * 
 * @author sabaja
 * Lock può essere considerato un'alternativa al metodo synchronized
 */
public class LockExample implements Runnable {
    private int numeroIntero;
    private Lock lock;//interfaccia che gestisce i lock come wait(), notify() notifyAll()

    /* A reentrant mutual exclusion Lock with the same basic behavior
    *  and semantics as the implicit monitor lock accessed using synchronized
    *  methods and statements, but with extended capabilities.
    *   class X {
    *      private final ReentrantLock lock = new ReentrantLock();
    *         // ...
    *            public void m() {
    *                 lock.lock();  // block until condition holds
    *                 try {
    *                             // ... method body
    *                 } finally {
    *                 		lock.unlock();
    *                 }
    *            }
    *   }
    * */
    
    public LockExample(int numeroIntero) {
        this.numeroIntero = numeroIntero;
        this.lock = new ReentrantLock();

    }

    @Override
    public void run() {
        try {
            if(lock.tryLock(5, TimeUnit.SECONDS)) {
                numeroIntero++;
                System.out.println(LocalTime.now() + " In lock "+numeroIntero);
                Thread.sleep(1000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        System.out.println(LocalTime.now() + " Out lock " + numeroIntero);
    }
    
    public static void main(String []args) throws InstantiationException, IllegalAccessException{
    	for(int i = 0 ; i < 5; i++){
    		LockExample l = new LockExample(i);
    	  			new Thread(l).start();
			}
      	}
    }