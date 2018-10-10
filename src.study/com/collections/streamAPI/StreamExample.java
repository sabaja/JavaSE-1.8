package com.collections.streamAPI;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.OptionalDouble;
import java.util.stream.Stream;


/**
 * 
 * @author sabaja
 *	Gli stream sono flussi di dati estrapolati da Collection, Arrays o Stream stessi.
 *	sono stati implementati per ottenere delle iterazioni su questi dati con
 *	le lambda e i reference ai metodi.
 *	gli stream sono caratterizzati da operazioni di pipeline (come Unix) che
 *	si posso svolgere su di essi.
 *	Permette di utilizzare una programmazione parallela e costituita da pipeline
 *
 *	I pipeline si dividono i tre elementi:
 *	1) Sorgente - Collezioni, Array..etc oppure Stream stessi, tutti quegli oggetti
 *	   che ti permettono di ottenere uno Stream con il metodo stream().
 *
 *	2) Operazioni di Aggregazione o intermendie - filter(), map(), mapToDouble() 
 *	   operazioni che generano un altro oggetto Stream.
 *
 *	3) Operazioni finali - operazione che non restituisce uno Stream es. forEach();
 */
public class StreamExample {

	public static void main(String[] args) {
		final String []arr = {"wqer","wqerq","2141rv","mqduh"};
		//Alternativa
		Stream<String> streamT = Stream.of(arr);
		
		//Possibilità di iterare sulle collection e creare delle aggregazioni
		Collection<Phone> phones = new HashSet<>();
		phones.add(new Phone("Iphone", "Apple", 890.587));
		phones.add(new Phone("IPad", "Apple",999.22));
		phones.add(new Phone("One", "Htc", 543.86));
		phones.add(new Phone("Galaxy Note", "Samsung", 213.56));
		//per Aggregare/stampare
		phones.stream().filter(
				(s) -> "Apple".equals(s.getMark())).forEach(
						(s) ->System.out.println(s));
		
		//per aggregare e poi fare altro come stampare:
		Stream<Phone> apple = phones.stream().filter(
				(s) -> "Apple".equals(s.getMark()));
		System.out.println("da stream:");
		apple.forEach((s)->System.out.println(s));
		
		//Troviamo la media del prezzo con operazione di riduzione
		double average = 
		phones.stream().mapToDouble(Phone::getPrice).average().getAsDouble();
		System.out.println("Media totale: " + average);
		
		//Troviamo la media del prezzo per Apple
		double avg = phones.stream().filter(
			(s) -> s.getMark().equals("Apple")
			).mapToDouble(Phone::getPrice).average().getAsDouble();
		System.out.println("Media Apple: " + avg);
		
		//Troviamo la media del prezzo per i non Apple
		double avg_ = phones.stream().filter(
				(s) -> !(s.getMark().equals("Apple"))
				).mapToDouble(Phone::getPrice).average().getAsDouble();
			System.out.println("Media non Apple: " + avg_);
	}

}


class Phone{
	private String name;
	private String mark;
	private double price;
	
	public Phone(String name, String mark, double price) {
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
		return "Phone [name=" + name + ", mark=" + mark + "]";
	}
	
	public Collection<Phone> getPhone(){
		Collection<Phone> phone = new ArrayList<>();
		phone.add(this);
		return phone;
	}
	
	
}