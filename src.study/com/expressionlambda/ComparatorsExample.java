/**
 * Creare una classe TestComparators contenente un metodo main() che dichiara
un array di stringhe (di cui almeno due con la stessa lunghezza). Creare i seguenti
oggetti Comparator con espressioni lambda o reference a metodi:
 in ordine di lunghezza (dalla stringa più lunga alla più breve);
 in ordine di lunghezza al contrario (dalla stringa più breve alla più lunga);
 in ordine alfabetico (usare comunque un’espressione lambda o un reference
a metodo anche se non ce ne sarebbe bisogno);
 in ordine alfabetico inverso;
 in ordine di lunghezza e in caso di stessa lunghezza in ordine alfabetico.
Ordinare l’array mediante il metodo sort() della classe Arrays , che prende in
input come primo parametro l’array dichiarato e come secondo parametro un og-
getto di tipo Comparator . Dopo ogni ordinamento stampare il risultato.
Creare questa classe in un package come per esempio: com.java8.mod15.test .
 */

package com.expressionlambda;

import java.util.Arrays;
import java.util.Comparator;

public class ComparatorsExample {
	public static void main(String[] args) {
		String[] names = {"Clarissa", "Jem", "Top", "Ermeringildo",
				"Iamaca", "Tom", "Arlequin", "Francesca", "Cumbus"};

		Comparator<String> lenghtOrder = (s1, s2) ->
				-(Integer.compare(s1.length(), s1.length()));

		Comparator<String> inverseLenghtOrder = (s1, s2) ->
				Integer.compare(s1.length(), s1.length());

		Comparator<String> alphabeticalOrder = (s1, s2) ->
				s1.compareTo(s2);
		
	Comparator<String> reverseAlphabeticalOrder = (s1,s2) ->
		-(s1.compareTo(s2));
		
	Comparator<String> mixOrder = (s1,s2) ->{
		int result = Integer.compare(s1.length(), s2.length());
		if(result == 0)
			result = s1.compareTo(s2);
		return result;
	};
	Arrays.sort(names, lenghtOrder);
	System.out.println("Nomi ordinati per lunghezza:\n" + Arrays.asList(names));
	
	Arrays.sort(names, inverseLenghtOrder);
	System.out.println("\nNomi ordinati per lunghezza al contrario:\n" + Arrays.asList(names));
	
	Arrays.sort(names, alphabeticalOrder);
	System.out.println("\nNomi ordinati alfabetiacamente:\n" + Arrays.asList(names));
	
	Arrays.sort(names, reverseAlphabeticalOrder);
	System.out.println("\nNomi ordinati alfabetiacamente al contrario:\n" + Arrays.asList(names));
	
	Arrays.sort(names, mixOrder);
	System.out.println("\nNomi ordinati in ordine misto:\n" + Arrays.asList(names));


	}
}
