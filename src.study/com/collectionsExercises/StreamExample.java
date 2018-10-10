package com.collectionsExercises;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

public class StreamExample {
/**
 * 
 * @param args
 * Con uno stream stampare tutte le città di mare.
 * Con un secondo stream stampare tutte le città capoluogo.
 * Con un terzo stream stampare tutte le città che finiscono con la lettera ‘ a ’.
 */
	public static void main(String[] args) {
		Set<City> cities = getCity();
		cities.stream().filter(s -> s.isSea()).forEach(s -> System.out.println(s));//Stampa tutti luoghi di mare
		cities.stream().filter(s -> s.isChiefTown()).forEach(s -> System.out.print(s + " "));
		System.out.println("\n");
		cities.stream().filter(s -> s.getName().endsWith("a")).forEach(s -> System.out.print(s + " "));
	}
	
	public static Set<City> getCity(){
		Set<City> set = new HashSet<>();
		set.add(new City("Milano", true, false));
		set.add(new City("Rovigo", false, false));
		set.add(new City("Potenza", true, false));
		set.add(new City("Siracusa", false, true));
		set.add(new City("Perugia", true, false));
		set.add(new City("Napoli", true, true));
		set.add(new City("Pescara", false, true));
		set.add(new City("Taranto", false, true));
		set.add(new City("Siena", false, false));
		return set;
	}
}

class City {
	private String name;
	private boolean chiefTown;
	private boolean sea;
	
	


	public City(String name, boolean chiefTown, boolean sea) {
		this.name = name;
		this.chiefTown = chiefTown;
		this.sea = sea;
	}

	public boolean isSea() {
		return sea;
	}

	public void setSea(boolean sea) {
		this.sea = sea;
	}

	public boolean isChiefTown() {

		return chiefTown;
	}

	public void setChiefTown(boolean chiefTown) {
		this.chiefTown = chiefTown;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return getName();
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (chiefTown ? 1231 : 1237);
		result = prime * result + (sea ? 1231 : 1237);
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		City other = (City) obj;
		if (chiefTown != other.chiefTown)
			return false;
		if (sea != other.sea)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
}