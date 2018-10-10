package com.expressionLambda;

public class FunctionalInterfaceWithLambda {

	public static void main(String[] args) {
		User_1.printFilms();
	}
}

class Film_1 {
	private String name;
	private String gender;
	private int review;

	public Film_1(String name, String gender, int review) {
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
interface FilterForFilm_1 {
	public boolean filter(Film_1 film);
}

class VideoLibrary_1 {
	private static Film_1[] film;

	public VideoLibrary_1() {
		super();
		film = new Film_1[10];
		setVideoLibrary();
	}

	public Film_1[] getFilm() {
		return film;
	}

	public void setFilm(Film_1[] film) {
		film = film;
	}

	public Film_1[] getFileteredFilm(FilterForFilm_1 filterFilm) {
		Film_1[] filteredFilms = new Film_1[10];

		for (int i = 0, j = 0; i < 10; i++) {
			if (filterFilm.filter(film[i])) {
				filteredFilms[j++] = film[i];
			}
		}
		return filteredFilms;
	}

	public static void setVideoLibrary() {
		film[0] = new Film_1("Master of the rings", "Fantasy", 3);
		film[1] = new Film_1("Croods", "Cartoons", 4);
		film[2] = new Film_1("Star Wars", "Science fiction", 4);
		film[3] = new Film_1("Platoons", "War", 3);
		film[4] = new Film_1("Sucks sluts", "Porno", 4);
		film[5] = new Film_1("Farts", "Commedy", 2);
		film[6] = new Film_1("Savrat", "Fantasy", 5);
		film[7] = new Film_1("The big Lebosky", "Commedy", 4);
		film[8] = new Film_1("Porcodio", "Fantasy", 5);
		film[9] = new Film_1("Intruder in her anal", "Porno", 3);
	}
}

class User_1 {
	public static void printFilms() {
		VideoLibrary_1 library = new VideoLibrary_1();
		Film_1[] goodFilms = library.getFileteredFilm((film) -> film.getReview() > 3);//Lambda

		Film_1[] scienceFilms = library.getFileteredFilm((film) -> "Science Fiction".equalsIgnoreCase(film.getGender()));
		
		System.out.println("Good films: ");
		for(Film_1 f : goodFilms){
			if(f != null)
				System.out.println(f);
		}
		
		System.out.println("Science Films: ");
		for(Film_1 f : scienceFilms){
			if(f != null)
				System.out.println(f);
		}
	}
	
	
}