package com.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Hashtable;
import java.util.Map;

@Target ({ElementType.FIELD, ElementType.TYPE, ElementType.TYPE_PARAMETER})
@Retention (RetentionPolicy.RUNTIME)
@interface Alphabet{
	Series value();
	enum Series{a,b,c,d,e,f,g,h,i,l,m,n,p,q,r,s,t,v,z;}
}

@Retention (RetentionPolicy.CLASS)
@interface SingleValue{
	int value();
}

@Retention (RetentionPolicy.RUNTIME)
@Target ({ElementType.TYPE, ElementType.TYPE_PARAMETER})
@interface NotSingleValue{
	String notsinglevalue() default "NOT SINGLE VALUE";
}

@Target ({ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention (RetentionPolicy.RUNTIME)
@interface HellBurn{
	HellZone value();
	enum HellZone {LIMBO, ROUND_1, ROUND_2, ROUND_3};
}

class Utility<T>{
	
	@Alphabet(Alphabet.Series.a) 
	private String a;
	

	@SingleValue (value = 100)
	private int value;
	@SingleValue (value = 1)
	private T ele;
	private Map<String,Integer> map;
	
	private Hashtable<String,Integer> hashSet;
	
	//Type_USE
	public void setEle(@HellBurn (value = HellBurn.HellZone.ROUND_2) T ele){
		
	}
}

@Alphabet (Alphabet.Series.c)
class SingleValueAnnotation {
	
}
