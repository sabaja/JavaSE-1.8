package com.collections;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Iterator;
import java.util.Set;

/**
 * 
 * La HashMap non è sincronizzata ed è performante 
 * Se si vuole la concorrenza si deve usare la HastTable
 *
 */

public class HashMapExample {

	public static void main(String args[]) {

		/* This is how to declare HashMap */
		HashMap<Integer, String> hmap = new HashMap<Integer, String>();

		/* Adding elements to HashMap */
		hmap.put(12, "Chaitanya");
		hmap.put(2, "Rahul");
		hmap.put(7, "Singh");
		hmap.put(49, "Ajeet");
		hmap.put(3, "Anuj");

		/* Display content using Iterator */
		Set<Entry<Integer, String>> set = hmap.entrySet();
		// Entry è un elemento della mappa
		Iterator<Entry<Integer, String>> iterator = set.iterator();
		while (iterator.hasNext()) {
			// Map.Entry Interfaccia innestata che rappresenta l'elemento della
			// mappa
			Map.Entry<Integer, String> mentry = iterator.next();
			System.out.print("key is: " + mentry.getKey() + " & Value is: ");
			System.out.println(mentry.getValue());
		}

		/* Get values based on key */
		String var = hmap.get(2);
		System.out.println("Value at index 2 is: " + var);

		/* Remove values based on key */
		hmap.remove(3);
		System.out.println("Map key and values after removal:");
		Set<Entry<Integer, String>> set2 = hmap.entrySet();
		Iterator<Entry<Integer, String>> iterator2 = set2.iterator();
		while (iterator2.hasNext()) {
			Map.Entry<Integer, String> mentry2 = (Map.Entry<Integer, String>) iterator2.next();
			System.out.print("Key is: " + mentry2.getKey() + " & Value is: ");
			System.out.println(mentry2.getValue());
		}
	}
}