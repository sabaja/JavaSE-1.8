package com.collections;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

public class IteratorWithInnestedCollection {

	public static void main(String[] args) {
		Map<Integer, ArrayList<String>> mapStr = new HashMap<>();
		ArrayList<String> s1 = new ArrayList<>();
		for (int i = 0; i < 3; i++) {
			s1.add("0°-" + String.valueOf(i));
		}
		mapStr.put(0, s1);

		ArrayList<String> s2 = new ArrayList<>();
		for (int i = 0; i < 3; i++) {
			s2.add("1°-" + String.valueOf(i));
		}

		mapStr.put(1, s2);

		System.out.println(mapStr.get(1).get(0));

		ArrayList<String> s3 = new ArrayList<>();
		for (int i = 0; i < 3; i++) {
			s2.add("3°-" + String.valueOf(i));
		}

		Iterator<String> i = s3.iterator();
		while (i.hasNext()) {
			System.out.println(i.next().toString() + " removed");
			i.remove();
		}
		if (s3.isEmpty()) {
			System.out.println("Empty " + s3.size() + " " + Objects.isNull(s3));
		} else {
			System.out.println(s3);
		}
	}

}
