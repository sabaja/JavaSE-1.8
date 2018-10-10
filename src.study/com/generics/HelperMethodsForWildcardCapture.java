package com.generics;

import java.util.Arrays;
import java.util.List;

public class HelperMethodsForWildcardCapture {
	public static void main(String[] args) {
		List<Integer> nums = Arrays.asList(1,2);
		WildcardError.foo(nums);
		System.out.println(nums);
		
		List<Integer> li = Arrays.asList(1, 2, 3);
		List<Double>  ld = Arrays.asList(10.10, 20.20, 30.30);
		WildcardErrorBad.swapFirst(li, ld);
	}
}


class WildcardError {

    public static void foo(List<?> list) {
    	fooHelper(list);
    }
    private static <T> void fooHelper(List<T> l) {
//        l.set(0, l.get(0));
    	l.set(0, l.get(1));
    }
}

class WildcardErrorBad {

    static void swapFirst(List<? extends Number> l1, List<? extends Number> l2) {
      Number temp = l1.get(0);
//      l1.set(0,l2.get(0)); // expected a CAP#1 extends Number,
                            // got a CAP#2 extends Number;
                            // same bound, but different types
//      l2.set(0, temp);	    // expected a CAP#1 extends Number,
                            // got a Number
    }//There is no helper method to work around the problem, because the code is fundamentally wrong.

}