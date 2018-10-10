package com.javaLang;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class String_UtilityMethods {

	public static void main(String[] args) {

		// Reference
		String str = new String("Prova");
		// Come primitivi
		String str2 = "Prova";
		String str3 = str2;

		System.out.println(str == str2);
		System.out.println(str2 == str3);
		System.out.println(str.equals(str2));

		System.out.println(str.charAt(4));

		System.out.println("Sei un babbo ".concat(str3));
		LocalDate date = LocalDate.now();
		DateTimeFormatter format = DateTimeFormatter.ofPattern("dd LLLL yyyy");

		System.out.println(String.format("stringa = %s, date = %s", "erba secca patate Stringa", date.format(format)));

		System.out.println("indice carattere unicode :".indexOf(0X0061) + 1);
		System.out.println("indice carattere".indexOf("a") + 1);

		System.out.println(String.join("/", "etc", "apt", "sources.list.d", ""));
		List<String> ls = Arrays.asList("Lista", "di stringhe", "con", "il metodo", "join");
		// OVERLOAD DEL METODO JOIN CON OGGETTO CHE IMPLEMENTA INTERFACCIA
		// ITERABLE
		System.out.println(String.join(" ", ls));
		System.out.println(str.replace("Pr", "tr") + " " + str);

		LocalTime l = LocalTime.now(ZoneId.systemDefault());
		System.out.println("Non lancia eccezione " + String.valueOf(l));
	}

}
