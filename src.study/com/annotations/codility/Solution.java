package com.annotations.codility;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class Solution {

	public int solution(String S) {
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

	// MissingInteger
	public int _solution(int[] A) {
		// write your code in Java SE 8
		int[] B = new int[1_000_001];
		int min = 1;
		for (int number : A) {
			if (number > 0) {
				B[number] = 1;
			}
		}
		for (int i = 1; i < B.length; i++) {
			if (B[i] == 0) {
				min = i;
				break;
			}
		}
		return min;
	}

	public void print(int[] A) {
		for (int i = 0; i < A.length; i++) {
			System.out.printf("%d ", A[i]);
		}
	}

	// OddOccurrencesInArray
	// A non-empty array A consisting of N integers is given. The array contains an
	// odd number of elements, and each element of the array can be paired with
	// another element that has the same value, except for one element that is left
	// unpaired
	public int solution1(int[] A) {
		Arrays.sort(A);
		for (int i = 0; i < A.length - 1; i += 2) {
			if (A[i] != A[i + 1]) {
				return A[i];
			}
		}
		return A[A.length - 1];
	}

	// CyclicRotation
	// An array A consisting of N integers is given. Rotation of the array means
	// that each element is shifted right by one index, and the last element of the
	// array is moved to the first place
	public int[] solution2(int[] A, int K) {
		final int A_LEN = A.length;
		int arrTemp[] = new int[A_LEN - 1];
		int tempNum = 0;
		for (int k = 0; k < K; k++) {
			tempNum = A[A_LEN - 1];
			arrTemp = Arrays.copyOfRange(A, 0, A_LEN - 1);
			// print(arrTemp);
			// System.out.println();
			for (int i = 0; i < A_LEN; i++) {
				if (i == 0) {
					A[i] = tempNum;
				} else {
					A[i] = arrTemp[i - 1];
				}
			}
		}
		return A;
	}

	// CyclicRotation
	// An array A consisting of N integers is given. Rotation of the array means
	// that each element is shifted right by one index, and the last element of the
	// array is moved to the first place
	public int[] solution3(int[] A, int K) {
		int[] shiftedArray = new int[A.length];
		for (int i = 0; i < A.length; i++) {
			if (i + K >= A.length)
				shiftedArray[(i + K) % A.length] = A[i];
			else
				shiftedArray[i + K] = A[i];
		}
		return shiftedArray;
	}

	// FrogJmp
	// A small frog wants to get to the other side of the road.
	public int solution4(int X, int Y, int D) {
		return (int) Math.ceil((double) (Y - X) / (double) D);
	}

	// BinaryGap
	// A binary gap within a positive integer N is any maximal sequence of
	// consecutive zeros that is surrounded by ones at both ends in the binary
	// representation of N.
	public int solution5(int N) {
		N >>>= Integer.numberOfTrailingZeros(N);
		int steps = 0;
		while ((N & (N + 1)) != 0) {
			N |= N >>> 1;
			steps++;
		}
		return steps;
	}

	// PermMissingElem
	// An array A consisting of N different integers is given. The array contains
	// integers in the range [1..(N + 1)], which means that exactly one element is
	// missing.
	public int solution6(int[] A) {
		Arrays.sort(A);
		int missing = 1;
		for (int i = 0; i < A.length; i++) {
			if (A[i] == missing) {
				missing++;
			}
		}
		return missing;
	}

	// TapeEquilibrium
	// A non-empty array A consisting of N integers is given. Array A represents
	// numbers on a tape.
	public int solution7(int[] A) {
		// currentDiff is right - left for the current partition.
		// Initially, we partition at P = 1.
		long currentDiff = -A[0];

		for (int i = 1; i < A.length; i++) {
			currentDiff += A[i];
		}

		// Now test each partition. minDiff is the minimum difference
		// found so far.
		long minDiff = Math.abs(currentDiff);
		for (int i = 1; i < A.length - 1; i++) {
			currentDiff -= 2 * A[i]; // A[i] moves from right to left.
			minDiff = Math.min(minDiff, Math.abs(currentDiff));
		}
		return (int) minDiff;
	}

	// PermCheck
	// A non-empty array A consisting of N integers is given.
	// A permutation is a sequence containing each element from 1 to N once, and
	// only once.
	public int solution8(int[] A) {
		Set<Integer> set = new HashSet<>();
		// calculating sum of permutation elements
		int sum = A.length * (A.length + 1) / 2;
		for (int i = 0; i < A.length; i++) {
			set.add(A[i]);
			sum -= A[i];
		}
		// return 0 if either sizes don't much or there was some
		// number/s missing in permutation and thus sum !=0;
		return ((set.size() == A.length) && sum == 0) ? 1 : 0;
	}

	// FrogRiverOne
	// A small frog wants to get to the other side of a river. The frog is initially
	// located on one bank of the river (position 0) and wants to get to the
	// opposite bank (position X+1).
	// Leaves fall from a tree onto the surface of the river.
	public int solution9(int X, int[] A) {
		int steps = -1;
		Set<Integer> values = new TreeSet<Integer>();
		for (int i = 0; i < A.length; i++) {
			if (A[i] <= X) {
				values.add(A[i]);
			}
			if (values.size() == X) {
				steps = i;
				break;
			}
		}
		return steps;
	}
	
	

	// MaxCounters
	// You are given N counters, initially set to 0, and you have two possible
	// operations on them:
	public int[] solution10(int N, int[] A) {
		int counter[] = new int[N];
		final int n = A.length;
		int max = -1, current_min = 0;

		for (int i = 0; i < n; i++) {
			if (A[i] >= 1 && A[i] <= N) {
				if (counter[A[i] - 1] < current_min) {
					counter[A[i] - 1] = current_min;
				}
				counter[A[i] - 1] = counter[A[i] - 1] + 1;

				if (counter[A[i] - 1] > max) {
					max = counter[A[i] - 1];
				}
			} else if (A[i] == N + 1) {
				current_min = max;
			}
		}
		for (int i = 0; i < N; i++) {
			if (counter[i] < current_min)
				counter[i] = current_min;
		}
		return counter;
	}

	public static void main(String[] args) {
		int[] A = { 17, 4, 2, 3 };
		Solution s = new Solution();
		//
		// s.print(s.solution3(A, 4));
		// System.out.println(s.solution6(A));
		// System.out.println(s.solution7(A));
		// System.out.println(s.solution8(A));
//		System.out.println(s.solution10(5, A));
		
		System.out.println(s.solution("011100"));
	}

}
