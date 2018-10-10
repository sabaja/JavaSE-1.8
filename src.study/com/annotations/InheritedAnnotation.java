package com.annotations;

import java.lang.annotation.*;

class InheritedAnnotation {

	public static void main(String[] args) {
		Annotation[] an = Son.class.getAnnotations();
		if(an != null){
			for(Annotation a : an)
				System.out.println(a);
		}

	}

}

@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target ({ElementType.TYPE, ElementType.FIELD, ElementType.TYPE_PARAMETER, ElementType.TYPE_USE})
@interface ExeInheritedAnnotation{
	String value();
	enum PrimalColour {RED,BLUE,GREEN,WHITE,BLACK}
	PrimalColour colour() default PrimalColour.WHITE;	
}

@ExeInheritedAnnotation 
	(value = "Father", colour = ExeInheritedAnnotation.PrimalColour.GREEN)
class FatherColour{
	String name;
	String colour;
	
	public FatherColour(String name, String colour) {
		super();
		this.name = name;
		this.colour = colour;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getColour() {
		return colour;
	}

	public void setColour(String colour) {
		this.colour = colour;
	}	
}

class Son extends FatherColour{

	public Son(String name, String colour) {
		super(name, colour);
	}
	
}