package com.expressionLambda;

import java.io.IOException;

public class ComeOvviareEffectiveFinal_wrappingValue_InAnArry {

	public static void main(String[] args) {
		int count = 0;
		int[] arr = new int[1]; // si usa un array di una posizione dove
								// modifico il suo valore
		arr[0] = count;
		new Thread(() -> {
			while (arr[0] < 100) {
				try {
					Thread.sleep(0);
				} catch (Exception e) {
					e.printStackTrace();
				}
				if (arr[0] == 99) {
					System.out.println(arr[0]);
					System.exit(1);//altrimenti entra in loop visto che il valore in questo punto è sempre 99
				} else {
					System.out.println(arr[0]++);
					System.out.println("\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n"
							+ "\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n"
							+ "\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n"
							+ "\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n");
				}
			}
		}).start();
	}
}
