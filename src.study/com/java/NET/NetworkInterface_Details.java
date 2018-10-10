package com.java.NET;


import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.List;

public class NetworkInterface_Details {

	public static void main(String[] args) {
		try {
			// ritorna la NIC con il nome indicato
			NetworkInterface nic = NetworkInterface.getByName("wlp3s0");
			if (nic != null) {
				System.out.println("Nome NIC: " + nic.getDisplayName() + "\nMaximum Transmission Unit (MTU) " + nic.getMTU());
				byte ott[] = nic.getHardwareAddress(); // MAC address (inidirizzo della scheda di rete)
				if (ott != null)
					System.out.format("Mac address: %02X-%02X-%02X-%02X-%02X-%02X\n", ott[0], ott[1], ott[2], ott[3],
							ott[4], ott[5]);
				System.out.println("E' UP ? " + nic.isUp());
				System.out.println("E' una sub-interfaccia ? " + nic.isVirtual());//sub interface
				List<InterfaceAddress> ia = nic.getInterfaceAddresses(); // indirizzi associati alla NIC
				for (InterfaceAddress one_ia : ia) {
					InetAddress addr = one_ia.getAddress();//Inet Address (IP+HOSTNAME)
					String ip = addr.getHostAddress();
					int prefix = one_ia.getNetworkPrefixLength();
					boolean ipv4 = addr instanceof Inet4Address;
					System.out.println("IP: " + ip + "/" + prefix);
					if (ipv4) {
						String broadcast_addr = one_ia.getBroadcast().getHostAddress();
						System.out.println("Broadcast address: " + broadcast_addr);
					}
				}
			} else
				System.out.print("NIC non trovata!");
		} catch (SocketException ex) {
			System.out.println(ex.getMessage());
		}
	}
}
