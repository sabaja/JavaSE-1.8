package com.miscellaneus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Optional;
import java.util.function.BiFunction;

public class CheckingCascadingIf {

	private static int c = 0;

	private static interface FiveFunction<K, K2, L, E, R> {
		public abstract R apply(K k, K2 k2, L l, E e);
	}

	public static void main(String[] args) {
		Map<String, List<String>> faseActionMap = new LinkedHashMap<>();

		List<String> listIn = Arrays.asList("ACCI", "REJI", "REBI");
		List<String> listOut = Arrays.asList("WITI", "REJI", "REBI");

		faseActionMap.put("PAO", listIn);
		faseActionMap.put("PAI", listIn);
		faseActionMap.put("PAR", listIn);
		faseActionMap.put("PAC", listIn);

		faseActionMap.put("PAA", listOut);
		faseActionMap.put("PIA", listOut);
		faseActionMap.put("PEA", listOut);
		faseActionMap.put("PCA", listOut);

		process(faseActionMap);

	}

	public static boolean contains(final String K1, final String K2, final List<String> actions, final String action) {
		return K1.equals(K2) && contains(actions, action);
	}

	public static boolean contains(final List<String> actions, final String action) {
		return actions.stream().anyMatch(ele -> action.equals(ele));
	}

	public static Optional<List<String>> process(Map<String, List<String>> faseActionMap) {
		printFase(faseActionMap);
		Optional<List<String>> opt;
		List<String> resFases = new ArrayList<>();
		FiveFunction<String, String, List<String>, String, List<String>> ff = (k, k2, l, e) -> {
			if (contains(k, k2, l, e)) {
				resFases.add(k);
				System.out.println("trovato n°: " + c + " key: " + k + " resFases: " + resFases);
			}
			return resFases;
		};

		int[] i = { 0 };

		faseActionMap.forEach((k, v) -> {

			switch (k) {
			case "PAO":
				i[0] = c++;
				System.out.println(k + " " + i[0]);
			case "PAI":
				i[0] = c++;
				System.out.println(k + " " + i[0]);
			case "PAR":
				i[0] = c++;
				System.out.println(k + " " + i[0]);
			case "PAC":
				i[0] = c++;

				// if(contains("PAI", k2, l, "REBI")) {
				ff.apply("PAI", k, v, "REBI");
				System.out.println(k + ": " + v + " - " + i[0]);
				break;
			}

		});
		if (!Objects.isNull(resFases)) {
			resFases.forEach(e -> System.out.println("Result: " + e));
		} else {
			System.out.println(resFases + " NULL");
		}
		return null;
	}

	private static void printFase(Map<String, List<String>> faseActionMap) {
		for (Entry<String, List<String>> fases : faseActionMap.entrySet()) {
			System.out.println(fases.getKey() + " " + fases.getValue());
		}
	}
}
