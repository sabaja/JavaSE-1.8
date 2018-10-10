package com.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

class MarkerAnnotation {	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

//Sono annotation type marcatori e non hanno Annotation Elements

@Retention (RetentionPolicy.SOURCE)
@interface NativeSource{}

@Retention (RetentionPolicy.CLASS)
@interface CompileTime{}

@Retention (RetentionPolicy.RUNTIME)
@interface ClassStore{}