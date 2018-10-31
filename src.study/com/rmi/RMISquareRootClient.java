package com.rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * https://www.html.it/articoli/rmi-metodi-a-distanza/
 * 
 * @author sabaja
 *
 */
public class RMISquareRootClient {

	public static void main(String[] args) {
		double num = 23D;
		try {
			IsSquareRoot server = (IsSquareRoot) Naming.lookup("rmi://localhost/RMISquareRoot");
			double result = server.calucateSquareRoot(num);
			System.out.println(result);
		} catch (NotBoundException | RemoteException | MalformedURLException e) {
			System.out.println(e.getMessage());
		}
	}
}
