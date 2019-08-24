package com.annotations.codility;

public class TestScientist {

	public static void main(String[] args) throws Exception {
//		int[] arr = new int[60_000];
//		for (int i = 0; i < 60_000; i++) {
//			arr[i] = 2;
//		}

		int[] arr = { -1, 1, 3, 3, 3, 2, 3, 2, 1, 0 };
		TestScientist t = new TestScientist();
		System.out.println("\nperiods: " + t.solution(arr));

	}

	public static int solution(String S) {
		final int ZERO_CONST = 0;
		if (null == S || "".equals(S))
			return ZERO_CONST;

		int temp, c = ZERO_CONST;
		final int num = Integer.parseInt(S, 2);
		if (num == ZERO_CONST) {
			return ZERO_CONST;
		}

		temp = num;
		while (temp >= 1) {
			if (temp % 2 == 0) {
				temp /= 2;
			} else {
				temp -= 1;
			}
			c++;
		}
		return c;
	}

	public int solution(int[] arr) throws Exception {
		System.out.print("arr:\t");
		print(arr);
		System.out.println();

		if (arr.length < 3)
			return -1;

		final int DIFF_LEN = arr.length - 1;
		int[] diff = new int[DIFF_LEN]; // difference are
		int[] binaryArr = new int[DIFF_LEN - 1];
		int[] binaryRecurrence = new int[DIFF_LEN / 2];

		// 1 - Start - Finding the difference between the positions of the particle
		for (int i = 0; i < DIFF_LEN; i++) {
			diff[i] = arr[i] - arr[i + 1];
		}

		System.out.print("diff:\t");
		print(diff);
		System.out.println();

		// 2 - Analysis of diff array - checking if the velocity of the particle is
		// constant.
		// I put 1 in an another array if the consecutive
		// values are equals: diff[i] == diff[i + 1], similar to something like a
		// roll-call
		for (int i = 0; i < DIFF_LEN - 1; i++) {
			if (diff[i] == diff[i + 1]) {
				binaryArr[i] = 1;
			}
		}
		System.out.print("bin:\t");

		print(binaryArr);
		System.out.println();

		// 3 - checking if period is contained in an another one.. I gather all the
		// consecutive 1s and summed them up
		int count = 0;
		binaryRecurrence[count] = 1;
		for (int i = 0; i < binaryArr.length - 1; i++) {
			if (binaryArr[i] == binaryArr[i + 1] && binaryArr[i] == 1) {
				binaryRecurrence[count] += binaryArr[i];
			} else if (binaryArr[i] == 1) {
				binaryRecurrence[count] += binaryArr[i];
				count++;
			} else {
				// count++;
				continue;
			}
		}

		System.out.print("binary recurrence:\t");
		print(binaryRecurrence);
		int n = 0;

		/* 
		 	summation formula: 
			∞ 
			∑n = n(n+1)/2
			n=1
		*/
		for (int ele : binaryRecurrence) {
			if (ele > 0) {
				final int max = ele;
				n += max * (1 + max) / 2;
		
		// 	below doesn't work				
		//	if (exceeds >= 1_000_000_000) {
		//		return -1;
		//	}
			}
		}
		return n;
	}

	public void print(int[] A) {
		for (int i = 0; i < A.length; i++) {
			System.out.printf("%d ", A[i]);
		}
	}
}
