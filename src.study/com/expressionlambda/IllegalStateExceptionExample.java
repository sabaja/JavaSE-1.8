package com.expressionlambda;

import reactor.core.publisher.Flux;

import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * preso da https://stackify.com/reactive-spring-5/ soluzione per
 * IllegalStateExceptionExample:
 * http://www.baeldung.com/java-stream-operated-upon-or-closed-exception
 */
public class IllegalStateExceptionExample {
	
	private enum Prova {
		A(1), B(2), C(3);
		private Integer i;

		Prova(Integer i) {
			this.i = i;
		}

		/**
		 * @return the i
		 */
		public Integer getI() {
			return i;
		}

		/**
		 * @param i the i to set
		 */
		public void setI(Integer i) {
			this.i = i;
		}
		
		
	}

	public static void main(String[] args) {

		// questo funziona correttamente per la gestione del backpressure degli
		// oggetti Flux and Mono che utilizzano streams riutilizzabili
		System.out.println("Reactive stream");
		Flux<Integer> j = Flux.just(1, 2, 3, 4, 5);
		j.map(i -> i * 10).subscribe(ele -> System.out.printf("%s ", ele));
		System.out.println();
		j.map(i -> i + 5).subscribe(ele -> System.out.printf("%s ", ele));

		// Non funziona
		// A Stream should be operated on (invoking an intermediate or terminal
		// stream operation) only once
		System.out.println("\n\nJava stream that throws IllegalStateExceptionExample: ");

		try {
			Stream<Integer> num = Stream.of(1, 2, 3, 4, 5);
			num.map(i -> i * 10).forEach(ele -> System.out.printf("%s ", ele));
			System.out.println();
			num.map(i -> i + 5).forEach(ele -> System.out.printf("%s ", ele));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		System.out.println("\nJava stream with Supplier suppport: ");
		// Soluzione:
		Supplier<Stream<Integer>> streamSupplier = () -> Stream.of(1, 2, 3, 4, 5);
		streamSupplier.get().map(i -> i * 10).forEach(ele -> System.out.printf("%s ", ele));
		System.out.println();
		streamSupplier.get().map(i -> i + 5).forEach(ele -> System.out.printf("%s ", ele));
		System.out.println();

		for(Prova pr : Prova.values()){
			System.out.printf("%s %d\t", pr.name(),  pr.getI());
		}
	}

}
