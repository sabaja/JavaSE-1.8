package com.collections;

import java.util.LinkedList;
import java.util.List;

/**
 * 
 * https://stackoverflow.com/questions/14851367/java-linkedlist-differences-between-retrieve-operations
 * 
 * Returning null + removing operations: poll(), pollFirst().
 * 
 * Returning null + not removing operations: peek(), peekFirst().
 * 
 * Throwing exception + removing operations: pop(), remove(), removeFirst().
 * 
 * Throwing exception + not removing operations: element(), getFirst().
 * 
 * @author sabaja
 *
 */
public class LinkedListExample {
	public static void main(String[] args) {

		// create a LinkedList
		List<String> list = new LinkedList<>();

		// add some elements
		list.add("Hello");
		list.add("2");
		list.add("Chocolate");
		list.add("10");

		// print the list
		System.out.println("LinkedList: " + list);

		// pop the list
		System.out.println("Pop element in the list: " + ((LinkedList<String>) list).pop());

		// print the list
		System.out.println("LinkedList: " + list);

		System.out.println("Poll element in the list: " + ((LinkedList<String>) list).poll());

		System.out.println("LinkedList: " + list);

		System.out.println("add NULL");

		((LinkedList<String>) list).addFirst("");

		System.out.println("LinkedList: " + list);

		// Returning null + removing operations: poll()
		System.out.println("Poll NULL element in the list: " + ((LinkedList<String>) list).poll());

		System.out.println("LinkedList: " + list);

		System.out.println("add NULL");

		((LinkedList<String>) list).addFirst("");

		// Throwing exception + removing operations: pop()
		System.out.println("Pop element in the list exception: " + ((LinkedList<String>) list).pop());

		System.out.println("LinkedList: " + list);
	}
}