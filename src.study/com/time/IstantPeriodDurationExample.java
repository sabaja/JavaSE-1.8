package com.time;

import java.time.*;
import java.time.temporal.ChronoUnit;

public class IstantPeriodDurationExample {

	public static void main(String[] args) {

		Instant timestamp = Instant.now();
		System.out.println("Instant now: " + timestamp + " is after Epoch (" + Instant.EPOCH + ")"
				+ timestamp.isAfter(Instant.EPOCH) + " is after Instant.MAX (" + Instant.MAX + ")"
				+ timestamp.isAfter(Instant.MAX));

		// Zone Russia
		ZonedDateTime timeMosk = timestamp.atZone(ZoneId.of("Europe/Moscow"));
		System.out.println(timeMosk);

		Instant timeEpoch = Instant.ofEpochMilli(timestamp.toEpochMilli());
		System.out.println("milliseconds " + timeEpoch);

		// Chico giorni dalla nascita
		Instant andreaBorn = Instant.parse("2008-03-19T08:10:00.00Z");
		long days = andreaBorn.until(Instant.now(), ChronoUnit.DAYS);
		System.out.print("Andrea giorni: " + days);
		Period ap = Period.between(LocalDate.of(2008, 3, 19), LocalDate.now());
		System.out.print(" anni " + ap.getYears() + " mesi " + ap.getMonths());

		Duration gaiaBorn = Duration.between(Instant.parse("2013-01-07T03:45:00.00Z"), Instant.now());
		System.out.print("\nGaia giorni: " + gaiaBorn.toDays());// solo duration
																// dal il tempo
																// macchina
		System.out.print(" mesi " + (Period.between(LocalDate.of(2013, 01, 07), LocalDate.now())).getMonths() + " anni "
				+ (Period.between(LocalDate.of(2013, 01, 07), LocalDate.now())).getYears());

		Instant jacopoBorn = Instant.parse("1976-09-17T00:00:00Z");
		System.out.println("\nPapà giorni: " + jacopoBorn.until(Instant.now(), ChronoUnit.DAYS));

		Duration elenaBorn = Duration.between(Instant.parse("1974-10-31T00:00:00Z"), Instant.now());
		System.out.println("Mamma giorni: " + elenaBorn.toDays());

	}
}
