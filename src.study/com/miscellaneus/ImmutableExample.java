package com.miscellaneus;

import java.lang.reflect.ParameterizedType;
import java.time.LocalDateTime;
import java.util.Objects;

public class ImmutableExample {

	public static void main(String[] args) {
		Immutable<Double> i1 = new Immutable<>();
		Immutable<Double> i2 = new Immutable<>("oo", 1L, LocalDateTime.now());
		System.out.println("i1 " + i1);
		Immutable<Double> i3 = i2.add(i1);
		System.out.println("i3 " + i3);
		System.out.println("i2 " + i2);

		 PincoPallo p = new PincoPallo(12.0D);
		 System.out.println(p.getInstanceClass());
	}

}

class Immutable<T> {
	private final String name;
	private final Long id;
	private final LocalDateTime createDateTime;
	private final Class<T> instanceClass;

	public Immutable() {
		this.name = "";
		this.id = 0L;
		createDateTime = LocalDateTime.now();
		instanceClass = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass())
				.getActualTypeArguments()[1];
	}

	public Immutable(String name, Long id, LocalDateTime createDateTime) {
		super();
		this.name = name;
		this.id = id;
		this.createDateTime = createDateTime;
		instanceClass = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass())
				.getActualTypeArguments()[1];
	}

	public Immutable<T> add(Immutable<T> immutable) throws NullPointerException {
		if (Objects.isNull(immutable)) {
			throw new NullPointerException();
		}
		return new Immutable<T>(name, id, createDateTime);
	}

	public String getName() {
		return name;
	}

	public Long getId() {
		return id;
	}

	public LocalDateTime getCreateDateTime() {
		return createDateTime;
	}

	@Override
	public String toString() {
		return "Immutable [name=" + name + ", id=" + id + ", createDateTime=" + createDateTime + "]";
	}

	public Class<T> getInstanceClass() {
		return instanceClass;
	}

}

class PincoPallo extends Immutable<Double> {
	private Double index;

	public PincoPallo(Double index) {
		super();
		this.index = index;
	}

	public Double getIndex() {
		return index;
	}

	public void setIndex(Double index) {
		this.index = index;
	}

	@Override
	public String toString() {
		return "PincoPallo [index=" + index + "]";
	}

	
}
