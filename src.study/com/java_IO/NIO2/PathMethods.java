package com.java_IO.NIO2;

import java.nio.file.*;

/**
 * 
 * @author sabaja
 *	L'interfaccia Path rappresenta il percorso di un fileSystem
 *	Paths è la classe di utilità
 *  This class consists exclusively of static methods that return a Path by converting a path string or URI.
 */
public class PathMethods {
    public static void main(String args[]) {
    	//metodo classico per ottenere il path
        Path pathToDesktop = Paths.get("/home/sabaja/Scrivania");
        //metodo tramite il FileSystems 
        Path path = FileSystems.getDefault().getPath("/home/sabaja/Scrivania");
        
        Path pathToDocuments = Paths.get("/home/sabaja/Scrivania/workspace");
        System.out.println("toString: " + pathToDesktop.toString());//rappresenta il percorso
        System.out.println("getFileName: " + pathToDesktop.getFileName());//prende il nome della cartella(linux file)
        
        
        //ritorna il nome dell'elemento indicato dall'indice 0 è il file root
        System.out.println("getName(0): " + pathToDesktop.getName(0));
        //ritorna il nume degli elementi nel path
        System.out.println("getNameCount: " + pathToDesktop.getNameCount());
        
        //ritorna il path indicato dagli indici subpath(0,2): home/sabaja
        System.out.println("subpath(0,2): " + pathToDesktop.subpath(0,2));
        System.out.println("getRoot: " + pathToDesktop.getRoot());
        
        //ritorna il file genitore
        System.out.println("getParent: " + pathToDesktop.getParent());
        //ritorna il path in formato URI Uniform Resource Identifier
        System.out.println("toUri: " + pathToDesktop.toUri());
        
        //ritorna la distanza tra il path corrente e il path passato come argomento
        System.out.println("path from p1 to p2: " + pathToDesktop.relativize(pathToDocuments));
        System.out.println("path from p2 to p1: " +  pathToDocuments.relativize(pathToDesktop));
        
        System.out.println("pathToDesktop.equals(pathToDocuments): " + pathToDesktop.equals(pathToDocuments));
        //Ritorna un boolean se il nodo radice è uguale a quello indicato
        System.out.println("pathToDesktop.startsWith: " +  pathToDesktop.startsWith(pathToDocuments.subpath(0,2)));
        System.out.println("pathToDesktop.endsWith: " + pathToDesktop.endsWith(pathToDocuments.subpath(0,2)));
    }
}