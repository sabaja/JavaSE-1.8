/**
Supponiamo di trovarci allo sportello del comune che rilascia le carte d’identità.
Supponiamo che ci siano 10 richiedenti in fila pronti per richiedere il documento.
Quando arriva il proprio turno, verrà consegnato al richiedente un modulo da
compilare. Per non bloccare la fila, il richiedente successivo potrà nel frattempo
richiedere il servizio allo stesso sportello. A tutti i richiedenti verrà consegnato il
modulo da compilare e quindi la fila sarò molto veloce, e in parallelo diverse persone
saranno occupate a compilare il modulo. Ogni richiedente potrebbe metterci
un tempo variabile da 1 a 10 secondi per compilare il modello, il primo che finirà
(indipendentemente dalla sua posizione nella fila iniziale) potrà richiedere la stampa
della propria carta d’identità. Questa verrà stampata sempre in 3 secondi.
Anche in questo caso usare delle stampe significative per rendere auto esplicativa
l’esecuzione del programma.
*/

package com.multithreadsExercises;

import java.util.Random;

public class ConsegnaCartaIdentitaTest {

	public static void main(String[] args) {
		final Person[] person = setPerson();
		for(Person p : person){
			p.start();
		}

	}

	static Person[] setPerson() {
		Person[] persons = { 
				new Person("Marta", Person.SEX.FEMALE),
				new Person("Sara", Person.SEX.FEMALE),
				new Person("Marco", Person.SEX.MALE),
				new Person("Luis", Person.SEX.MALE),
				new Person("Francy", Person.SEX.FEMALE),
				new Person("Elena", Person.SEX.FEMALE),
				new Person("Gaia", Person.SEX.FEMALE),
				new Person("Andrea", Person.SEX.MALE),
				new Person("Frank", Person.SEX.MALE),
				new Person("Slat", Person.SEX.FEMALE)
				};
		return persons;

	}

}

class TimeUtils {
	private static int wait;

	public static int getWait() {
		Random random = new Random();
		wait = random.nextInt(10);
		return wait;
	}
}

class Printer {

	private static Printer instance;

	public static Printer getInstance() {
		if (instance == null) {
			instance = new Printer();
		}
		return instance;
	}

	public synchronized void print(Person person) {
		System.out.println("Print in progess..");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("End! " + person.getMyName() + "'s ID card is ready");
	}
}

class Person extends Thread {

	private String name;
	SEX gender;

	public enum SEX {
		MALE, FEMALE
	};
	// Municipality

	public Person(String name, SEX gender) {
		super();
		this.name = name;
		this.gender = gender;
		System.out.print(name + " is waiting in queue for ");
		System.out.println(gender == SEX.MALE ? "his" : "her" + " Id Card");
	}

	@Override
	public void run() {
		CityHall.getInstance().manageDocument(this);
	}

	public String getMyName() {
		return name;
	}

	public void setMyName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + "]";
	}

}

class CityHall {
	private static CityHall instance;
	private Printer printer;

	private CityHall() {
		printer = Printer.getInstance();
	}

	public synchronized static CityHall getInstance() {
		if (instance == null) {
			instance = new CityHall();
		}
		return instance;
	}

	public synchronized void manageDocument(Person person) {
		System.out.print("Good mornig ");
		System.out.print(person.gender == Person.SEX.MALE ? "Mr" : "Miss");
		System.out.println(" " + person.getMyName());
		System.out.println("Please, compile this module");
		compileModule(person);
		printer.print(person);
	}

	private synchronized void compileModule(Person person) {
		final int wait = TimeUtils.getWait();
		System.out.println(person.getMyName() + " says: \"Ok thanks, I'll take " + wait + " minutes\"");
		try {
			Thread.sleep(wait * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(person.getMyName() + " says: \"Done!!!\"");
	}
}