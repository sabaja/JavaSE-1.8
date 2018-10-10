package com.javaLang;

import java.util.GregorianCalendar;

class WrapperClasses {

	public static void main(String[] args) {
		System.out.println(Integer.parseInt("101001111011", 2));
        byte b = (byte)(128);
        System.out.println(Byte.valueOf(b));
        System.out.println(Byte.toUnsignedInt(b));
        int i = (int)3000000000L;
        System.out.println(Integer.toUnsignedString(i, 10));
        System.out.println(Integer.toUnsignedString(i, 2));
        System.out.println(Integer.toUnsignedString(i, 8));
        System.out.println(Integer.toUnsignedString(i, 16));

	}

}

class Age{
	private int age;
	
	public void setAge(Object age){
		if(age instanceof java.util.Calendar){
			GregorianCalendar newAge = (GregorianCalendar) age;
			this.age = newAge.getFirstDayOfWeek();
			}
		if(age instanceof java.lang.Integer){
			Integer newInt = (Integer) age;
			this.age = newInt.intValue();//
		}
	}
}
