package com.java_IO.ClientServer;

import java.net.*;
import java.io.*;

public class SimpleServer {
	private static ServerSocket s = null;

	public static void main(String args[]) throws IOException {
		// ServerSocket va chiuso
		try {
			s = new ServerSocket(9999);
			System.out.println("Server avviato, in ascolto sulla" + "porta 9999");
		} catch (IOException e) {
			e.printStackTrace();
		}

		while (true) {
			try {
				Socket s1 = s.accept();
				OutputStream s1out = s1.getOutputStream();
				BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(s1out));
				bw.write("Ciao client sono il server!");
				System.out.println("Messaggio spedito a " + s1.getInetAddress());
				bw.close();
				s1.close();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				s.close();
			}
		}

	}
}