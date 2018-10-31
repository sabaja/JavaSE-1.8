package com.expressionLambda;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.IntStream;

public class SortBooleanArray {

	public static void main(String[] args) {
		boolean[] arr = new boolean[] { true, false, false, true, true, true, false, false, true, true, false, false };
		int len = arr.length;
		Boolean[] arB = new Boolean[len];
		for (int i = 0; i < len; i++) {
			arB[i] = Boolean.valueOf(arr[i]);
		}

		List<Boolean> list = (List<Boolean>) Arrays.asList(arB);
		Collections.reverse(list);
		list.forEach(System.out::println);
		System.out.println();

		// Consumer<List<String>> con = name -> {
		// ;
		// };

		Comparator<Boolean> reverse = (s1, s2) -> -(s1.compareTo(s2));
		Arrays.sort(arB, reverse);
		for (Boolean b : arB)
			System.out.println(b);

	}

	public static int[] sortIt(int[] in) {
		Arrays.parallelSort(in);

		return in;
	}

	public static int[] mergeAndSort(final int[]... in) {
		int[] tmp = new int[0];

		for (int[] x : in) {
			tmp = IntStream.concat(Arrays.stream(tmp), Arrays.stream(x)).distinct().toArray();
		}

		return sortIt(tmp);
	}
}
