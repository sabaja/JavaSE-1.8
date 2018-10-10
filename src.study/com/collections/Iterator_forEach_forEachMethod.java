package com.collections;


import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

public class Iterator_forEach_forEachMethod {

	public static void main(String[] args) {
		ForEACH f = new ForEACH();
		IteratorExample ie = new IteratorExample();
		ForEachMethod fem = new ForEachMethod();
	}

}

class ForEACH {
	public ForEACH() {
		// Costrutto for( a : arr ) da usare solo con collezioni piccole
		// e non si devono fare operazioni sugli elementi
		System.out.println("for(a : arr) example: ");
		Collection<String> progBands = new HashSet<>();
		progBands.add("Yes");
		progBands.add("PFM");
		progBands.add("Giganti");
		progBands.add("Gaggi");
		for (String a : progBands)
			System.out.println(a);
		System.out.println();
	}
}
/**
 * 
 * Iterator<?>
 * ha 4 metodi:
 * 		boolean	hasNext() - serve per iterare sull'oggetto collection 
 * 							e ritorna true se non è all'ultimo elemento. 
 * 
 * 		E next() - produce un oggetto dello stesso tipo della collection
 * 
 * 
 * 		default void remove() - rimuove l'oggetto alla iesima posizione 
 * 								e è thread safe
 * 
 * 		default void forEachRemaining(Consumer<? super E> action)
 * 		- esegue l'operazione indicata da Consumer per tutti gli elementi

 * 				
 */
class IteratorExample{

	public IteratorExample() {
		super();
		System.out.println("Iterator Example: ");
		Collection<String> progBands  = new HashSet<>();
		progBands.add("Yes");
		progBands.add("PFM");
		progBands.add("Giganti");
		progBands.add("Gaggi");
		progBands.add("Aeyrons");
		Iterator<String> iterator = progBands.iterator();
		System.out.println("Iterator appartiene alla classe " + iterator.getClass().getName());
		//esempio di remove
		while(iterator.hasNext()){
			if(iterator.next().equals("Aeyrons")){
				iterator.remove();
			}
		}
		System.out.println(progBands);
		Collection<Phone> phones = new HashSet<>();
		phones.add(new Phone("Iphone", "Apple"));
		phones.add(new Phone("One", "Htc"));
		phones.add(new Phone("Galaxy Note", "Samsung"));
		Iterator<Phone> i = phones.iterator();
		System.out.println(phones);
		while(i.hasNext()){
			i.forEachRemaining((element)->element.setMark("NULL"));
		}
		System.out.println(phones);
		System.out.println();
	}
	
}
//classe esempio per: default void forEachRemaining(Consumer<? super E> action)
class Phone{
	private String name;
	private String mark;
	
	public Phone(String name, String mark) {
		super();
		this.name = name;
		this.mark = mark;
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

	@Override
	public String toString() {
		return "SmartPhone [name=" + name + ", mark=" + mark + "]";
	}
	
}

//Uso delle lambda per il metodo forEach:
//default void	forEach(Consumer<? super T> action)
//opera la determinata azione su tutti gli elementi della collezione fino alla 
//fine oppure per un'eccezione
class ForEachMethod{
	public ForEachMethod() {
		super();
		System.out.println("ForEachMethod: ");
		Collection<Phone> phones = new HashSet<>();
		phones.add(new Phone("Iphone", "Apple"));
		phones.add(new Phone("One", "Htc"));
		phones.add(new Phone("Galaxy Note", "Samsung"));
		phones.forEach((s)->System.out.println(s));
		System.out.println();
	}
	
}