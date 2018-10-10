package com.javaUtil;

import java.util.Calendar;
import java.util.Formatter;
import java.util.GregorianCalendar;
import java.util.Locale;

public class FormatterClass {

	public static void main(String[] args) {
		StringBuilder sb = new StringBuilder();
		// Send all output to the Appendable object sb
		Formatter formatter = new Formatter(sb, Locale.US);
		// Explicit argument indices may be used to re-order output.
		System.out.println(formatter.format("%4$2s %3$2s %2$2s %1$2s", "a", "b", "c", "d"));
		// -> " d c b a"

		// Optional locale as the first argument can be used to get
		// locale-specific formatting of numbers. The  precision and width can be
		// given to round and align the value.
		System.out.println(formatter.format(Locale.FRANCE, "\nMath.E = %+11.4f", Math.E));
		// -> "e = +2,7183"
		formatter.close();

		Formatter f = new Formatter(System.out);
		f.format(Locale.US, "Math.E = %+.10f", Math.E);

		Calendar c = new GregorianCalendar(1995, Calendar.MAY, 02);
		f.format("\nDuke's Birthday: %1$tB %1$te, %1$tY", c);
		
		String s = String.format(Locale.CANADA_FRENCH, "\nDuke's Birthday in France: %1$tY/%1$tm/%1$td ", c);
		System.out.println(s);
		
		s = String.format
		(
			Locale.CHINESE, "\nDuke's Birthday in China: \n\t%1$tY/%1$tB/%1$tA - %td " + 
					String.format(Locale.UK ,"\n\t%1$tY/%1$tB/%1$tA - %te" 
							+ String.format(Locale.ITALY ,"\n\t%1$tY/%1$tB/%1$tA - %td", c)
					, c)
			, c);
		System.out.println(s);
		
		Object[] o = { "1", 2.09141414D, "3;" };
		System.out.printf(Locale.ENGLISH, "%s\u0020%+-2.7f\u0020%s", o[0], o[1], o[2]);// " " \u0020
																						 

	}

}