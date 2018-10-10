package com.collections;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;


/**
 * 
 * A BlockingDeque has 4 different sets of methods for inserting, 
 * removing and examining the elements in the deque. 
 * Each set of methods behaves differently in case the requested 
 * operation cannot be carried out immediately. 
 * Here is a table of the methods:

 	    Throws Exception	  Special Value	  Blocks	    Times Out
Insert	addFirst(o)	          offerFirst(o)	  putFirst(o)	offerFirst(o, timeout, timeunit)
Remove	removeFirst(o)	      pollFirst(o)	  takeFirst(o)	pollFirst(timeout, timeunit)
Examine	getFirst(o)	          peekFirst(o)	 	 

 	    Throws Exception	  Special Value	  Blocks	    Times Out
Insert	addLast(o)	          offerLast(o)	  putLast(o)	offerLast(o, timeout, timeunit)
Remove	removeLast(o)	      pollLast(o)	  takeLast(o)	pollLast(timeout, timeunit)
Examine	getLast(o)	          peekLast(o)	 	 
 *
 */

public class BlockingDeque_LinkedBlockingDeque {

	public static void main(String[] args) {
		BlockingDeque<String> deque = new LinkedBlockingDeque<String>();

		deque.addFirst("1");
		deque.addLast("2");

		
		try {
			String one = deque.takeFirst();
			String two = deque.takeLast();
			System.out.println(one + " " + two);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}

	}

}
