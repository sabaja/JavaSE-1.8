package com.collections;

import java.time.Instant;
import java.time.LocalTime;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 
 * @author sabaja
 *A BlockingQueue has 4 different sets of methods for inserting, removing and examining the elements in the queue. Each set of methods behaves differently in case the requested operation cannot be carried out immediately. Here is a table of the methods:
 		ThrowsException	SpecialValue	Blocks	Times Out
Insert	add(o)			offer(o)		put(o)	offer(o, timeout, timeunit)
Remove	remove(o)		poll()			take()	poll(timeout, timeunit)
Examine	element()		peek()	 	 
The 4 different sets of behaviour means this:

Throws Exception: 
If the attempted operation is not possible immediately, an exception is thrown.
Special Value: 
If the attempted operation is not possible immediately, a special value is returned (often true / false).
Blocks: 
If the attempted operation is not possible immedidately, the method call blocks until it is.
Times Out: 
If the attempted operation is not possible immedidately, the method call blocks until it is, but waits no longer than the given timeout. Returns a special value telling whether the operation succeeded or not (typically true / false).
 */


/** http://tutorials.jenkov.com/java-util-concurrent/blockingqueue.html
 * 
 * Here is a Java BlockingQueue example. 
 * The example uses the ArrayBlockingQueue implementation of 
 * the BlockingQueue interface.
 * First, the BlockingQueueExample class which starts a Producer 
 * and a Consumer in separate threads. 
 * The Producer inserts strings into a shared BlockingQueue, 
 * and the Consumer takes them out.
 */
public class BlockingQueueExample_ThreadSafe {
	   public static void main(String[] args) throws Exception {

	        BlockingQueue<String> queue = new ArrayBlockingQueue<>(1024);

	        Producer producer = new Producer(queue);
	        Consumer consumer = new Consumer(queue);

	        new Thread(producer).start();
	        new Thread(consumer).start();

	        Thread.sleep(4000);
	        System.out.println("End");
	    }
}

enum NUM{
	N(3), FLAG(true);
	private int n;
	volatile boolean flag;
	
	private NUM(int n){
		this.n	= n;
	}

	private NUM(boolean flag){
		this.flag	= flag;
	}

	public int getN() {
		return n;
	}

	public void setN(int n) {
		this.n = n;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	
	
}

class Producer implements Runnable{

    protected BlockingQueue<String> queue = null;
    private Lock lock = new ReentrantLock();
    
    public Producer(BlockingQueue<String> queue) {
        this.queue = queue;
    }

    public void run() {
        try {
        	  queue.put("1");
              Thread.sleep(1000);
              queue.put("2");
              Thread.sleep(1000);
              queue.put("3");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        }
}

class Consumer implements Runnable{

    protected BlockingQueue<String> queue = null;
    
    public Consumer(BlockingQueue<String> queue) {
        this.queue = queue;
    }

    public void run() {
        try {
            System.out.println(queue.take());
            System.out.println(queue.take());
            System.out.println(queue.take());        	
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
    }
}