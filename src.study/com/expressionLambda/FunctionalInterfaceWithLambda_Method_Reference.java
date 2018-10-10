package com.expressionLambda;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class FunctionalInterfaceWithLambda_Method_Reference {

	public static void main(String[] args) {
		User_2.printFilms();
		User_3.orderingAndPrintFilmByName();
		User_3.orderingAndPrintFilmbyReview();
		User_Guitar.buildingGuitarNewReference();
		User_Guitar.buildingGuitarPreJava8();
	}
}

class Film_2 {
	private String name;
	private String gender;
	private int review;

	public Film_2(String name, String gender, int review) {
		super();
		this.name = name;
		this.gender = gender;
		this.review = review;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getReview() {
		return review;
	}

	public void setReview(int review) {
		this.review = review;
	}

	@Override
	public String toString() {
		return "Film [name=" + name + ", gender=" + gender + ", review=" + review + "]";
	}

}


@FunctionalInterface
interface FilterForFilm_2 {
	public boolean filter(Film_2 film);
}


class Filters{
	public static boolean isGoodFilms(Film_2 film){
		return film.getReview() > 3;
	}
	
	public static boolean isScienceFilms(Film_2 film){
		return film.getGender().equalsIgnoreCase("science fiction");
	}
	
	public static boolean isInner_S_films(Film_2 film){
		return film.getName().contains("s");
	}
}


//Qui usiamo la static method reference
class User_2 {
	public static void printFilms() {
		VideoLibrary_2 library = new VideoLibrary_2();
		Film_2[] goodFilms = library.getFileteredFilm(Filters::isGoodFilms); //Static Method Reference

		Film_2[] scienceFilms = library.getFileteredFilm(Filters::isScienceFilms);
		
		System.out.println("Good films: ");
		for(Film_2 f : goodFilms){
			if(f != null)
				System.out.println(f);
		}
		
		System.out.println("Science Films sd: ");
		for(Film_2 f : scienceFilms){
			if(f != null)
				System.out.println(f);
		}
	}
}

class VideoLibrary_2 {
	private static Film_2[] film;

	public VideoLibrary_2() {
		super();
		film = new Film_2[10];
		setVideoLibrary();
	}

	public Film_2[] getFilm() {
		return film;
	}

	public void setFilm(Film_2[] film) {
		film = film;
	}

	public Film_2[] getFileteredFilm(FilterForFilm_2 filterFilm) {
		Film_2[] filteredFilms = new Film_2[10];

		for (int i = 0, j = 0; i < 10; i++) {
			if (filterFilm.filter(film[i])) {
				filteredFilms[j++] = film[i];
			}
		}
		return filteredFilms;
	}

	public static void setVideoLibrary() {
		film[0] = new Film_2("Master of the rings", "Fantasy", 3);
		film[1] = new Film_2("Croods", "Cartoons", 4);
		film[2] = new Film_2("Star Wars", "Science fiction", 4);
		film[3] = new Film_2("Platoons", "War", 3);
		film[4] = new Film_2("One milion dollar man", "Tragedy", 4);
		film[5] = new Film_2("Farts", "Commedy", 2);
		film[6] = new Film_2("Savrat", "Fantasy", 5);
		film[7] = new Film_2("The big Lebosky", "Commedy", 4);
		film[8] = new Film_2("Antasia", "Fantasy", 2);
		film[9] = new Film_2("Interstellar", "Science fiction", 5);
	}
}






//Reference to an instance reference of an object Example
class OrderingFilm{
	
	public int orederingFilmByName(Film_2 film1, Film_2 film2){
		return film1.getName().compareTo(film2.getName());
	}
	
	public int orderingFilmByReviewDesc(Film_2 film1, Film_2 film2){
		Integer rew1 = film1.getReview();
		Integer rew2 = film2.getReview();
		return -(rew1.compareTo(rew2));
	}
	
	public int orderingFilmByReviewAsc(Film_2 film1, Film_2 film2){
		Integer rew1 = film1.getReview();
		Integer rew2 = film2.getReview();
		return rew1.compareTo(rew2);
	}

}

//Reference to an instance reference of an object Example
class User_3{
	
	public static void orderingAndPrintFilmByName(){
		VideoLibrary_2 library_2 = new VideoLibrary_2();
		Film_2[] films = library_2.getFilm();
		OrderingFilm orderingFilm = new OrderingFilm();
		Arrays.sort(films, orderingFilm::orederingFilmByName);//reference to an instance of an object
		System.out.println("Films ordered by name:");
		for(Film_2 f : films){
			if(f != null){
				System.out.println(f.toString());
			}
		}
	}
	
	public static void orderingAndPrintFilmbyReview(){
		VideoLibrary_2 library_2 = new VideoLibrary_2();
		Film_2[] films = library_2.getFilm();
		OrderingFilm orderingFilm = new OrderingFilm();
		Arrays.sort(films, orderingFilm::orderingFilmByReviewDesc); //reference to an instance of an object
		System.out.println("Films ordered by review:");
		for(Film_2 f : films){
			if(f != null){
				System.out.println(f.toString());
			}
		}
	}
}

/**
 * Reference to an instance method of a certain TYPE
 * Usiamo con una lista tipizzata che il compilatore è in grado di
 * dedurre in automatico il tipo 
 */
class User_4{
	public void printOrderedCollection(){
		List<String> list = new ArrayList<>();
		VideoLibrary_2 library_2 = new VideoLibrary_2();
		Film_2 []film_2 = library_2.getFilm();
		for(Film_2 f : film_2){
			list.add(f.getName());
		}
		//Deduzione automatica anche se il metodo d'ordinamento non è statico
		//Reference to an instance method of a certain Type 
		Collections.sort(list, String::compareToIgnoreCase);
	}
	 
}

/**
 * Reference a un costruttore con il factory pattern (costruttore di oggetti)
 */
class Guitar{
	private String mark;

	public Guitar(String mark) {
		super();
		this.mark = mark;
		System.out.println("Created a/an " + mark + " guitar");
	}
}
/**
 * interfaccia funzionale per creare una factory di chitarre
 * @author sabaja
 *
 */
@FunctionalInterface
interface BuildingGuitar{
	public Guitar getGuitar(String mark);
}

/**
 * prejava 8 Classe factory per implementare l'interfaccia
 */

@Deprecated
class BuildingGuitarFactory implements BuildingGuitar{

	@Override
	public Guitar getGuitar(String mark) {
		return new Guitar(mark);
	}
	
}

class User_Guitar{
	/**
	 * pre Java 8
	 */
	static public void buildingGuitarPreJava8(){
		BuildingGuitar buildingGuitar = new BuildingGuitarFactory();
		Guitar myGuitar = buildingGuitar.getGuitar("Fender");
	}
	
	static public void buildingGuitarNewReference(){
		//non usiamo + la classe factory ma il reference al costruttore della chitarra
		//poichè c'è un unico metodo che ritorna la chitarra
		BuildingGuitar buildingGuitar = Guitar::new;
		Guitar myGuitar = buildingGuitar.getGuitar("Gypson");
	}
	
}