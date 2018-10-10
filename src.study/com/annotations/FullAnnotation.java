package com.annotations;

import java.awt.RenderingHints.Key;
import java.beans.MethodDescriptor;
import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.annotations.AnnotationsPublisher.Priority;

@Retention(RetentionPolicy.RUNTIME)
@interface AnnotationsPublisher {
	//può esserci un valore di default alla variabile description 
	//se non riportato in fase di implementazione, questo è il valore assegnato	
	String description() default "First Example";
	String assignTo();
	enum Priority{HIGH, MEDIUM, LOW;} 
	Priority priority() default Priority.MEDIUM;
}

/*
 * l'annotazione viene assegnata a un metodo xxx() come se fosse un modificatore
 * */


class FullAnnotation{
	public static void main(String []args) throws Exception{
		Map<String,String> map = new HashMap<>();
		Method []methods = Class.forName("com.annotations.chptr11.Something").getDeclaredMethods();
		AnnotationsPublisher ab = null;
		for(Method m : methods){
			if((ab = m.getAnnotation(AnnotationsPublisher.class)) != null){
				String desc = ab.description();
				String asTo = ab.assignTo();
				map.put(asTo, desc);
			}
		}
		publisherAnnotation(map);
	}
	
	static public void publisherAnnotation(Map<String,String> map){
		Set<String> keys = map.keySet();
		for(String k : keys ){
			System.out.println("Key: " + k + "\nvalue: " + map.get(k));
		}
	}
	
	
}
	


class Something{
	@AnnotationsPublisher
		(description = "Sockless Sneakers all day",
		 assignTo = "Clair",
		 priority=Priority.HIGH)
	
	public void SomethigToDO(){
	//............	
	}
}