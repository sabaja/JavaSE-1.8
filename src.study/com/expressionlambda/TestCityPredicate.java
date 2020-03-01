package com.expressionlambda;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class TestCityPredicate {

    public static void main(String[] args) {
        List<City> cities = createCity();
        System.out.println(getFilteredCity(cities, (city) -> city.isSeaCity()));

        cities = createCity();
        System.out.println(getFilteredCity(cities, (city) -> city.isChiefTown()));

        //Oppure con le reference ai metodi:
        cities = createCity();
        System.out.println(getFilteredCity(cities, TestCityPredicate::isSeaCity));

        cities = createCity();
        System.out.println(getFilteredCity(cities, TestCityPredicate::isChiefTown));

        //Uso di consumer
        cities = createCity();
        printDetails(cities, (city) -> System.out.println(city.getName()
                + (city.isChiefTown() ? " è capoluogo, " : "")
                + (city.isSeaCity() ? " è città di mare" : "")));

    }

    private static List<City> createCity() {
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

    public static List<City> getFilteredCity(List<City> city, Predicate<City> p) {
        Iterator<City> i = city.iterator();
        while (i.hasNext()) {
            City temp = i.next();
            if (!p.test(temp)) {
                i.remove();
            }
        }
        return city;
    }

    public static boolean isSeaCity(City city) {
        return city.isSeaCity();
    }

    public static boolean isChiefTown(City city) {
        return city.isChiefTown();
    }

    public static void printDetails(List<City> lsCity, Consumer<City> c) {
        for (City ic : lsCity) {
            c.accept(ic);
        }
    }

}