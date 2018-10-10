package com.java_IO.NIO2;


import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class ScriveretramiteFile_bufferedWriter {
    public static void main(String args[]) {
        Charset charset = Charset.forName("UTF-8");
        String contenutoDelFile = "Ciao";
        Path path = Paths.get("/home/sabaja/Scrivania/Dev-space/Input_Ouput/ScriveretramiteFile_bufferedWriter");
        try (BufferedWriter writer = Files.newBufferedWriter(path, charset)) {
            writer.write(contenutoDelFile, 0, contenutoDelFile.length());
        } catch (IOException x) {
            System.err.format("IOException: %s%n", x);
        }
    }
	
    /**
     * Metodo per ritornare il contenuto di un file dentro una lista
     * utilizzando files
     */
    public static List<String> getWordsList() throws IOException{
           String contents = new String(Files.readAllBytes(
           Paths.get("/home/sabaja/Scrivania/Dev-space/Java/Manuale_JSE_8/Appendici/appendici/modulo_16/esempi/la-divina-commedia.txt")), StandardCharsets.UTF_8);
           List<String> words = Arrays.asList(contents.split(" "));
           return words;
       }
    
}
