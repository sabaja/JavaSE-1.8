package com.collections;

import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 
 * 
 * http://crunchify.com/hashmap-vs-concurrenthashmap-vs-synchronizedmap-
 * how-a-hashmap-can-be-synchronized-in-java/ 
 * 
 * ConcurrentHashMap:
 *
 * You should use ConcurrentHashMap when you need very high concurrency in your
 * project. It is thread safe without synchronizing the whole map. 
 * Reads can happen very fast while write is done with a lock. 
 * There is no locking at the object level. The locking is at a much finer
 * granularity at a hashmap bucket level. 
 * ConcurrentHashMap doesn’t throw a ConcurrentModificationException if
 * one thread tries to modify it while another is iterating over it.
 * ConcurrentHashMap uses multitude of locks. 
 * 
 * SynchronizedHashMap:
 * 
 * Synchronization at Object level. Every read/write operation needs to acquire
 * lock. Locking the entire collection is a performance overhead. This
 * essentially gives access to only one thread to the entire map & blocks all
 * the other threads. It may cause contention. SynchronizedHashMap returns
 * Iterator, which fails-fast on concurrent modification. Now let’s take a look
 * at code
 * 
 * Create class ConcurrentHashMapVsSynchronizedHashMap.java 
 * Create object for each HashTable, SynchronizedMap and CrunchifyConcurrentHashMap Add
 * and retrieve 500k entries from Map Measure start and end time and display
 * time in milliseconds We will use ExecutorService to run 5 threads in parallel
 * 
 */

public class ConcurrentHashMapVsSynchronizedMap {
	public final static int THREAD_POOL_SIZE = 5;

	public static Map<String, Integer> hashTableObject = null;
	public static Map<String, Integer> synchronizedMapObject = null;
	public static Map<String, Integer> concurrentHashMapObject = null;

	public static void main(String[] args) throws InterruptedException {

		// TestScientist with Hashtable Object
		hashTableObject = new Hashtable<String, Integer>();
		performTest(hashTableObject);

		// TestScientist with synchronizedMap Object
		synchronizedMapObject = Collections.synchronizedMap(new HashMap<String, Integer>());
		performTest(synchronizedMapObject);

		// TestScientist with ConcurrentHashMap Object
		concurrentHashMapObject = new ConcurrentHashMap<String, Integer>();
		performTest(concurrentHashMapObject);

	}

	public static void performTest(final Map<String, Integer> threadObjs) throws InterruptedException {

		System.out.println("TestScientist started for: " + threadObjs.getClass());
		long averageTime = 0;
		for (int i = 0; i < 5; i++) {

			long startTime = System.nanoTime();
			ExecutorService executorService = Executors.newFixedThreadPool(THREAD_POOL_SIZE);

			for (int j = 0; j < THREAD_POOL_SIZE; j++) {
				executorService.execute(new Runnable() {
					@SuppressWarnings("unused")
					@Override
					public void run() {

						for (int i = 0; i < 500_000; i++) {
							Integer randomNumber = (int) Math.ceil(Math.random() * 550000);

							// Retrieve value. We are not using it anywhere
							Integer value = threadObjs.get(String.valueOf(randomNumber));

							// Put value
							threadObjs.put(String.valueOf(randomNumber), randomNumber);
						}
					}
				});
			}

			// Make sure executor stops
			executorService.shutdown();

			// Blocks until all tasks have completed execution after a shutdown
			// request
			executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);

			long entTime = System.nanoTime();
			long totalTime = (entTime - startTime) / 1000000L;
			averageTime += totalTime;
			System.out.println("2500K entried added/retrieved in " + totalTime + " ms");
		}
		System.out.println("For " + threadObjs.getClass() + " the average time is " + averageTime / 5 + " ms\n");
	}
}
