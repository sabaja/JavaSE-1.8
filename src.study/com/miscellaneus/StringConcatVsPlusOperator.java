package com.miscellaneus;

import java.time.Instant;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class StringConcatVsPlusOperator {

	public static void main(String[] args) {
		String a = "Inizio";
		String c = a;
		LocalTime t2 = LocalTime.now();
		for (long i = 0; i < 100000L; i++) {
			c += "\n"; // make sure javac cannot skip the loop
			// using c += b for the alternative
		}
		LocalTime t3 = LocalTime.now();		
		
		LocalTime t = LocalTime.now();
		for (long i = 0; i < 100000L; i++) {
			c = c.concat(Long.toString(i).concat("\n")); // make sure javac
															// cannot skip the
															// loop
			// using c += b for the alternative
		}
		LocalTime t1 = LocalTime.now();
		
		ChronoUnit u = ChronoUnit.MILLIS;
		System.out.println("concact elapsed milliseconds: " + u.between(t, t1));

		a = "inizio";
		c = a;
		ChronoUnit u2 = ChronoUnit.MILLIS;
		System.out.println("+ elapsed milliseconds: " + u2.between(t2, t3));

	}
	

}
