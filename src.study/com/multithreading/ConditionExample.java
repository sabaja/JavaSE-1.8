package com.multithreading;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 
 * @author sabaja
 *         https://examples.javacodegeeks.com/core-java/util/concurrent/locks-concurrent/condition/java-util-concurrent-locks-condition-example/
 * 
 * 
 *         Conditions in Java As we have already described, Conditions are being
 *         used in order for a thread to be notified, when a condition is true.
 *         One fundamental example that demonstrates the usage of Conditions is
 *         the producer-consumer example. According to this model, a thread
 *         produces a number of items and places them to a shared queue, while a
 *         thread consumes these objects, by removing them from the shared
 *         queue. Important: Notice that the model supports the presence of
 *         multiple producers and consumers, but in this example, we will
 *         demonstrate the simple case where we have one producer and one
 *         consumer. In addition, it is important to mention that the shared
 *         queue is accessed by multiple threads and thus, it must be properly
 *         synchronized. Our implementation of the shared queue is shown below:
 */
public class ConditionExample {
	public static void main(String[] args) throws InterruptedException {
		SharedFiFoQueue sharedQueue = new SharedFiFoQueue(10);

		// Create a producer and a consumer.
		Thread producer = new Prod(sharedQueue);
		Thread consumer = new Cons(sharedQueue);

		// Start both threads.
		producer.start();
		consumer.start();

		// Wait for both threads to terminate.
		producer.join();
		consumer.join();
	}
}

/**
 * 
 * @author sabaja The SharedQueue class contains a private array of elements and
 *         a maximum capacity. It supports two methods, add and remove, which
 *         are used to add and remove an element to the queue respectively. In
 *         both methods, the lock is first acquired. Then, if the queue is not
 *         full, an element can be inserted, or correspondingly, if the queue is
 *         not empty, an element can be removed. Finally, before the lock is
 *         released, both methods notify any waiting thread.
 */
class SharedFiFoQueue {

	private Object[] elems = null;
	private int current = 0;
	private int placeIndex = 0;
	private int removeIndex = 0;

	private final Lock lock = new ReentrantLock();
	private final Condition isEmpty = lock.newCondition();
	private final Condition isFull = lock.newCondition();

	public SharedFiFoQueue(int capacity) {
		this.elems = new Object[capacity];
	}

	public void add(Object elem) throws InterruptedException {
		lock.lock();
		while (current >= elems.length)
			isFull.await();

		elems[placeIndex] = elem;

		// We need the modulo, in order to avoid going out of bounds.
		placeIndex = (placeIndex + 1) % elems.length;

		++current;

		// Notify the consumer that there is data available.
		isEmpty.signal();

		lock.unlock();
	}

	public Object remove() throws InterruptedException {
		Object elem = null;

		lock.lock();
		while (current <= 0)
			isEmpty.await();

		elem = elems[removeIndex];

		// We need the modulo, in order to avoid going out of bounds.
		removeIndex = (removeIndex + 1) % elems.length;

		--current;

		// Notify the producer that there is space available.
		isFull.signal();

		lock.unlock();

		return elem;
	}
}

/**
 * 
 * @author sabaja The Consumer class constantly reads elements from the shared
 *         queue, until a special null object is received. The Consumer class
 *         also counts the number of distinct words, as received by the
 *         producer.
 */
class Cons extends Thread {
	private final Set<Object> seenObjects = new HashSet();
	private int total = 0;
	private final SharedFiFoQueue queue;

	public Cons(SharedFiFoQueue queue) {
		this.queue = queue;
	}

	@Override
	public void run() {
		try {
			do {
				Object obj = queue.remove();
				if (obj == null)
					break;

				if (!seenObjects.contains(obj)) {
					++total;
					seenObjects.add(obj);
				}

				System.out.println("[Consumer] Read the element: " + obj.toString());

			} while (true);
		} catch (InterruptedException ex) {
			System.err.println("An InterruptedException was caught: " + ex.getMessage());
			ex.printStackTrace();
		}

		System.out.println("\n[Consumer] " + total + " distinct words have been read...");
	}
}

/**
 * 
 * @author sabaja The Producer class reads the contents of the specified file,
 *         line-by-line. Each line is split into separated words and each word
 *         is placed into the shared queue. Once the file has been completely
 *         read, a special null object is placed into the queue, in order to
 *         notify the consumer that no more elements will be placed into the
 *         queue.
 */
class Prod extends Thread {
	private final static String FILENAME = "input.txt";
	private final SharedFiFoQueue queue;

	public Prod(SharedFiFoQueue queue) {
		this.queue = queue;
	}

	@Override
	public void run() {
		BufferedReader rd = null;

		try {
			rd = new BufferedReader(new FileReader(FILENAME));

			String inputLine = null;
			while ((inputLine = rd.readLine()) != null) {
				String[] inputWords = inputLine.split(" ");

				for (String inputWord : inputWords)
					queue.add(inputWord);
			}

			// Terminate the execution.
			queue.add(null);
		} catch (InterruptedException ex) {
			System.err.println("An InterruptedException was caught: " + ex.getMessage());
			ex.printStackTrace();
		} catch (IOException ex) {
			System.err.println("An IOException was caught: " + ex.getMessage());
			ex.printStackTrace();
		} finally {
			try {
				if (rd != null)
					rd.close();
			} catch (IOException ex) {
				System.err.println("An IOException was caught: " + ex.getMessage());
				ex.printStackTrace();
			}
		}
	}
}