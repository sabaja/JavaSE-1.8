package com.javaLang;

class Clone_ShallowCopy {
 
	public static void main(String []args){
		Car c = new Car("pip", 3D), d;
		try{
			d = (Car)c.clone();
			System.out.println(d.getName() + " " + d.getWheels() );
		}
		catch (CloneNotSupportedException e){
			e.printStackTrace();
		}
		
	}
}

class Car implements Cloneable{
	private String name;
	private double wheels;
	
	public Car(String name, double wheels){
		this.name = name;
		this.wheels = wheels;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getWheels() {
		return wheels;
	}

	public void setWheels(double wheels) {
		this.wheels = wheels;
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException{
		return super.clone();
	}
}

