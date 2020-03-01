package com.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//https://blog.chalda.it/guida-alla-sintassi-delle-espressioni-regolari-217.html

public class RegexValidationPrintExample {

	private final int NUM_PAG = 12;
	private final String REGEX = "^([1-9][0-9]*|[1-9][0-9]*\\-[1-9][0-9]*)(,([1-9][0-9]*|[1-9][0-9]*\\-[1-9][0-9]*))*$";
	private String exceptionNumPage = "Il range immesso [%d] supera il numero delle pagine del corrente documento";
	private final int N = 2;
	private String input = "12,3-5,4,1-3";
	private String input2 = "1";
	private String input3 = "1-8";
	private String input4 = "2,3,4";
	private String input5 = "2,90-4";

	// Input
	/*----------------------------------------------------------------------------------------------------*/
	// Validazione
	// un numero ex: 1 o 11 o 123, senza 0 iniziali
	// String regex1 = "^[1-9][0-9]*";
	// 1,2
	// String regex2 =
	// "^[1-9][0-9]*(([,][1-9][0-9]*)*|([\\-][1-9][0-9]*)*)*";
	// String regex2 =
	// "^[1-9][0-9]*(([,][1-9][0-9]*)*|(([\\-][1-9][0-9]*)|([,][1-9][0-9]*)*)*)*";
	// String regex2 =
	// "^[1-9][0-9]*(([,][1-9][0-9]*)*|(([\\-][1-9][0-9]*)\\b([,][1-9][0-9]*)*)*)*";
	// String regex2 =
	// "^([1-9][0-9]*|\\-[1-9][0-9]*){1}((,[1-9][0-9]*|\\-[1-9][0-9]*))*";
	public static void main(String[] args) {
		RegexValidationPrintExample r = new RegexValidationPrintExample();
		r.rangeCopyManger();
	}

	private void rangeCopyManger() {
		Collection<String> rangeList = new ArrayList<>();
		// validazione dell'input
		boolean isMatched = this.validation(input);
		if (isMatched) {
			// split della stringa senza virgola
			rangeList = splitComma(input);
		}

	}

	private boolean validation(String input) {

		Pattern pattern = Pattern.compile(REGEX);
		Matcher m = pattern.matcher(input);
		boolean isMatched = m.matches();
		System.out.println(isMatched);
		return isMatched;
	}

	/**
	 * 1 Se contiene '-' split in un array di 2 elementi trasformati in Integer
	 * 1.1 controllo i[0] e i[1] se maggiore di numero massimo di pagine. 1.1.2
	 * se maggiore num pagine: rilancio eccezione: range superiore al numero di
	 * pagine del documento 1.1.3 se i[1] > i[0]: rilancio eccezione: range non
	 * valido 1.2 altrimenti costruisco una lista Collection<String> tempPages =
	 * new ArrayList<>(); anche con un solo elemento nel caso i[1] == i[0] 2
	 * altrimenti trasformo in integer subito 2.1 se maggiore num pagine:
	 * rilancio eccezione: range superiore al numero di pagine del documento 2.2
	 * inserisco l'elemento in una Collection<String> pages = new ArrayList<>();
	 * 3 Se tempPages != null aggiungo tempPages a pages
	 * pages.addAll(tempPages);
	 * 
	 * @param rangeList
	 * 
	 */
	public void compositionPrintigElement(Collection<String> rangeList) throws Exception {
		Iterator<String> i = rangeList.iterator();
		// N = 2
		String[] arr = new String[N];
		// Se contiene '-', split in un array di 2 elementi trasformati in
		// Integer
		while (i.hasNext()) {
            String element = i.next();
            System.out.println(element);
            Collection<Integer> listInt = new ArrayList<Integer>(N);
            if (element.contains("-")) {
                arr = element.split("-");
                final int N = arr.length;
                int temp;
                for (int e = 0; e < N; e++) {
                    temp = Integer.valueOf(arr[e]);
                    // se maggiore num pagine: rilancio eccezione
                    if (temp > NUM_PAG) {
                        String.format(exceptionNumPage, temp);
						throw new Exception(exceptionNumPage);
					}
					listInt.add(temp);
				}
			}
			if (null != listInt || !listInt.isEmpty()) {
				// TODO
			}
		}
	}

	private Collection<String> splitComma(String input) {
		return Arrays.asList(input.split(","));
	}
}