package com.time;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Locale;


public class Standardizzazione_Dei_Metodi_Time_API {
	@SuppressWarnings({ "null", "unused" })
	public static void main(String[] args){ 
		//Now:
		//restituisce un'istanza della classe su cui è chiamato
		//nel momento della chiamata
		LocalDate now = LocalDate.now();

		//From:
		//Restituisce un'istanza della classe su cui è chiamato
		//a partire dal parametro in input che contiene + informazioni
		//ciò implica una perdita di dati.
		//now rispetto a yearMonth contiene il giorno
		YearMonth ym = YearMonth.from(now);

		//OF:
		//Restituisce un'istanza della classe su cui è chiamato
		//dalla formattazione passata in input
		LocalDate myBirthday = LocalDate.of(1976, Month.SEPTEMBER /*oppure 9*/, 17);
		
		//With
		//Restituisce una copia dell'oggetto chiamato con un elemento cambiato
		//è equivalente a SET per un oggetto immutabile
//		DateTimeFormatter df = null;
//		df.withLocale(Locale.ENGLISH);
		
		//Plus
		//Restituisce una copia dell'oggetto chiamato con un elemento in più
		Instant inTenMinutes = Instant.now().plus(10,ChronoUnit.MINUTES);
		
		//Minus
		//Restituisce una copia dell'oggetto chiamato con un elemento in meno
		Instant tenMintuesEgo = Instant.now().minus(10, ChronoUnit.MINUTES);
		
		//Get
		//Ritorna una parte dei valori del primo parametro specificato
		DayOfWeek dayOfWeek = myBirthday.getDayOfWeek();
		
		//Is
		//ritorna lo stato dell'oggetto specificato
		boolean isLeap = Year.isLeap(2016);
		
		//To 
		//trasforma l'oggetto in un'altro
		
		//At
		//Restituisce un nuovo oggetto ottenuto aggiungendo le informazioni 
		//specificate all'oggetto corrente
		//ese_1
		LocalDate lcd = LocalDate.ofEpochDay(System.currentTimeMillis());
		LocalDateTime ldt = lcd.atTime(LocalTime.now(ZoneId.of("America/New York")));
		//ese_2
		LocalDateTime toDay = LocalDateTime.now(); 
		ZonedDateTime zdt = toDay.atZone(ZoneId.of("America/LosAngels"));
		
		//Parse
		//Analizza la stringa in input e genera l'istanza di data in base all'iso
		LocalTime lt = LocalTime.parse("hh:mm:ss", DateTimeFormatter.ISO_LOCAL_TIME);
		
		//Format
		//ritorna una stringa formattata usando il formatter 
		//specificato con l'oggetto temporal
		String timeFormatted = LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE.withLocale(Locale.CHINA));
		System.out.println(timeFormatted);
	}
}
