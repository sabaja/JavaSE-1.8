package com.expressionLambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class MethodReference {

	public static void main(String[] args) {
		lambdaPrint("lamda");
		methodReferencePrint("reference");
		// to avoid unsupport method
		// https://stackoverflow.com/questions/7885573/remove-on-list-created-by-arrays-aslist-throws-unsupportedoperationexception
		List<String> list = new ArrayList<>(Arrays.asList("1", "2", "3"));
		methodReferenceList(list, new String("4"));
		System.out.println();
		checkAndAddElement(list, "5");

	}

	public static void lambdaPrint(String arg) {
		Consumer<String> c = s -> System.out.println(s);
		c.accept(arg);
	}

	public static void methodReferencePrint(String arg) {
		Consumer<String> c = System.out::println;
		c.accept(arg);
	}

	public static void methodReferenceList(List<String> list, String ele) {
		if (Objects.nonNull(ele) && Objects.nonNull(list)) {
			Consumer<String> c = list::add;
			c.accept(ele);
			list.forEach(MethodReference::methodReferencePrint);
		}
	}

	public static void checkAndAddElement(List<String> list, String ele) {
		final Integer minusOne = Integer.valueOf(ele) - 1;
		Optional<String> minusOneOptional = list.stream().filter(e -> e.equalsIgnoreCase(String.valueOf(minusOne)))
				.findAny();
		if (minusOneOptional.isPresent()) {
			methodReferenceList(list, ele);
		}
	}
	
}
