package com.javaTimeAPI;

import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import io.reactivex.functions.Consumer;

public class FamilyBirthday {

	private LocalDate born;
	private Family familyElement;

	private enum Family {
		ELENA, JACOPO, ANDREA, GAIA;
	};

	public FamilyBirthday() {

	}

	public FamilyBirthday(Family familyElement, LocalDate born) {
		super();
		this.born = born;
		this.familyElement = familyElement;
	}

	public static List<String> getFamilyList() {
		String[] familyArr = new String[Family.values().length];
		for (Family ele : Family.values()) {
			familyArr[ele.ordinal()] = ele.toString();
		}
		return Arrays.asList(familyArr);
	}

	public boolean isBirthdayDay(Family familyElement, TemporalAccessor date) {
		final int month = date.get(ChronoField.MONTH_OF_YEAR);
		final int day = date.get(ChronoField.DAY_OF_MONTH);

		for (Family ele : Family.values()) {
			switch (ele) {
			case ELENA:
				if (Month.OCTOBER.getValue() == month && (31 == day)) {
					return true;
				}
				break;
			case JACOPO:
				if (Month.SEPTEMBER.getValue() == month && (17 == day)) {
					return true;
				}
				break;
			case ANDREA:
				if (Month.MARCH.getValue() == month && (19 == day)) {
					return true;
				}
				break;
			case GAIA:
				if (Month.JANUARY.getValue() == month && (0_7 == day)) {
					return true;
				}
				break;
			}
		}
		return false;
	}

	public LocalDate getBorn() {
		return born;
	}

	public Family getFamilyElement() {
		return familyElement;
	}

	public void setFamilyElement(Family familyElement) {
		this.familyElement = familyElement;
	}

	@Override
	public String toString() {
		return "FamilyBirthday [born=" + born + ", familyElement=" + familyElement + "]";
	}

	public static int compareByAge(LocalDate born1, LocalDate born2) {
		return born1.compareTo(born2);
	}

	public long daysFromBorn() {
		return ChronoUnit.DAYS.between(born, LocalDate.now());
	}

	public static void main(String[] args) throws Exception {
		FamilyBirthday familyBirthday = new FamilyBirthday();
		Consumer<Boolean> c = System.out::println;
		c.accept(familyBirthday.isBirthdayDay(Family.ANDREA, LocalDate.of(2008, Month.MARCH.getValue(), 19)));
//		FamilyBirthday[] family = new FamilyBirthday[] {new FamilyBirthday(Family.ANDREA,LocalDate.of(2008, Month.MARCH.getValue(), 19)};
		FamilyBirthday andrea = new FamilyBirthday(Family.ANDREA, LocalDate.of(2008, Month.MARCH.getValue(), 19));
		FamilyBirthday gaia = new FamilyBirthday(Family.GAIA, LocalDate.of(2013, Month.JANUARY.getValue(), 07));
		FamilyBirthday elena = new FamilyBirthday(Family.ELENA, LocalDate.of(1974, Month.OCTOBER.getValue(), 31));
		FamilyBirthday jacopo = new FamilyBirthday(Family.JACOPO, LocalDate.of(1976, Month.SEPTEMBER.getValue(), 17));
		List<FamilyBirthday> listBirthday = Arrays.asList(andrea, gaia, elena, jacopo);
		Collections.sort(listBirthday, new FamilyAgeComparator());
		listBirthday.forEach(
				f -> System.out.printf("%d giorni passati dalla nascita di %s %n", f.daysFromBorn(), f.familyElement));
//		for (FamilyBirthday f : listBirthday) {
//			System.out.printf("%d giorni passati dalla nascita di %s %n", f.daysFromBorn(), f.familyElement);
//		}
		
		List<String> family = getFamilyList();
		Random rand = new Random();
		int index = rand.nextInt(family.size());
		System.out.println("Sorpresa! Ecco a voi: "  + family.get(index));
	}

}

class FamilyAgeComparator implements Comparator<FamilyBirthday> {

	@Override
	public int compare(FamilyBirthday o1, FamilyBirthday o2) {
		return o1.getBorn().compareTo(o2.getBorn());
	}

}