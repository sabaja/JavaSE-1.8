package com.io;

import java.io.*;

/**
 * 
 * @author sabaja public Stream<String> lines() Returns a Stream, the elements
 *         of which are lines read from this BufferedReader. The Stream is
 *         lazily populated, i.e., read only occurs during the terminal stream
 *         operation.
 * 
 *         The reader must not be operated on during the execution of the
 *         terminal stream operation. Otherwise, the result of the terminal
 *         stream operation is undefined.
 * 
 *         After execution of the terminal stream operation there are no
 *         guarantees that the reader will be at a specific position from which
 *         to read the next character or line.
 * 
 *         If an IOException is thrown when accessing the underlying
 *         BufferedReader, it is wrapped in an UncheckedIOException which will
 *         be thrown from the Stream method that caused the read to take place.
 *         This method will return a Stream if invoked on a BufferedReader that
 *         is closed. Any operation on that stream that requires reading from
 *         the BufferedReader after it is closed, will cause an
 *         UncheckedIOException to be thrown.
 */

public class BufferedReader_GeneraunoggettoSTREAM {
	public static void main(String[] args) throws IOException {
		try (BufferedReader br = new BufferedReader(
				new FileReader("/home/sabaja/Scrivania/Dev-space/Input_Ouput/BuffTraialin.txt"));
				BufferedWriter bw = new BufferedWriter(
						new FileWriter("/home/sabaja/Scrivania/Dev-space/Input_Ouput/BuffTraialout.txt"))) {
			int c;
			Long count = 0L;
			String str = "";
			while (true) {
				str = br.readLine();
				count = br.lines().filter(s -> s.contains("dinosauri")).count();
				if (str == null)
					break;
			}
			System.out.println(count);
		}

	}
}
