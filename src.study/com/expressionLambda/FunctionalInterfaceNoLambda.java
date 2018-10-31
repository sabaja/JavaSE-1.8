package com.expressionLambda;



public class FunctionalInterfaceNoLambda {
	
	public static void main(String []args){
		User.printFilms();
	}
}

class Film{
	private String name;
	private String gender;
	private int review;
	
	public Film(String name, String gender, int review) {
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
interface FilterForFilm{
	public boolean filter(Film film);
}

class VideoLibrary{
	private static Film []film;
	
	public VideoLibrary(){
		super();
		film = new Film[10];
		setVideoLibrary();
	}

	public Film[] getFilm() {
		return film;
	}

	public void setFilm(Film[] film) {
		VideoLibrary.film = film;
	}
	
	public Film[] getFileteredFilm(FilterForFilm filterFilm){
		Film []filteredFilms = new Film[10];
		
		for(int i = 0, j = 0 ; i < 10 ; i++){
			if(filterFilm.filter(film[i])){
				filteredFilms[j++] = film[i];
			}
		}
		return filteredFilms;
	}
	
	public static void setVideoLibrary(){
		film[0] = new Film("Master of the rings", "Fantasy", 3);
		film[1] = new Film("Croods", "Cartoons", 4);
		film[2] = new Film("Star Wars", "Science fiction", 4);
		film[3] = new Film("Platoons", "War", 3);
		film[4] = new Film("Sucks sluts", "Porno", 4);
		film[5] = new Film("Farts", "Commedy", 2);
		film[6] = new Film("Savrat", "Fantasy", 5);
		film[7] = new Film("The big Lebosky", "Commedy", 4);
		film[8] = new Film("Porcodio", "Fantasy", 5);
		film[9] = new Film("Intruder in her anal", "Porno", 3);
	}
}

class User{
	public static void printFilms() {
		VideoLibrary library = new VideoLibrary();
		Film[] goodFilms = library.getFileteredFilm(new FilterForFilm() {

			@Override
			public boolean filter(Film film) {
				return film.getReview() > 3;
			}
		});

		Film[] scienceFilms = library.getFileteredFilm(new FilterForFilm() {

			@Override
			public boolean filter(Film film) {

				return "Science Fiction".equalsIgnoreCase(film.getGender());
			}
		});
		
		System.out.println("Good films: ");
		for(Film f : goodFilms){
			if(f != null)
				System.out.println(f);
		}
		
		System.out.println("Science Films: ");
		for(Film f : scienceFilms){
			if(f != null)
				System.out.println(f);
		}
	}
	
}