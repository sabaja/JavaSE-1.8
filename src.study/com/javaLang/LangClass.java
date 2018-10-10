package com.javaLang;

import java.util.*;

/* Name of the class has to be "Main" only if the class is public. */
class LangClass {
	public static void main(String[] args) throws java.lang.Exception {

		EInterface abc = ABC.A;
		abc.printIndex();
		System.out.println(abc.getIndex());
		System.out.println(abc);
		String name = "giorgio";
		String tmp = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
		System.out.println(tmp);
		System.out.println(tmp.equalsIgnoreCase(name));
		System.out.println(tmp.indexOf('o'));
		System.out.println(String.format("Giorgio%d", tmp.indexOf(111)));
		List<String> list = Arrays.asList("Java", "is", "more simple", "than", "C++");
		System.out.println(String.join("- o -", list).lastIndexOf(111));
		byte[] arr = "Armando".getBytes();
		int len = arr.length;
		for (int i = 0; i < len ; ++i)
		{
			byte b =  (byte) (("Armando".codePointAt(i))<<1);
			System.out.print(b + " " + (char)b + " ");
			System.out.println("byte: " + arr[i] + "\tchar:\t" + "Armando".charAt(i) + "\tunicode\t" );
		}
		String str = String.join("-", "Giorgio", "Armando", null, "Sara");
		System.out.println(str);
		String str1 = 
			(
				str.substring
				(
						str.toLowerCase().indexOf("-A".toLowerCase(), 5)
				)
			).replace("-null-", "-");
		
		System.out.println(str1);
		List<String> string1 = new LinkedList<>();
		string1.add("Java");
		string1.add("is");
		string1.add("cool");
		System.out.println(String.join(" ", string1));
		// message returned is: "Java is cool"

		Set<String> string2 = new LinkedHashSet<>();
		string2.add("Java");
		string2.add("is");
		string2.add("very");
		string2.add("cool");
		System.out.println(String.join("-", string2));
		// message returned is: "Java-is-very-cool"
		
		String str11 = "hh";
		System.out.println(str11.getClass().getSimpleName());
		str11 += "ll";
		str11 = str.toUpperCase();
		System.out.println(str11);
		System.out.println(str11.intern());
	}
};

enum ABC implements EInterface {
	A, B, C;

	@Override
	public void printIndex() {
		System.out.print(this.ordinal());
	}

	@Override
	public int getIndex() {
		return this.ordinal();
	}

};

interface EInterface // functional Interface
{
	public void printIndex();

	public int getIndex();
};