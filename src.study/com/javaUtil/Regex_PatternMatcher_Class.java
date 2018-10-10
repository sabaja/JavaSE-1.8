package com.javaUtil;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Regex_PatternMatcher_Class {

	public static void main(String[] args) {
		PatternClass.printExempale();
	}

}

class PatternClass {

	static void printExempale() {
		Pattern p = Pattern.compile("a*b");
		Matcher m = p.matcher("aaaaab");
		boolean b = m.matches();
		System.out.println(b);

		boolean c = Pattern.matches("a*b", "aaaaab");
		System.out.println(c);

		String regex = "[ol]";// [ie]&&[o*]^\\s
		Pattern pat = Pattern.compile(regex);
		Matcher mat = pat.matcher("Colori e colore");
		String append = "";
		while (mat.find()) {
			int start = mat.start();
			int end = mat.end();
			append += mat.group();

			System.out.println(start + " " + end + " " + append + " " + mat.group());
		}
	}
}