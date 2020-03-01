package com.lang;

import java.util.Objects;

/* Name of the class has to be "Main" only if the class is public. */
class MyObject    
{
	public static void main (String[] args) throws java.lang.Exception
	{
		Point_2<Integer> p1 = new Point_2 <>(2,5);
		Point_2<Double> p2 = new Point_2 <>(3.4,5D);
		Point_2<?> p3 = p2;
		System.out.println(p1.equals(p2));
		System.out.println(p3.equals(p2));
		Point_2<?> p4 = p3.copy();
		System.out.println(p4.getA() + " " + p4.getB() );
		if(p3 == p2)
			System.out.println(true);
		System.out.println(p1.hashCode() + " " + p2.hashCode() + " " + p3.hashCode() + " " + p4.hashCode());
		
	}
}

class Point_2 <T extends Number> 
{

	private T a;
	private T b;
	
	
	public Point_2(T a,T b)
	{
		this.a = a;
		this.b = b;
	}

	public T getA() {
		return a;
	}

	public T getB() {
		return b;
	}

	
	@Override
	public boolean equals(Object obj)
	{
		if (obj == null)
			return false;
			
		if (obj instanceof Point_2<?>)
		{
			Point_2<?> another = (Point_2<?>) obj;
			return another.a == this.a && another.b == this.b; 
		}
		else 
			return false;
	}
	
	public int hashCode(){
		return 31 *  Objects.hash(this.a, this.b);
	}

	
	public Point_2<?> copy() throws NullPointerException{
		if(Objects.isNull(this))
			throw new NullPointerException();
		Point_2<?> temp = new Point_2<>(this.a, this.b);
		return temp; 
	}
}