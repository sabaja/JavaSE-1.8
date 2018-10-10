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

package com.expressionLambdaExercises;

import java.util.Arrays;

public class TestComparators_fattoDaMe {

	private static String[] arr = { "Fantaman", "Sorat", "Tros", "amar non posso", "Sort", "Meeeeaaa!",
			"Java non ins3gna", "nonsi" };

	public static void main(String[] args) {
		UserPrintStringOrder.printLengthOrder(arr);
		UserPrintStringOrder.printReverseLengthOrder(arr);
		UserPrintStringOrder.printAlphabeticalOrder(arr);
		UserPrintStringOrder.printReverseAlphabeticalOrder(arr);
		UserPrintStringOrder.printMixOrder(arr);;

	}

}

class UserPrintStringOrder {
	public static void printLengthOrder(String[] arr) {
		Arrays.sort(arr, OrderingFilter::lengthOrder);
		System.out.println("\nOrdered by lenght Array:");
		for (String str : arr) {
			if (str != null) {
				System.out.println(str);
			}
		}
	}
	
	public static void printReverseLengthOrder(String[] arr) {
		Arrays.sort(arr, OrderingFilter::reverseLengthOrder);
		System.out.println("\nReverse ordered by lenght Array:");
		for (String str : arr) {
			if (str != null) {
				System.out.println(str);
			}
		}
	}

	public static void printAlphabeticalOrder(String[] arr) {
		Arrays.sort(arr, OrderingFilter::alphabeticalOrder);
		System.out.println("\nAlphbetical ordered array:");
		for (String str : arr) {
			if (str != null) {
				System.out.println(str);
			}
		}
	}
	
	public static void printReverseAlphabeticalOrder(String[] arr) {
		Arrays.sort(arr, OrderingFilter::reverseAlphabeticalOrder);
		System.out.println("\nAlphbetical ordered array:");
		for (String str : arr) {
			if (str != null) {
				System.out.println(str);
			}
		}
	}
	
	public static void printMixOrder(String[] arr) {
		Arrays.sort(arr, OrderingFilter::mixOrder);
		System.out.println("\nMix ordered array:");
		for (String str : arr) {
			if (str != null) {
				System.out.println(str);
			}
		}
	}
}

class OrderingFilter {

	/**
	 * in ordine di lunghezza (dalla stringa più lunga alla più breve)
	 * 
	 * @param s1
	 * @param s2
	 * @return
	 */
	public static int lengthOrder(String s1, String s2) {
		Integer lenghtS1 = s1.length();
		Integer lenghtS2 = s2.length();
		return lenghtS1.compareTo(lenghtS2);
	}

	/**
	 * in ordine di lunghezza al contrario (dalla stringa più breve alla più
	 * lunga);
	 * 
	 * @param s1
	 * @param s2
	 * @return
	 */
	public static int reverseLengthOrder(String s1, String s2) {
		Integer lenghtS1 = s1.length();
		Integer lenghtS2 = s2.length();
		return -(lenghtS1.compareTo(lenghtS2));
	}

	/**
	 * in ordine alfabetico (usare comunque un’espressione lambda o un reference
	 * a metodo anche se non ce ne sarebbe bisogno);
	 * 
	 * @param s1
	 * @param s2
	 * @return
	 */
	public static int alphabeticalOrder(String s1, String s2) {
		return s1.compareToIgnoreCase(s2);
	}

	/**
	 * in ordine alfabetico inverso;
	 * 
	 * @param s1
	 * @param s2
	 * @return
	 */
	public static int reverseAlphabeticalOrder(String s1, String s2) {
		return -(s1.compareToIgnoreCase(s2));
	}

	/**
	 * In ordine di lunghezza e in caso di stessa lunghezza in ordine
	 * alfabetico.
	 * 
	 * @param s1
	 * @param s2
	 * @return
	 */
	public static int mixOrder(String s1, String s2) {
		Integer lenghtS1 = s1.length();
		Integer lenghtS2 = s2.length();
		if (lenghtS1.equals(lenghtS2)) {
			return alphabeticalOrder(s1, s2);
		}
		return lenghtS1.compareTo(lenghtS2);
	}
}
