package com.collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

/**
 * E' l'intefaccia madre di Queue, Deque, Set (SortedSet), List
 * 
 * rappresenta il protipo delle collezioni a cui appartengoono
 * metodi per ordinare, trasformare in varie collezioni,
 * togliere e inserire e paragonare.
 */
public class CollectionExample {

	public static void main(String[] args) {
		
		//HashSet
		Collection<String> progBands  = new HashSet<>();
		progBands.add("Yes");
		progBands.add("PFM");
		progBands.add("Giganti");
		progBands.add("Gaggi");
		Iterator<String> iterator = progBands.iterator();
		System.out.println("Hashset:");
		while(iterator.hasNext()){
			System.out.println(iterator.next());
		}

		//<T> T[] toArray(T[] arr) restituisce un array di tipo T a partire da una
		//collezione
		System.out.println("\nArray:");
		String [] progBandArr = progBands.toArray(new String[0]);
		for(String a : progBandArr)
			System.out.println(a);

		//é possibile fare anche l'inverso con asList della classe Arrays:
		System.out.println("\nList:");
		List<String> ls = Arrays.asList(progBandArr);
		System.out.println(ls);
		
		//boolean ContainsAll(Collection <?> c) verifica se gli elementi di c
		//sono presenti sulla collezione che richiama il metodo
		
		Collection<Integer> collection1 = new ArrayList<>();
		for(int i = 0; i < 5 ; i++)
			collection1.add(i);
		
		Collection<Integer> collection2 = new ArrayList<>();
		collection2.add(2);
		collection2.add(5);//elemento non presente nella coll1
		collection2.add(1);
		
		System.out.println("\n" + collection1.containsAll(collection2));
		
		//boolean retainAll(Collection <?> c) mantiene solo gli elementi di c
		//se sono presenti sulla collezione che richiama il metodo
		System.out.println(collection1.retainAll(collection2));
		System.out.println("\nRetain example: " + collection1);
		
		
	}

}
