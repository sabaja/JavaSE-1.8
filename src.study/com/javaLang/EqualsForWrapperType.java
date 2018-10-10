package com.javaLang;

public class EqualsForWrapperType {

	private int num;
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + num;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EqualsForWrapperType other = (EqualsForWrapperType) obj;
		if (num != other.num)
			return false;
		return true;
	}

	public static void main(String[] args) {
		Integer a = 1000;
		Integer b = 1000;
		System.out.println("==  \t" + (a == b));
		System.out.println("equals " + a.equals(b) + "\n");
		Integer c = 127; //byte
		Integer d = 127; //byte
		Integer x = 126;
		byte y = 127;
		System.out.println("==  \t" + (c == d) + " " + (d == x) + " " +  (d == y));
		System.out.println("equals " + c.equals(y) + "\n");
		Integer e = 128; //Integer
		Integer f = 128; //Integer
		System.out.println("==  \t" + (f == e));
		System.out.println("equals " + f.equals(e) + "\n");		
		int g = 10000;
		Integer h = 10000;
		System.out.println("== : \t" + (g == h));
		System.out.println("equals : " + h.equals(g));		
		
		
		
	}

}
