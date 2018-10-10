package com.javaLang;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;


class ReflectionExample {
	public static void main(String[] args) throws Exception {
		String i = utilityForClassReflection.createObjectFromClass(String.class);
		//non funziona con con le classi che non hanno un no arg default constructor tipo Integer
		//ese: http://www.java2s.com/Code/Java/Reflection/ObjectReflectioninvokeconstructorwithparameters.htm
		
		System.out.println(i);
	}
}

class utilityForClassReflection{
	static public Van newIsntaceForVanClass(String name, double wheel) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, ClassNotFoundException{
		Class<Van> vanClass = (Class<Van>) Class.forName("com.javaLang.Van");
		Constructor<?> []c = vanClass.getConstructors();
		return (Van) c[0].newInstance(name, wheel);
	}
		
	static public <T> T createObjectFromClass(Class<T> type){
		T obj = null;
		try{
			obj = type.newInstance();
		}
		catch(InstantiationException | IllegalAccessException e){
			e.printStackTrace();
		}
		return obj;
	}
	
}