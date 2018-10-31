package com.rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * https://www.html.it/articoli/rmi-metodi-a-distanza/
 */
public class RMISquareRootServer extends UnicastRemoteObject implements IsSquareRoot {

	private static final long serialVersionUID = -8400749423201084809L;

	protected RMISquareRootServer() throws RemoteException {
		super();
	}

	@Override
	public double calucateSquareRoot(double factor) throws RemoteException {
		return Math.sqrt(factor);
	}

	public static void main(String[] args) {
		try {
			IsSquareRoot server = new RMISquareRootServer();
			Naming.rebind("//localhost/RMISquareRoot", server);
			System.out.println("Start");
		} catch (RemoteException | MalformedURLException e) {
			System.out.println(e.getMessage());
		}
	}
}
