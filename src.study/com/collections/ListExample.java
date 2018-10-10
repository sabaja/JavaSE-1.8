package com.collections;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ListExample {

	public static void main(String[] args) {
		List_ArrayList la = new List_ArrayList();
		la.arrayListAumentaCapacità();
	}
	
}

class List_ArrayList{

	public List_ArrayList() {
		super();
		List<Integer> ls = new ArrayList<>();
		for(int i = 0;  i < 10; i++){
			ls.add(new Random().nextInt(2000));//Random da 0 a 2000
		}
		final int s =  ls.size();
		for(int i = 0; i< s; i++)
			System.out.println("pos_" + (i+1) + "\t" + ls.get(i));
		
	}
	/**
	 * Metodo per aumentare la capacità ensureCapcity(int) 
	 * di un arrayList in modo tale da non perdere prestazioni
	 * con add 
	 */
	public void arrayListAumentaCapacità(){
		ArrayList<String> ls = new ArrayList<>();
		ls.ensureCapacity(100_000_000); // senza ottengo java.lang.OutOfMemoryError: Java heap space 
		Long start = System.currentTimeMillis();//trimOff() per togliere
		for(int i = 0; i < 100000000; i++)
			ls.add("");
		Long end = System.currentTimeMillis();
		System.out.println(end-start);
	}
	
}