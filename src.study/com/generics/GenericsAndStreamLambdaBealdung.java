package com.generics;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public interface GenericsAndStreamLambdaBealdung {

	public static <T, G> List<G> fromArrayToList(T[] a, Function<T, G> mapperFunction) {
		return Arrays.stream(a).map(mapperFunction).collect(Collectors.toList());
	}
	
	//trasforma da integer a lista di 
	public static void main(String[] args) {
		  Integer[] intArray = {1, 2, 3, 4, 5};
		    List<String> stringList
		      = fromArrayToList(intArray, Object::toString);
		  
		    System.out.println(stringList);
	}
}
