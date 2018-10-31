package com.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * https://www.html.it/articoli/rmi-metodi-a-distanza/
 * @author sabaja
 *
 */
public interface IsSquareRoot extends Remote {
	public double calucateSquareRoot(final double factor)throws RemoteException;
}
