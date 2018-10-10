package com.generics;

class GenericMethodsandBoundedTypeParameters {

	public static void main(String[] args) {
		Integer []arr = {2,431,515,151,3,1,41,31,2122,2,2,2,2,22,2,41,1};
		System.out.println(GenericMethodsandBoundedTypeParameters.compare(arr, 2));

	}
	
	public static <T extends Comparable> int compare(T[] arr, T ele){
		int count = 0;
		for(T t : arr){
			if(t == ele)
				++count;
		}
		return count;
	}

}
