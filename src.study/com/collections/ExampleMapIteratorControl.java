package com.collections;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

public class ExampleMapIteratorControl {

	public static void main(String[] args) {

		Map<String, String[]> parametersMap = new HashMap<>();
		parametersMap.put("nome", new String[]{"Chaitanya"});
		parametersMap.put("cognome", new String[]{"0", "1", "2"});
		parametersMap.put("indirizzo", new String[]{"Via", " Sort ", "13"});
		
		Set<Entry<String, String[]>> entry = parametersMap.entrySet();
		Iterator<Entry<String, String[]>> i = entry.iterator();
		while (i.hasNext()) {
			Map.Entry<String, String[]> mEntry = i.next();
			System.out.print(mEntry.getKey() + " ");
			String[] params = mEntry.getValue();
			final int pLen = params.length;
			if (pLen > 1) {
				for(int e = 0; e <= pLen; e++){
					System.out.print(e == pLen ? "\n" : params[e]+" -- ");
				}
			}
			else{
				System.out.println(params[0]);
			}
		}

	}

}
