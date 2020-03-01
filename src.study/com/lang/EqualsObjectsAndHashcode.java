package com.lang;

/* Name of the class has to be "Main" only if the class is public. */
class EqualsObjectsAndHashcode {
	public static void main(String[] args) throws java.lang.Exception {
		Punt<Integer> p1 = new Punt<>(2, 5);
		Punt<Double> p2 = new Punt<>(3.4, 5D);
		Punt<Double> p3 = new Punt<>(3.4, 5D);
		System.out.println(p1.equals(p1));
		System.out.println(p3.equals(p2));
		System.out.println(p1.hashCode());
		System.out.println(p2.hashCode());
		System.out.println(p3.hashCode());
	}
}

class Punt<T extends Number> {
	private T a;
	public T getA() {
		return a;
	}

	public T getB() {
		return b;
	}

	private T b;

	public Punt(T a, T b) {
		this.a = a;
		this.b = b;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((a == null) ? 0 : a.hashCode());
		result = prime * result + ((b == null) ? 0 : b.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Punt<?> other = (Punt<?>) obj;
        if (a == null) {
            if (other.a != null)
                return false;
        } else if (!a.equals(other.a))
            return false;
        if (b == null) {
            return other.b == null;
        } else return b.equals(other.b);
    }


}