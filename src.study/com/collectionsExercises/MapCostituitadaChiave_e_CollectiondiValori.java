package com.collectionsExercises;

import java.util.ArrayList;

/**
* output:
* {
* 1=[Germania, Inghilterra], 
* 2=[Francia, Belgio, Polonia], 
* 3=[Olanda, Italia, Spagna], 
* 4=[Grecia, Portogallo]
* }
* 
* Si ha una classe che estende una hashMap<K,Collection<V>>
* che utilizza un metodo void add(K key, V value)
* che controlla se il valore è nullo tramite this.get(k)...quindi verifica se è la prima volta che viene inserita questa collection.
* se nullo:
*  - crea una collection nuova di tipo ArrayList (una serializaable list di elementi ordinati secondo l'inserimento)  
*  - aggiunge il valore nuovo nella collection di tipo ArrayList
*  - aggiunge la collection/arraylist in posizione k tramite this.put(K key, V value)
* 
* se non nullo il this.get(K):
*  - prende l'elemento k-iesimo (che è una collection) e istanzia una nuova collection di tipo V  - Collection<V> collection = this.get(key);
*  - e aggiunge il valore nuovo alla collection caricata con tutti gli elementi precedenti - collection.add(value). 
* 
* 
* public static void setIncrementalMap(IncrementalMap<Integer, String> map) 
* {
* 	CheckHashSet<String> set = CheckSingleAddHashSet.setCheckSingleAddHash();
* 	int i = 1;
* 	int j = 1;
* 	Iterator<String> iterator = set.iterator();
* 	while (iterator.hasNext()) {
* 	if (i % 3 == 0) 
* 	{
* 		j++;
* 	}
* 	String string = iterator.next();
* 	map.add(j, string);
* 	i++;
* }
*
*/

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

public class MapCostituitadaChiave_e_CollectiondiValori {

	public static void main(String[] args) {
		CheckHashSet<String> set = CheckSingleAddHashSet.setCheckSingleAddHash();
		IncrementalMap<Integer,String> map = new IncrementalMap<>();
		
		set.add("Ciao");
		set.add("sono Pippo");
		map.setIncrementalMap(map, set, 3);
		System.out.println(map);
	}

}

class IncrementalMap<K, V> extends HashMap<K, Collection<V>> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6360236213150427439L;

	private void add(K key, V value) {
		if (this.get(key) == null) {// verifica se è la prima volta che viene
									// inserito questo valore.
			Collection<V> set = new ArrayList<V>();// crea una collection nuova
													// di tipo ArrayList (una
													// serializable list di
													// elementi ordinati secondo
													// l'inserimento)
			set.add(value);// aggiungo il valore all'arrayList
			this.put(key, set);// inserisco l'arraylist in posizione key
		} else {
			Collection<V> collection = this.get(key);// creo una collection
														// nuova e inserisco i
														// valori preesistenti
			collection.add(value);// inserisco il nuovo valore
		}
	}

	/**
	 * 
	 * @param map:IncrementalMap<Integer, String>  
	 * @param set
	 * @param n
	 */
	public void setIncrementalMap(IncrementalMap<Integer, String> map, CheckHashSet<String> set,Integer n) {
		int i = 0;
		int j = 0;
		Iterator<String> iterator = set.iterator();
		//verifica n minore o uguale a 0 e non superiore a 4
		if(n == 0 || n < 0)
			n = 1;
		else if( n > 5)
			n = 4;
			
		while (iterator.hasNext()) {
			if (i % n == 0) {
				j++;
			}
			String string = iterator.next();
			map.add(j, string);
			i++;
		}
	}
}