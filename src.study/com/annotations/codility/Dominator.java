package com.annotations.codility;

/**
 * 13 I just had a codility problem give me a hard time and I'm still trying to
 * figure out how the space and time complexity constraints could have been met.
 * 
 * The problem is as follows: A dominant member in the array is one that
 * occupies over half the positions in the array, for example:
 * 
 * {3, 67, 23, 67, 67}
 * 
 * 67 is a dominant member because it appears in the array in 3/5 (>50%)
 * positions.
 * 
 * Now, you are expected to provide a method that takes in an array and returns
 * an index of the dominant member if one exists and -1 if there is none.
 * 
 * Easy, right? Well, I could have solved the problem handily if it were not for
 * the following constraints:
 * 
 * Expected time complexity is O(n) Expected space complexity is O(1) I can see
 * how you could solve this for O(n) time with O(n) space complexities as well
 * as O(n^2) time with O(1) space complexities, but not one that meets both O(n)
 * time and O(1) space.
 * 
 * I would really appreciate seeing a solution to this problem. Don't worry, the
 * deadline has passed a few hours ago (I only had 30 minutes), so I'm not
 * trying to cheat. Thanks.
 * 
 * @author sabaja
 *
 */
public class Dominator {

	public static void main(String[] args) {
		int[] arr = {3, 67, 23, 67, 67};
		solution(arr);
	}

	public static int solution(int[] arr) {
	    int indexOfCandidate = -1;
        int stackCounter = 0, candidate=-1, value=-1, i =0;

        for(int element: arr ) {
            if (stackCounter == 0) {
                value = element;
                ++stackCounter;
                indexOfCandidate = i;
            } else {
                if (value == element) {
                    ++stackCounter;
                } else {
                    --stackCounter;
                }
            }
            ++i;
        }

        if (stackCounter > 0 ) {
            candidate = value;
        } else {
            return -1;
        }

        int countRepetitions = 0;
        for (int element: arr) {
            if( element == candidate) {
                ++countRepetitions;

            }
            if(countRepetitions > (arr.length / 2)) {
                return indexOfCandidate;
            }
        }
        return -1;
    }
}
