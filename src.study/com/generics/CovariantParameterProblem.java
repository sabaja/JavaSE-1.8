package com.generics;

//https://play.google.com/books/reader?id=z15ODwAAQBAJ&printsec=frontcover&output=reader&hl=it&pg=GBS.PT367.w.16.3.0
public class CovariantParameterProblem {

	public static void main(String[] args) {
		Animale<Herbivore<Herb>> tigre = new Carnivorous<>();
		Herbivore<Herb> erbivoro = new Herbivore<>();
		tigre.mangia(erbivoro);
		erbivoro.mangia(new Herb());
	}

}

interface Cibo {
	public String getColore();
}

interface Animale<E extends Cibo> {
	public void mangia(E cibo);
}

class Herb implements Cibo {
	{
		System.out.println(this.toString());
	}

	@Override
	public String getColore() {
		return "green";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Herb [color = " + getColore() + "]";
	}

}

class Herbivore<E extends Herb> implements Cibo, Animale<E> {

	@Override
	public String getColore() {
		return "green";
	}

	@Override
	public void mangia(E cibo) {
		System.out.println("Mangia " + cibo);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Herbivore [color =" + getColore() + "]";
	}

}

class Carnivorous<E extends Herbivore<Herb>> implements Animale<E> {

	@Override
	public void mangia(E cibo) {
		System.out.println("Mangia " + cibo);
	}

}