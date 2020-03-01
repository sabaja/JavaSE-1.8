package com.expressionlambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CollectionsFunctionalVsStream {

	public static void main(String[] args) {
		List<Integer> nums = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
		System.out.println("functional: " + functional(nums));
		System.out.println("functional Stream: " + functionalStream(nums));
	}

	// ai numeri pari aggiunge 100 e stampa solo i numeri che diviso 10 danno 0 
	// e cioè il numreo  110
	public static List<Integer> functional(List<Integer> nums) {
		List<Integer> temp = new ArrayList<>();
		int len = nums.size();
		for (int i = 0; i < len; i++) {
			int num = nums.get(i);
			if (num % 2 == 0) {
				int temp2 = num + 100;
				if (temp2 % 10 == 0) {
					temp.add(temp2);
				}
			}
		}
		return temp.isEmpty() ? null : temp;
	}

	public static List<Integer> functionalStream(List<Integer> nums) {
		List<Integer> temp = nums.stream().filter(num -> num % 2 == 0).map(num -> num + 100).filter(num -> num % 10 == 0).collect(Collectors.toList());
		return temp.isEmpty() ? null : temp ;
	}
}
