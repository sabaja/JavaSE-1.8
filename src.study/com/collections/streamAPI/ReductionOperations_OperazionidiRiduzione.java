package com.collections.streamAPI;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

/**
 * 
 * @author sabaja
 * sono tipicamente le operazioni di riduzione su stream tipo:
 * average(), max(), min(), sum(), count() etc..
 *
 */
public class ReductionOperations_OperazionidiRiduzione {

	public static void main(String[] args) {
		Collection<SmartPhone_> phones = new HashSet<>();
		phones.add(new SmartPhone_("Iphone", "Apple", 890.587));
		phones.add(new SmartPhone_("IPad", "Apple",999.22));
		phones.add(new SmartPhone_("One", "Htc", 543.86));
		phones.add(new SmartPhone_("Galaxy 3", "Samsung", 589.56));
		phones.add(new SmartPhone_("Galaxy Note", "Samsung", 300.56));
		phones.add(new SmartPhone_("Galaxy TabA", "Samsung", 213.56));
		
		//Contiamo gli Apple
		
		long count = phones.stream().filter(
				(s) -> s.getMark().equals("Apple")).count();
		System.out.println("Numero degli apple: " + count);
		
		//Sommiamo i prezzi di Samsung
		double sum = phones.stream().filter(
				s -> s.getMark().equals("Samsung")
				).mapToDouble(SmartPhone_::getPrice).sum();
		System.out.println("somma prezzi Samsung: " + sum);
		
		//Facciamo la media
		double avg = phones.stream().filter(
				(s) -> s.getMark().equals("Apple")
				).mapToDouble(SmartPhone_::getPrice).average().getAsDouble();
			System.out.println("media apple: " + avg);
			
		//Massimo degli Apple
		double max = phones.stream().filter(
				s -> s.getMark().equals("Apple")
				).mapToDouble(SmartPhone_::getPrice).max().getAsDouble();
		System.out.println("max apple: " + max);
		
		//Minimo apple
		double min = phones.stream().filter(
				s -> s.getMark().equals("Apple")
				).mapToDouble(SmartPhone_::getPrice).min().getAsDouble();
		System.out.println("min apple: " + min);
		
		/**
		 * Metodo Reduce degli oggetti *_Stream
		 * reduce(int identity, IntBinaryOperator op)
		 * Performs a reduction on the elements of this stream, 
		 * using the provided identity value and an associative 
		 * accumulation function, and returns the reduced value.
		 */
		
	}

}


class SmartPhone_{
	private String name;
	private String mark;
	private double price;
	
	public SmartPhone_(String name, String mark, double price) {
		super();
		this.name = name;
		this.mark = mark;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Phone [name=" + name + ", mark=" + mark + ", price="+ price +"]";
	}
	
	public Collection<SmartPhone_> getPhone(){
		Collection<SmartPhone_> phone = new ArrayList<>();
		phone.add(this);
		return phone;
	}
}