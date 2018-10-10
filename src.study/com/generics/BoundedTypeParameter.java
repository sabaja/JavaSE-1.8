package com.generics;

class BoundedTypeParameter {
	   public static void main(String[] args) {
	        Shape<Integer> integerShape = new Shape<Integer>();
	        integerShape.set(new Integer(10));
	        integerShape.inspect(new Integer("23")); // error: this is still String!
	    }
}

class Shape<T> {

    private T t;          

    public void set(T t) {
        this.t = t;
    }

    public T get() {
        return t;
    }

    public <U extends Number> void inspect(U u){
        System.out.println("T: " + t.getClass().getName());
        System.out.println("U: " + u.getClass().getName());
    }

}