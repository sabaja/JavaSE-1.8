package com.annotations.codility;

public class Test_Sc {

	public int solution(int[] A) {
		/*
		 * int[] B = new int[1_000_001]; int min = 1; for (int number : A) { if (number
		 * > 0) { B[number] = 1; } }
		 */
		final int LEN_A = A.length;
		int period = 0;
		int[] result = new int[LEN_A-1];
		if (LEN_A <= 2)
			return 0;
		// carico il risultato
		for (int i = 0; i < LEN_A - 1; i++) {
			result[i] = (A[i]) - (A[i + 1]);
		}
		print(A);
		System.out.println("\t");
		print(result);
		int len_temp;
		boolean flag = false;
//		 Analizzo il risultato se fino a quando
//		 while(!flag || ) {
//			 
//			 for(int i = 0; )
//		 }

		return period;

	}

	public static void main(String[] args) {
		Test_Sc t = new Test_Sc();
		int[] A = { -1, 1, 3, 3, 3, 2, 3, 2, 0, 1 };
		t.solution(A);
	}

	public void print(int[] A) {
		for (int i = 0; i < A.length; i++) {
			System.out.printf("%d\n", A[i]);
		}
	}
}
