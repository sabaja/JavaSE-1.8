package com.generics;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class LowerWildcardBounded {

	public static void main(String[] args) {
		ArrayList<Integer> l = new ArrayList<>();
		l.add(12);l.add(13);l.add(1);
		LowerWildcardBounded.addNumbers(l);
	}
	//Integer è la più bassa
	public static void addNumbers(List<? super Integer> list) {
	    for (int i = 1; i <= 10; i++) {
	        list.add(i);
	    }
	}
}
