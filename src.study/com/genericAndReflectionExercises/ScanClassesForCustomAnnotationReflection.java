package com.genericAndReflectionExercises;

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Set;

import org.reflections.Reflections;

public class ScanClassesForCustomAnnotationReflection {

	
	public void scan(final String strPackage) {
		Reflections reflections = new Reflections(strPackage);

		Set<Class<? extends Object>> allClasses = 
		                 reflections.getSubTypesOf(Object.class);


		for (Class clazz : allClasses) {
		   Annotation[] annotations = clazz.getAnnotations();

		   for (Annotation annotation : annotations) {
		     if (annotation instanceof MyAnnotation) {
		        MyAnnotation myAnnotation = (MyAnnotation) annotation;
		        System.out.println("value: " + myAnnotation.value());
		     }
		   }
		}     
	}
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@interface MyAnnotation{
	String value();
}