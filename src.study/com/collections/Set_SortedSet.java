package com.collections;


import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.NavigableSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

public class Set_SortedSet {

	public static void main(String[] args) {
		new Set_HashSet_Example();
		new Set_LinkedHshSet();
		new SortedSet_TreeSet();
		new NavigableSetExample();
	}

}

/**
 * Non ammette i duplicati e contiene elementi non ordinati
 * è + veloce di un  sortedSet/treeSet
 */
class Set_HashSet_Example{

	public Set_HashSet_Example() {
		super();
		System.out.println("HashSet:");
		Set<String> set = new HashSet<>();
		set.add("c");
		set.add("a");
		set.add("b");
		set.add("e");
		set.add("e");
		set.add("t");
		set.forEach(System.out::println);
	
	}
	
}

/**
 * Non ammette i duplicati e contiene elementi che rispecchiano l'ordine
 * di inserimento 
 */

class Set_LinkedHshSet{

	public Set_LinkedHshSet() {
		super();
		System.out.println("LinkedHashSet:");
		Set<String> set = new LinkedHashSet<>();
		set.add("c");
		set.add("a");
		set.add("e");
		set.add("c");
		set.add("uu");
		set.forEach(System.out::println);

	}
	
}

/**
E' un'interfaccia collection che non accetta duplicati e 
mette in ordine i suoi elementi richiamando il metodo compareTo 
dell'interfaccia comparable di String
si possono definire anche altri ordinamenti passando un oggetto comparator
o implementado tramite lambda expression l'ordinamento desiderato
è piu lenta della HashSet
*/
class SortedSet_TreeSet{

	public SortedSet_TreeSet() {
		super();
		System.out.println("Treeset-SortedSet:");
		SortedSet<String> set = new TreeSet<>();
		set.add("c");
		set.add("a");
		set.add("b");
		set.add("c");
		set.forEach(System.out::println);
		
		//ordinamento al contrario
		System.out.println("Treeset-ordinamento definito traite lambda:");
		TreeSet<String> ts = new TreeSet<>((x,y)-> -(x.compareTo(y)));
		ts.add("c");
		ts.add("a");
		ts.add("b");
		ts.add("c");
		ts.forEach(System.out::println);
		
		System.out.println("Treeset al contrario con descendingIterator:");
		//Dalla 1.6 i treeSet sono bidirezionali
		Iterator<String> iSet = ts.descendingIterator();
		while(iSet.hasNext())
			System.out.println(iSet.next());
	}
	
}

class NavigableSetExample{

	public NavigableSetExample() {
		super();
		NavigableSet<String> original = new TreeSet<>();
		original.add("1");
		original.add("2");
		original.add("3");
		original.add("4");
		original.add("5");
		
		System.out.println("NavigableSet:");
		original.forEach(System.out::println);
		
		//ceiling will be "2".
		String ceiling = original.ceiling("2");
		System.out.println("ceiling: " + ceiling);

		//floor will be "2".
		String floor = original.floor("2");
		System.out.println("floor: " + floor);
		
		//higher will be "3".
		String higher = original.higher("2");
		System.out.println("higher: " + higher);
		
		//lower will be "1"
		String lower = original.lower("2");
		System.out.println("lower: " + lower);
	}
}