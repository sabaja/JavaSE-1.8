package com.java_IO;

import java.io.File;
import java.io.FileInputStream;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * 
 * @author sabaja Si possono serializzare solo oggetti che non sono dinamici e
 *         implementano l'interfaccia serializable. Nel caso si voglia
 *         serializzare una classe derivata da un'altra classe, la prima
 *         superClasse non serializzabile deve avere un costruttore senza
 *         parametri es:
 * 
 *         Person class costruttore(nome, cognome, etc...) (no construttore
 *         senza parametri e non serializzabile) -
 * 
 * 
 *         Quindi da Person class derivo una classe con un costruttore senza
 *         parametri come la seguente ConstructNoParamPersonNoSerializable
 *         extends Person CostruttoreSenzaParametri()
 * 
 *         SerializablePerson extends ConstructNoParamPersonNoSerializable
 *         implements Serializable Adesso posso implementare Serializable
 * 
 *         Se non si fosse fatto cosi la JVM avrebbe dato un errore runtime di
 *         tipo: Java.io.InvalidClassException : No Valid Construct Di seguito
 *         il grafico
 * 
 *         Person class costruttore(nome, cognome, etc...) | | | V
 *         ConstructNoParamPersonNoSerializable extends Person
 *         CostruttoreSenzaParametri() | | | V SerializablePerson extends
 *         ConstructNoParamPersonNoSerializable implements Serializable
 */

public class Serializzazione {
	public static void main(String[] args) {
		Person obj = new Person("Ja", "Sa", 1.1D);
		Serialization.serial(obj);
		DeSerialization.deser(obj);
	}

}

enum Path {
	DIR("/home/sabaja/Scrivania/Dev-space/Input_Ouput");
	private String dir;

	private Path(String dir) {
		this.setDir(dir);
	}

	public String getDir() {
		return dir;
	}

	public void setDir(String dir) {
		this.dir = dir;
	}
}

class Serialization<E extends Serializable> {

	public static <E> void serial(E obj) {

		try (FileOutputStream fis = new FileOutputStream(
				new File(Path.DIR.getDir(), File.separator + "Object.serialization"));
				ObjectOutputStream os = new ObjectOutputStream(fis);) {
			os.writeObject(obj);
			os.flush();
			os.close();
			System.out.println(obj.getClass().getSimpleName() + " serialized!");
		} catch (IOException e) {

			e.printStackTrace();
		}
	}
}

class DeSerialization<E extends Serializable> {
	public static <E> void deser(E obj) {
		try (FileInputStream fis = new FileInputStream(
				new File(Path.DIR.getDir(), File.separator + "Object.serialization"));
				ObjectInputStream oos = new ObjectInputStream(fis);) {
			@SuppressWarnings({ "unchecked", "unused" })
			E p = (E) oos.readObject();
			System.out.println(obj.getClass().getSimpleName() + " deserialized!");
		} catch (IOException | ClassNotFoundException e) {

			e.printStackTrace();
		}
	}
}

class Person implements Serializable {
	/**
	 * UId for Serialization
	 */
	private static final long serialVersionUID = 7045306416374070961L;
	private String Name;
	private String Surname;
	// transient obbligatorio altrimenti si ottiene una
	// NotSerializationException poichè thread non è serializzabile
	private transient Thread thread;

	// Non voglio serialiazzare un id e allora lo segno con transient
	private transient double serialId;

	public Person(String name, String surname, double serialId) {
		super();
		Name = name;
		Surname = surname;
		this.serialId = serialId;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getSurname() {
		return Surname;
	}

	public void setSurname(String surname) {
		Surname = surname;
	}

	public double getSerialId() {
		return serialId;
	}

	public void setSerialId(double serialId) {
		this.serialId = serialId;
	}

	public Thread getThread() {
		return thread;
	}

	@Override
	public String toString() {
		return "Person [Name=" + Name + ", Surname=" + Surname + ", serialId=" + serialId + "]";
	}

}