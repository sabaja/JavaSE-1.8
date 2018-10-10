package com.generics;

class GenericMethodsWithCompare {
	public static void main(String[] args) {
		Couple<Integer, String> p1 = new Couple<>(1, "pear");
		Couple<Integer, String> p2 = new Couple<>(1, "pear");
		boolean same1 = Util.<Integer, String>compare(p1, p2);

		Couple<Integer, String> p3 = new Couple<>(1, "apple");
		Couple<Integer, String> p4 = new Couple<>(2, "pear");
		//compiler automatic infers the type
		boolean same2 = Util.compare(p3, p4);

		System.out.println(same1 + " " + same2);
	}
}

class Util {
	public static <K, V> boolean compare(Couple<K, V> p1, Couple<K, V> p2) {
		return p1.getKey().equals(p2.getKey()) && p1.getValue().equals(p2.getValue());
	}
}

class Couple<K, V> {

	private K key;
	private V value;

	public Couple(K key, V value) {
		this.key = key;
		this.value = value;
	}

	public void setKey(K key) {
		this.key = key;
	}

	public void setValue(V value) {
		this.value = value;
	}

	public K getKey() {
		return key;
	}

	public V getValue() {
		return value;
	}
}