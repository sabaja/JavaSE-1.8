package com.lang;

public class RawArray {

	public static void main(String[] args) {
		int[] arr = new int[3];
		arr[0] = 0;
		arr[1] = 1;
		for (int i = 0; i < arr.length; i++) {
			System.out.println(arr[i]);
		}

		System.out.println("----");

		String[][] strArr = { { "0Str0", "0Str1" }, { "1str0" }, { "2s0", "2s1", "2s3" } };
		final int I_LEN = strArr.length;
		for (int i = 0; i < I_LEN; i++) {
			final int Y_LEN = strArr[i].length;
			for (int y = 0; y < strArr[i].length; y++) {
				System.out.print(y < Y_LEN - 1 ? strArr[i][y] + " - " : strArr[i][y] + "\n");
			}
		}
	}
}
