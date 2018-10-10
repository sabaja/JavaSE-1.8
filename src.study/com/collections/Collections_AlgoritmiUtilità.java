package com.collections;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.TreeSet;

public class Collections_AlgoritmiUtilità {

	public static void main(String[] args){
		Collection<String> c = new HashSet<>();
		final Collection<String> hs = Collections.unmodifiableCollection(c);
		//piu altri metodi di Collections e Arrays..vedere il 
		//libro pag 458-460
		
	//per rimuove una collection predefinita dentro un'altra collection
	//usando una copia singleton immutabile:
	//!!! non funziona: bands.removeAll(Collections.singleton(progBands));	
		Collection<String> bands  = new TreeSet<>();
		bands.add("Depeche Mode");
		bands.add("U2");
		bands.add("Yes");
		bands.add("PFM");
		bands.add("David Guetta");
		bands.add("Giganti");
		bands.add("Sex Pistols");
		bands.add("Gaggi");
		
		
		Collection<String> progBands = new TreeSet<>();
		progBands.add("Yes");
		progBands.add("PFM");
		progBands.add("Giganti");
		progBands.add("Gaggi");
		
		
		//Oggetto da rimuovere
		boolean f = bands.removeAll(progBands);
		System.out.println(f + " " + bands);
	}
}
