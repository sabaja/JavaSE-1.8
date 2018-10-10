package com.collections;

import java.time.Instant;
import java.time.LocalTime;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
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

/**
 * http://tutorials.jenkov.com/java-util-concurrent/blockingqueue.html
 * 
 * The SynchronousQueue class implements the BlockingQueue interface. Read the
 * BlockingQueue text for more information about the interface. The
 * SynchronousQueue is a queue that can only contain a single element
 * internally. A thread inseting an element into the queue is blocked until
 * another thread takes that element from the queue. Likewise, if a thread tries
 * to take an element and no element is currently present, that thread is
 * blocked until a thread insert an element into the queue.
 * 
 * Calling this class a queue is a bit of an overstatement. It's more of a
 * rendesvouz point.
 */
public class SynchronizedBlockingQueueExample_ThreadSafe_FIFOExample_as_IdealEconomy {
	public static void main(String[] args) throws Exception {

		BlockingQueue<String> queue = new SynchronousQueue<>(true);

		Producer_ producer = new Producer_(queue);
		Consumer_ consumer = new Consumer_(queue);

		new Thread(producer).start();
		new Thread(consumer).start();

		Thread.sleep(4000);
		System.out.println("End");
	}
}

enum NUM_ {
	N(3);
	private int n;

	private NUM_(int n) {
		this.n = n;
	}

	public int getN() {
		return n;
	}

	public void setN(int n) {
		this.n = n;
	}

}

class Producer_ implements Runnable {

	protected BlockingQueue<String> queue = null;

	public Producer_(BlockingQueue<String> queue) {
		this.queue = queue;
	}

	public void run() {
		try {
			for (int i = 0; i < NUM_.N.getN(); i++) {
				queue.put(new Integer(i).toString());
				System.out.println("prodotto: " + i + " " + LocalTime.now() );
				Thread.sleep(1000);
			}

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class Consumer_ implements Runnable {

	protected BlockingQueue<String> queue = null;

	public Consumer_(BlockingQueue<String> queue) {
		this.queue = queue;
	}

	public void run() {
		try {
			for (int i = 0; i < NUM_.N.getN(); i++) {
				System.out.println("Consumato: " + queue.take() + " " + LocalTime.now() );
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}