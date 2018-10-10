package com.java.NET;

import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Collections;
import java.util.Enumeration;
/**
 * 
 * @author sabaja
 * API PER ACCESSO ALL'INTERFACCIA DI RETE
 *	Un’interfaccia di rete o NIC ( Network Interface Card ) è definibile come un punto di connessione tra un host e una rete 
 *	e può essere costituita da un dispositivo hardware, come la classica scheda di rete, 
 *	oppure da uno strato software che ne emula il funzionamento, 
 *	come l’interfaccia di loopback avente indirizzo 127.0.0.1 per IPv4 o ::1 per IPv6.
 */
public class NetworkInterfaceCard_interfaccia_tra_Host_e_Rete {
	public static void main(String args[]) {
		try {
			// dammi le NIC presenti
			Enumeration<NetworkInterface> nics = NetworkInterface.getNetworkInterfaces();
			if (nics != null) {
				while (nics.hasMoreElements()) {
					NetworkInterface nic = nics.nextElement();
					System.out.println("Nome NIC: " + nic.getDisplayName() + "( " + nic.getName() + " ) Inet Address: " + nic.getInetAddresses().nextElement().getHostAddress());
					Enumeration<NetworkInterface> sub_nics = nic.getSubInterfaces(); // ha
																						// delle
																						// sottointerfacce?
					if (sub_nics.hasMoreElements()) {
						for (NetworkInterface sub_nic : Collections.list(sub_nics))
							System.out.println(
									" \tNome SUB-NIC: " + sub_nic.getDisplayName() + "( " + sub_nic.getName() + " )");
					} else
						System.out.println("Non sono presenti sub-interfacce!");
				}
			} else
				System.out.println("Nessuna interfaccia di rete trovata!");
		} catch (SocketException ex) {
			System.out.println(ex.getMessage());
		}
	}
	
/**
 * output:
 * 
 * Nome NIC: wlp3s0( wlp3s0 )
 * Non sono presenti sub-interfacce!
 * Nome NIC: lo( lo )
 * Non sono presenti sub-interfacce!
 */
}
