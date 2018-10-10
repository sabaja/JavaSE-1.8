package com.expressionLambda;

import java.util.function.Predicate;

public class Predicate_Interface_Java_Util_Function_ {

	public static void main(String[] args) {
		 

	}

}
/**
 * 
 * Vedi la classe FunctionalInterfaceWithLambda_Method_Reference
 * doce c'è il metodo:
 * public Film_2[] getFileteredFilm(FilterForFilm_2 filterFilm)
 * questo si può riscrivere così:
 * public Film_2[] getFileteredFilm(Predicate<Film_2> filterFilm) 
 * Predicate ha un metodo (SAM): 
 * boolean test(T t);
 * 
 */
class PredicateExample{
	//Uso predicate al posto dell'interfaccia creata apposta FilterForFilm_2
	public static Film_2[] getFileteredFilm(Predicate<Film_2> filterFilm){
		VideoLibrary_2 vl = new VideoLibrary_2();
		Film_2[] film = vl.getFilm();
		Film_2[] filteredFilms = new Film_2[10];

		for (int i = 0, j = 0; i < 10; i++) {
			if (filterFilm.test(film[i])) {//riscritto con test di predicate 
				filteredFilms[j++] = film[i];
			}
		}
		return filteredFilms;
	}
}