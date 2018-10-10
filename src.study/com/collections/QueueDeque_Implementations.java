package com.collections;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;

public class QueueDeque_Implementations {

	public static void main(String[] args) {
		Queue_implementations qi = new Queue_implementations();
	}

}
/** Queue: 
 * 	Coda di elementi che vengono prima immagazinati
 * 	poi processati.
 * 	Implementa Collection ma aggiunge metodi che invece di lanciare eccezioni
 * 	ritornano un valore o un boolean 	
 * 
 * 	Throws 	exception	Returns  special value
 *  Insert	add(e)		offer(e) Boolean
 *  Remove	remove()	poll()	 E/null
 *  Examine	element()	peek()	 E/null
 * 
 * Implementazioni:
 * PriorityQueue
 *  An unbounded priority queue based on a priority heap. 
 *  The elements of the priority queue are ordered according to their natural ordering, 
 *  or by a Comparator provided at queue construction time, 
 *  depending on which constructor is used. 
 *  A priority queue does not permit null elements. 
 *  A priority queue relying on natural ordering also does not permit insertion of 
 *  non-comparable objects (doing so may result in ClassCastException).
 */

class Queue_implementations{

	public Queue_implementations() {
		super();
		//4 elementi e ordine inverso
		Queue<Integer> queue = new PriorityQueue<>(4,(x,y)-> (x.compareTo(y)));
		Queue<Integer> que =  new PriorityQueue<>();
		for(int i = 0; i < 11; i++){
			queue.offer(new Random().nextInt(2000));
			que.offer(new Random().nextInt(2000));
		}
		
		//Metodo per ordidare un Priority queue;
		Arrays.sort(queue.toArray());
		Arrays.sort(que.toArray());
		//per stampare 
		System.out.println(queue);
		System.out.println(que);
	}
	
}


/**
*	Deque Codia doppia:
*	This interface extends the Queue interface. 
*	When a deque is used as a queue, FIFO (First-In-First-Out) 
*	behavior results. Elements are added at the end of the deque
*	and removed from the beginning. 
*	The methods inherited from the Queue interface are precisely
*	equivalent to Deque methods as indicated in the following 
*	metodi:
*
*					First Element (Head)					Last Element (Tail)
*			Throws exception		Special value	Throws exception	Special value
*	Insert	addFirst(e)				offerFirst(e)	addLast(e)			offerLast(e)
*	Remove	removeFirst()			pollFirst()		removeLast()		pollLast()
	Examine	getFirst()				peekFirst()		getLast()			peekLast()	
	
	Deques can also be used as LIFO (Last-In-First-Out) stacks.
	This interface should be used in preference to the legacy
	Stack class. When a deque is used as a stack, elements are 
	pushed and popped from the beginning of the deque.
*/

