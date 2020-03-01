package com.time;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalQueries;
import java.time.temporal.TemporalQuery;

public class DateUtils {

	/**
	 * Metodo restituisce il numero di: secondi, minuti, ore, giorni, 
	 * settimane, mesi che intercorrono tra due istanti specificati.
	 */
	public static Long getDurationByType(Temporal start, Temporal end, ChronoUnit t){
		return t.between(start, end);
	}
	
	/**
	 * Metodo che dato un istante nel passato capace di restituire il numero di
	 * secondi, minuti, ore, giorni, settimane, mesi che intercorrono 
	 * fino ad adesso.
	*/
	public static Long getDurationFromNow(Instant start, ChronoUnit unit){
		return getDurationByType(start, Instant.now(), unit);
	}
	
	/**
	 * Metodo che stampa l'Ora esatta in formato HH:mm ss
	 */
	public static String getTime(){
		LocalTime time = LocalTime.now();
		return time.getHour() + 
			   ":" + time.getMinute() +
			   " " + time.getSecond();
	}
	
	/**
	 * metodo che formatta la data specificata secondo
	 * il pattern specificato in input. 
	 * il metodo potrebbe lanciare l’eccezione  IllegalArgumentException
	 * nel caso si specifichi un pattern errato.
	 */
	public static String getFormatDate(LocalDateTime dateTime, String format) throws IllegalArgumentException{
		String formatDate = "";
		try{
			formatDate = dateTime.format(DateTimeFormatter.ofPattern(format));
		}
		catch(IllegalArgumentException ex){
			ex.printStackTrace();
			throw new IllegalArgumentException(); 
		}
		return formatDate;
	}

	/**
	 * metodo che formatta la data specificata secondo
	 * il formato DateTimeFormatter passato in input. 
	 * il metodo potrebbe lanciare l’eccezione  IllegalArgumentException
	 * nel caso si specifichi un pattern errato.
	 */
	public static String getFormatDate(LocalDateTime dateTime, DateTimeFormatter format) throws IllegalArgumentException{
		String formatDate = null;
		try{
			formatDate = dateTime.format(format);
		}
		catch(IllegalArgumentException ex){
			ex.printStackTrace();
			throw new IllegalArgumentException(); 
		}
		return formatDate;
	}
	
	/**
	* metodo che analizzi la data specificata secondo
	* il pattern specificato in input.
	*/
	public static String queryDate(String data, String pattern) throws DateTimeParseException {
		LocalDate localDate = null;
		String result = null;
		try {
			localDate = LocalDate.parse(data, DateTimeFormatter.ofPattern(pattern));
			TemporalQuery<?> queries = TemporalQueries.precision();
			result = localDate.query(queries).toString();
		} catch (DateTimeParseException dateTimeParseException) {
			dateTimeParseException.printStackTrace();
			throw dateTimeParseException;
		}
		return result;
	}
	
	/**
	 * Metodo per fare il parse di un {@link DateTimeFormatter} 
	 * passato come argomento 
	 */
	
	public LocalDate parseDate (DateTimeFormatter formatter){
		LocalDate d = LocalDate.now();
		String text = d.format(formatter);
		LocalDate parsedDate = LocalDate.parse(text, formatter);
		return parsedDate;
	}
}

