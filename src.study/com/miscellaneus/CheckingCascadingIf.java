package com.miscellaneus;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class CheckingCascadingIf {

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

		for (Entry<String, List<String>> fases : faseActionMap.entrySet()) {
			System.out.println(fases.getKey() + " " + fases.getValue());
		}

		int[] i = { 0 };
		faseActionMap.forEach((k, v) -> {
			int c = 0;
			switch (k) {
			case "PAO":
				i[0] = c++;
				System.out.println("PAO " + i[0]);
			case "PAI":
				i[0] = c++;
				System.out.println("PAI " + i[0]);
			case "PAR":
				i[0] = c++;
				System.out.println("PAR " + i[0]);
			case "PAC":
				i[0] = c++;
				System.out.println("PAC " + i[0]);
				if ("PAI".equals(k) && cointains(v, "REBI")) {
					System.out.println(k + " " + v + " [" + i[0] + "]");
				}
				break;
			}

		});

	}
	
	public static boolean cointains(final List<String> actions, final String action) {
		return actions.stream().anyMatch(ele -> action.equals(ele));
	}

}
