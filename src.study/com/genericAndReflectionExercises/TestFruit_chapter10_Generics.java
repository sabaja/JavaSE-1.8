package com.genericAndReflectionExercises;

import java.util.ArrayList;
import java.util.Objects;

class TestFruit_chapter10_Generics {

	public static void main(String[] args) {
		Basket<Apple> basketApple = new Basket<>();
		Apple a2 = new Apple(2D);
		Peach p1 = new Peach(3D);
		try {
			basketApple.add(new Apple(2.2));
			basketApple.add(a2);
			basketApple.printBasket();
		} catch (BasketException e) {
			e.printStackTrace();
		}
		int x =2;
		int y = ( 
					( (int) (8 * Math.pow(x, 3) ) ) 
							- 
					( (int) (16 * Math.pow(x, 2) )) 
					    	+ 
					( (int) (4 * x)) -1) 
				/
					(4*x -2);
		System.out.println(y);
		}

}

enum TypeFruit {
	APPLE("apple"), ORANGE("orange"), PEACH("peach");
	String typeName;
	
	private TypeFruit(String name){
		typeName = name;
	}
	
	public String getType(){
		return typeName;
	}
}

abstract class Fruit {
	
	@SuppressWarnings("unused")
	private double weight;

	public abstract void setWeight(Double weight);
	public abstract double getWeight();
}

class Apple extends Fruit {

	public static final TypeFruit TYPE;
	private Double weight;

	static {
		TYPE = TypeFruit.APPLE;
	}

	public Apple(Double weight) {
		setWeight(weight);
	}

	@Override
	public void setWeight(Double weight) throws NullPointerException {
		this.weight = Objects.requireNonNull(weight);
	}

	@Override
	public double getWeight() {
		return weight;
	}
	
	public static TypeFruit getType() {
		return Apple.TYPE;
	}
	
	@Override
	public String toString(){
		return TypeFruit.APPLE.getType();
	}
}

class Orange extends Fruit {

	public static final TypeFruit TYPE;
	private Double weight;

	static {
		TYPE = TypeFruit.ORANGE;
	}

	public Orange(Double weight) {
		setWeight(weight);
	}

	@Override
	public void setWeight(Double weight) throws NullPointerException {
		this.weight = Objects.requireNonNull(weight);
	}
	
	@Override
	public double getWeight() {
		return weight;
	}
	
	public static TypeFruit getType() {
		return Apple.TYPE;
	}
	
	@Override
	public String toString(){
		return TypeFruit.ORANGE.getType();
	}
}

class Peach extends Fruit {

	public static final TypeFruit TYPE;
	private Double weight;

	static {
		TYPE = TypeFruit.PEACH;
	}

	public Peach(Double weight) {
		setWeight(weight);
	}

	@Override
	public void setWeight(Double weight) throws NullPointerException {
		this.weight = Objects.requireNonNull(weight);
	}

	@Override
	public double getWeight() {
		return weight;
	}
	
	public static TypeFruit getType() {
		return Apple.TYPE;
	}
	
	@Override
	public String toString(){
		return TypeFruit.PEACH.getType();
	}
}

class Basket<E extends Fruit>{
	private final double MAXCAPACITY = 5D; 
	private ArrayList<E> fruits;
	
	public Basket(){
		this.fruits = new ArrayList<>();
	}
	
	public ArrayList<E> getFruits(){
		return fruits;
	}
	
	public void add(E fruit) throws BasketException{
		if(getWeight() > MAXCAPACITY){
			double rest = getWeight() - MAXCAPACITY;  
			throw new BasketException(Double.toString(rest));
		}
		else{
			fruits.add(fruit);
		}
	}
	
	public Double getWeight() {
		double wght = 0D;
		for (Fruit f : fruits) {
			wght += f.getWeight();
		}
		return wght;
	}
	
	public void printBasket(){
		if(getSizeBasket() > 0){
			System.out.println(fruits.get(0).getClass().getSimpleName());
			for(Fruit fr : fruits)
				System.out.print(fr.getWeight() + " ");
			System.out.println(getWeight());
		}
			
	}
	
	private int getSizeBasket() throws NullPointerException{
		return Objects.requireNonNull(fruits.size());
	}
}

class BasketException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5332011117235513720L;

	public BasketException(String message){
		super("Weight exceeding error " + message + " kg more than 5.0 kg of maximum capacity");
	}
	
}