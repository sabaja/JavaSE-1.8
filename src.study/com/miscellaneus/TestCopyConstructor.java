package com.miscellaneus;

class TestCopyConstructor {

	public static void main(String[] args) {
		Obj2D o2 = new Obj2D(3,5);
		System.out.println(o2.toString());
		Obj2D o3 = new Obj2D(o2);
		System.out.println(o3.toString());

	}

}

class Obj2D{
	private int a;
	private int b;
	
	public Obj2D(int a, int b) {
		super();
		this.a = a;
		this.b = b;
	}
	
	//Copy constructor
	public Obj2D(Obj2D o2){
		this.a = o2.getA();
		this.b = o2.getB();
	}
	
	public int getA() {
		return a;
	}
	public void setA(int a) {
		this.a = a;
	}
	public int getB() {
		return b;
	}
	public void setB(int b) {
		this.b = b;
	}

	@Override
	public String toString() {
		return "Obj2D [a=" + a + ", b=" + b + "]";
	}
}
