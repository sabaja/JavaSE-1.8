package com.collectionsExercises;

import java.util.HashSet;
import java.util.Set;

/**
 * 
 * @author sabaja
 *	
 * Creare una collezione che nel caso si aggiungano due elementi uguali 
 * sollevi un’eccezione personalizzata. 
 * Creare anche una classe di test.
 */
public class CheckSingleAddHashSet {
	private static CheckHashSet<String> set;
	
	public static void main(String[] args) {
		set = setCheckSingleAddHash();
		set.add("Ciao");
		set.add("sono Pippo");
		set.add("Ciao");
		set.forEach(s -> System.out.println(s));

	}

	public static  CheckHashSet<String> setCheckSingleAddHash(){
		set = new CheckHashSet<>();
		set.add("Italia");
		set.add("Francia");
		set.add("Polonia");
		set.add("Germania");
		set.add("Inghilterra");
		set.add("Spagna");
		set.add("Grecia");
		set.add("Olanda");
		set.add("Portogallo");
		set.add("Belgio");
		return set;
	}
}


class CheckHashSet<E> extends HashSet<E>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 * @param e
	 * Solleva un'eccezione @CheckSingleException se viene aggiunto
	 * un elemento già presente
	 */
	@Override
	public boolean add(E e){
		boolean present = super.add(e); 
		if(!present){
				try {
					throw new CheckSingleException("element " +  e.toString() +
												   " already present");
				} catch (CheckSingleException e1) {
					e1.printStackTrace();
				}
		
		}
		return present;
	}
}


/**
 * Classe d'eccezione per controllo di unicità elemento 
 * in fase di inserimento.
 */
class CheckSingleException extends Exception{

	private static final long serialVersionUID = 1L;
	
	public CheckSingleException(String exception){
		super(exception);
		
	}
}