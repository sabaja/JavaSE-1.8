package com.generics;

/*
 * 
Box<String> stringBox = new Box<>();
Box rawBox = stringBox;               // OK

But if you assign a raw type to a parameterized type, you get a warning:

Box rawBox = new Box();           // rawBox is a raw type of Box<T>
Box<Integer> intBox = rawBox;     // warning: unchecked conversion

You also get a warning if you use a raw type to invoke generic methods defined in the corresponding generic type:

Box<String> stringBox = new Box<>();
Box rawBox = stringBox;
rawBox.set(8);  // warning: unchecked invocation to set(T)

 */

@SuppressWarnings("unused")
class RawType {
	  public static void main(String[] args){
	        Box<Integer> bi;
	        bi = createBox();
	    }

	    static Box createBox(){
	        return new Box();
	    }
}
