package com.miscellaneus;

import org.checkerframework.checker.nullness.qual.NonNull;

public class azz {
	public static void main(String args[]){
		System.out.println(new String("sbtjcp76p17l219i").toUpperCase());
		System.out.println(new String("IT80S0329601601000067037713").length());
		System.out.println(new String("IT97M0558401799000000093357").length());
		try {
			Class.forName("jd").newInstance();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
