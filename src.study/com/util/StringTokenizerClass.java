//	     StringTokenizer is a legacy class that is retained for compatibility reasons
//	     although its use is discouraged in new code.
//	     It is recommended that anyone seeking this functionality use the split method of String
//	     or the java.util.regex package instead.

package com.util;

import java.util.StringTokenizer;

public class StringTokenizerClass {

	public static void main(String[] args) {
	     StringTokenizer st = new StringTokenizer("this is a test");//Default: " " \t \n \r \f
	     while (st.hasMoreTokens()) {
	         System.out.println(st.nextToken());
	     }
	     
	     System.out.println();
	     
	     StringTokenizer st1 = new StringTokenizer("this is a test", "t", true);
	     while (st1.hasMoreTokens()) {
	         System.out.println(st1.nextToken());
	     }
	     
	     System.out.println();

	     StringTokenizer st2 = new StringTokenizer("this is a test", "t", false);
	     while (st2.hasMoreTokens()) {
	         System.out.println(st2.nextToken());
	     }
	}

}
