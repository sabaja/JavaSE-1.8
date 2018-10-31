package com.miscellaneus;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class AbstractShapeInheritanceExample{
	public static void main(String[] args){
		Shape rect = new Rectangle(32.4, 12.0);
		
	}
}

abstract class Shape {
	abstract public Double calculatePerimeter();
	abstract public Double calculateArea();
}


class Rectangle extends Shape{

	private double sideA, sideB;
	
	public Rectangle(double sideA, double sideB) {
		super();
		this.sideA = sideA;
		this.sideB = sideB;
		Class cls = Rectangle.class;
		Field[] fields = cls.getDeclaredFields();
		for(Field field : fields){
			System.out.println("Field " + field.getName() + " type: " + field.getType());
		}
		
		Method[] methods =  cls.getDeclaredMethods();
		for(Method m : methods){
			System.out.println("Method " + m.getName() + " " + cls.getSimpleName());
		}
		
	}
	
	@Override
	public Double calculatePerimeter() {
		System.out.println();
		return null;
	}

	@Override
	public Double calculateArea() {
		// TODO Auto-generated method stub
		return null;
	}

	public double getSideA() {
		return this.sideA;
	}

	public void setSideA(double sideA) {
		this.sideA = sideA;
	}

	public double getSideB() {
		return this.sideB;
	}

	public void setSideB(double sideB) {
		this.sideB = sideB;
	}

	@Override
	public String toString() {
		return "Rectangle [sideA=" + this.sideA + ", sideB=" + this.sideB + "]";
	}

	
	
}

class Square extends Shape{
	private Double radius;

	public Square(Double radius) {
		super();
		this.radius = radius;
	}
	
	@Override
	public Double calculatePerimeter() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Double calculateArea() {
		// TODO Auto-generated method stub
		return null;
	}

	public Double getRadius() {
		return this.radius;
	}

	public void setRadius(Double radius) {
		this.radius = radius;
	}

	@Override
	public String toString() {
		return "Square [radius=" + this.radius + "]";
	}


}

