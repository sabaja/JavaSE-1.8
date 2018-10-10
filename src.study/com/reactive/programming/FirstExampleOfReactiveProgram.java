package com.reactive.programming;

import java.util.Arrays;
import java.util.List;

import io.reactivex.Observable;

/**
 * 
 * @author sabaja
 *
 */
public class FirstExampleOfReactiveProgram {

	private static List<String> words = Arrays.asList("the", "quick", "brown", "fox", "jumped", "over", "the", "lazy", "dog");
	
	public static void main(String[] args) {
		example1();
		example2();
		exampleWithZipWith();
	}

	/**
	 * https://dzone.com/articles/5-things-to-know-about-reactive-programming
	 */
	public static void example1() {
		Observable<Integer> obs1 = Observable.fromArray(new Integer[] { 1, 4 });
		Observable<Integer> obs2 = Observable.fromArray(new Integer[] { 3, 5 });
		Observable.merge(obs1, obs2).filter(i -> i <= 4).map(i -> i + 1).subscribe(x -> System.out.println(x));
	}

	/**
	 * https://www.infoq.com/articles/rxjava-by-example?utm_source=twitter&utm_medium=link&utm_campaign=calendar
	 */
	public static void example2() {
		Observable<String> just = Observable.just("just 1!", "just 2!");
		just.subscribe(System.out::println);
		
		Observable.just(words).subscribe(System.out::println);
	}
	
	/**
	 * https://www.infoq.com/articles/rxjava-by-example?utm_source=twitter&utm_medium=link&utm_campaign=calendar
	 * Unisce uno stream esistente con un'altro
	 * 
	 */
	public static void exampleWithZipWith(){
		Observable.just(words)
		.flatMap(word -> Observable.just(word.toString().split(" ")))
		.zipWith(Observable.range(1, Integer.MAX_VALUE), 
				(string, count)->String.format("%2d. %s", count, string)).subscribe(System.out::println);
	}
}
