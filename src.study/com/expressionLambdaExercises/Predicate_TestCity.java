package com.expressionLambdaExercises;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class Predicate_TestCity {

	public static void main(String[] args) {
		List<City> cities = getCity();
		System.out.println(getFilteredCity(cities, (city)->city.isSeaCity()));
		
		cities = getCity(); 
		System.out.println(getFilteredCity(cities, (city)->city.isChiefTown()));
		
		//Oppure con le reference ai metodi:
		cities = getCity(); 
		System.out.println(getFilteredCity(cities, Predicate_TestCity::isSeaCity));
		
		cities = getCity(); 
		System.out.println(getFilteredCity(cities, Predicate_TestCity::isChiefTown));
		
		//Uso di consumer
		cities = getCity();
		printDetails(cities, (city)-> System.out.println(city.getName()
				+ (city.isChiefTown() ? " è capoluogo, " : "")
				+ (city.isSeaCity() ? " è città di mare" : "")));

	}
	
	private static List<City> getCity(){
		List<City> city = new ArrayList<>();
		city.add(new City("Milano", true, false));
		city.add(new City("Rovigo", false, false));
		city.add(new City("Potenza", true, false));
		city.add(new City("Siracusa", false, true));
		city.add(new City("Perugia", true, false));
		city.add(new City("Napoli", true, true));
		city.add(new City("Pescara", false, true));
		city.add(new City("Taranto", false, true));
		city.add(new City("Siena", false, false));
		return city;
	}
	
	public static List<City> getFilteredCity(List<City> city, Predicate<City> p){
		Iterator<City> i = city.iterator();
		while(i.hasNext()){
			City temp = i.next();
			if(!p.test(temp)){
				i.remove();
			}
		}
		return city;
	}

	public static boolean isSeaCity(City city){
		return city.isSeaCity();
	}
	
	public static boolean isChiefTown(City city){
		return city.isChiefTown();
	}
	
	public static void printDetails(List<City> lsCity, Consumer<City> c){
		for(City ic : lsCity){
			c.accept(ic);
		}		
	}
	
}

class City{
	private String name;
	private boolean seaCity;
	private boolean chiefTown;
	
	public City(String name, boolean chiefTown, boolean seaCity) {
		super();
		this.name = name;
		this.seaCity = seaCity;
		this.chiefTown = chiefTown;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isSeaCity() {
		return seaCity;
	}

	public void setSeaCity(boolean seaCity) {
		this.seaCity = seaCity;
	}

	public boolean isChiefTown() {
		return chiefTown;
	}

	public void setChiefTown(boolean chiefTown) {
		this.chiefTown = chiefTown;
	}

	@Override
	public String toString() {
		return "City [name=" + name + ", seaCity=" + seaCity + ", chiefTown=" + chiefTown + "]";
	}
	
}