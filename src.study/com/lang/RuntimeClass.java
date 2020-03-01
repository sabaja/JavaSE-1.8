package com.lang;

public class RuntimeClass {

	public static void main(String[] args) {
		Runtime r = Runtime.getRuntime();
		String command = "pwd";
		try{
			r.exec(command);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		System.out.println(r.freeMemory() + "\n" + r.totalMemory() + "\n" + r.maxMemory());

	}

}
