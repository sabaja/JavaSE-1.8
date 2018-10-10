package com.generics;

import java.util.Arrays;
import java.util.List;

public class UpperBoundedWildcards {
	public static void main(String []args){
		List<Double> l = Arrays.asList(12.3,141.1,414D,4D);
		System.out.println(l + " tot: " + UpperBoundedWildcards.sumOfList(l));
		
		List<Integer> l1 = Arrays.asList(12,3,141,1,414,67);
		System.out.println(l1 + " tot: " + (int)UpperBoundedWildcards.sumOfList(l1));
	}
	//Lavora con tutte le classi sotto Number
	public static double sumOfList(List<? extends Number> list) {
	    double s = 0.0;
	    for (Number n : list)
	        s += n.doubleValue();
	    return s;
	}
}

