package com.miscellaneus;

import java.util.ArrayList;
import java.util.List;
import java.util.EnumSet;
import java.util.Set;

class FunctionalInterfaceAndAnonymousClass {
	public Set<?> sexSet = EnumSet.allOf(SEX.class);
	
	static public void main(String []args){
		List<Person> persons = new ArrayList<>();
		persons.add(new Person("Jacopo", 39,SEX.MALE));
		persons.add(new Person("Andre", 8,SEX.MALE));
		persons.add(new Person("Elena",41,SEX.FEMALE));
		persons.add(new Person("Gaia",3,SEX.FEMALE));
		persons.add(new Person("Francesca",42,SEX.FEMALE));
		persons.add(new Person("Marta",30,SEX.FEMALE));
		persons.add(new Person("Marcella",35,SEX.FEMALE));
		
		ServiceForPerson.getPerson(persons, new ListForPerson() {
			
			@Override
			public boolean test(Person p) {
				return p.getSex() == SEX.FEMALE.getName() && p.getName().contains("ar");
			}
		});
	}
}


class ServiceForPerson{
	public static void getPerson(List<Person> list, ListForPerson p){
		for(Person person : list){
			if(p.test(person)){
				System.out.println(person.toString());
			}
		}
	}
}

interface ListForPerson{
	public boolean test(Person p); 
}

enum SEX{
	MALE("male"), FEMALE("female");
	String name;
	
	private SEX(String name){
		SEX.this.name = name;	
	}
	public String getName(){
		return name;
	}
};


class Person{
	private String name;
	private int age;
	private String sex;
	public Person(String name, int age, SEX sex) {
		this.name = name;
		this.age = age;
		this.sex = sex.getName();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Override 
	public String toString(){
		return this.name + " " + this.age + " " + this.sex + " "; 
	}
	
}
