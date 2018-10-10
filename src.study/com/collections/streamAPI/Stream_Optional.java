package com.collections.streamAPI;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.function.Supplier;

/**
 * 
 * @author sabaja
 * Le classi Optional permettono con i loro metodi
 * di ovviare al problema del NullPointerException
 * con orElse() e altri.
 * Inoltre hanno metodi a cui è possibile passare
 * espressioni lambda o valori di ritorno alternativi
 * 
 */

public class Stream_Optional {

	public static void main(String[] args) {
		Collection<TelePhone> phones = new HashSet<>();
		phones.add(new TelePhone("Iphone", "Apple", 890.587));
		phones.add(new TelePhone("IPad", "Apple",999.22));
		phones.add(new TelePhone("One", "Htc", 543.86));
		phones.add(new TelePhone("Galaxy 3", "Samsung", 589.56));
		phones.add(new TelePhone("Galaxy Note", "Samsung", 300.56));
		phones.add(new TelePhone("Galaxy TabA", "Samsung", 213.56));
		
		/**
		 * Utilizzeremo il metodo findFirst della classe Stream
		 * che ritorna un  oggetto Optional nel caso trovi qualcosa oppure 
		 * null nel caso non si riesca a trovare niente 
		 * e con il metodo orElse nel caso sia null gli passiamo il valore nuovo
		 * cioè TelePhone("Win10", "Nokia", 356.32)
		 */
		Optional<TelePhone> found =  phones.stream().filter((s) -> s.getMark().equals("Nokia")).findFirst();
		System.out.println(found.orElse(new TelePhone("Win10", "Nokia", 356.32)));//nel caso sia null gli passiamo un nuovo valore
		
		/**
		 *  Nel caso l'oggetto Optional filtrato (es - Sony Experia) non ci sia, 
		 *  creo un oggetto <T> tramite Factory-Supplier passato al metodo orElseGet(Supplier)
		 *  
		 */
		Optional<TelePhone> sonyFound = phones.stream().filter(
				(s) -> s.getName().equals("Experia")).findFirst();
		TelePhone sonyExperia = sonyFound.orElseGet(() -> new TelePhone("Experia", "Sony", 347.70));
		System.out.println(sonyExperia);
	}

}

class TelePhone{
	private String name;
	private String mark;
	private double price;
	
	public TelePhone(String name, String mark, double price) {
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
	
	public Collection<TelePhone> getPhone(){
		Collection<TelePhone> phone = new ArrayList<>();
		phone.add(this);
		return phone;
	}
}