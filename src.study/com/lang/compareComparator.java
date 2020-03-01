package com.lang;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;



class compareComparator {

	public static void main(String[] args) {
		String []names = {"ant", "marc", "ser"};
		List<String> list =  Arrays.asList(names);	
		printList(list);
		Collections.sort(list, new CompareClass());
		printList(list);
		Collections.sort(list);
		printList(list);
		Collections.sort(list, new CompareClass());
		printList(list);
		
	}
	
	public static void printList(final List<?> list){
		for(Object t : list)
			System.out.print(t.toString() + " ");
		System.out.println();
	}

}

class CompareClass implements Comparator<String>{//per definire una propria forma di ordinamento

	@Override
	public int compare(String s1, String s2) {
		StringBuilder sb1 = new StringBuilder(s1);
		StringBuilder sb2 = new StringBuilder(s2);
		s1 = sb1.reverse().toString();
		s2 = sb2.reverse().toString();
		return s1.compareTo(s2);
	}

}