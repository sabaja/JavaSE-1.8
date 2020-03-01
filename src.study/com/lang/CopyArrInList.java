package com.lang;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* Name of the class has to be "Main" only if the class is public. */
class MyClass {
	private List<String> list = new ArrayList<>();

	public MyClass() {

		list.add("1");
		list.add("2");
		list.add("3");
	}


	public <T extends String> void copyArr(List<T> dest, List<? extends T> src) {
		if (dest != null)
			dest.clear();
		dest.addAll(src);
	}

	public void printArr(List<?> src) {
		if (src != null) {
			Iterator<?> i = src.iterator();
			while (i.hasNext())
				System.out.println(i.next());

		}
	}

	public List<String> getList() {
		return this.list;
	}
}

class CopyArrInList {

	public static void main(String[] args) throws java.lang.Exception {
		MyClass id = new MyClass();
		List<String> ls = new ArrayList<>();
		id.copyArr(ls, id.getList());
		id.printArr(id.getList());
		id.printArr(ls);
	}
}