package com.collections;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
The LinkedBlockingQueue class implements the BlockingQueue interface. Read the BlockingQueue text for more information about the interface.

The LinkedBlockingQueue keeps the elements internally in a linked structure (linked nodes). This linked structure can optionally have an upper bound if desired. If no upper bound is specified, Integer.MAX_VALUE is used as the upper bound.

The LinkedBlockingQueue stores the elements internally in FIFO (First In, First Out) order. The head of the queue is the element which has been in queue the longest time, and the tail of the queue is the element which has been in the queue the shortest time.

Here is how to instantiate and use a LinkedBlockingQueue:
*/
public class LinkedBlockingQueueExample {
	public static void main(String []args){
		
		BlockingQueue<String> unbounded = new LinkedBlockingQueue<>();
		BlockingQueue<String> bounded   = new LinkedBlockingQueue<>(1024);

		try {
			bounded.put("Value");
			String value = bounded.take();
			System.out.println(value);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}
}
