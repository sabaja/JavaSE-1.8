package com.generics;

import java.util.Objects;

class GenericsClass <E>{

	private String name;
	private E element;
	
	public GenericsClass(){
		this(" ", null);
	}
	
	public GenericsClass(String name, E element){
		this.name = name;
		this.element = element;
	}
	
	@Override	
	public boolean equals(Object obj){
		if(!(obj instanceof GenericsClass<?>))
			return false;
		
		GenericsClass<?> temp = (GenericsClass<?>) obj;
		return Objects.equals(this.name, temp.name) 
							&& 
			   Objects.equals(this.element, temp.element);
	}

	public int hashcode(){
		return 31 * (Objects.hashCode(this.element) * Objects.hashCode(this.name));
	}
	
}

class TestClass{
	public static void main(String[] args){
		
	}
}
