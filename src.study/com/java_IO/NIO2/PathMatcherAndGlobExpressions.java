package com.java_IO.NIO2;

import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.Paths;

/**
 * 
 * @author sabaja
 * Un oggetto di tipo PathMatcher è ottenibile invocando il metodo 
 * getPathMatcher di un oggetto di tipo FileSystem , 
 * al quale si passa come argomento una stringa indicante la sintassi 
 * del motore di ricerca di corrispondenze da usare ( glob o regex ) 
 * e il pattern per la comparazione separati dal carattere di due punti. 
 * Successivamente, sull’oggetto così ottenuto, si invoca il metodo 
 * matches , che ritorna un valore booleano che indica se l’argomento 
 * passato di tipo Path corrisponde al pattern di ricerca.
 */
public class PathMatcherAndGlobExpressions {

	public static void main(String[] args) {
		Path path = Paths.get("C:\\MY_JAVA_SOURCES\\TestScientist.java"); 
		Path path_2 = Paths.get("TestScientist.class"); 
		FileSystem fs = FileSystems.getDefault(); 
		PathMatcher matcher = fs.getPathMatcher("glob:*.java"); 
		matcher.matches(path); // false 
		
		//tipo di syntax("glob, "regex") syntax:pattern
		matcher = fs.getPathMatcher("glob:**.java"); 
		matcher.matches(path); // true - è stato percorso tutto il path 
		matcher = fs.getPathMatcher("glob:????.class"); 
		matcher.matches(path_2); // true - esattamente 4 caratteri prima di .class
		matcher = fs.getPathMatcher("glob:*.{class,java}" ); 
		matcher.matches(path_2); // true - il nome termina con .class o .java
		
		// true - il nome inizia con T, ha una delle lettere a, b, e oppure f, ha la lettera s
		// ha esattamente un qualsiasi carattere, termina con .class 
		matcher = fs.getPathMatcher("glob:T[abef]s?.class"); 
		matcher.matches(path_2);
	}

}
