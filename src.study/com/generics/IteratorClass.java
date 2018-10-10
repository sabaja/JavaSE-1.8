package com.generics;

import java.util.ArrayList;
import java.util.Iterator;

public class IteratorClass {
    public static void main(String args[]) {
        ArrayList <Number> list = new ArrayList <Number>();
        list.add(1);
        list.add(2.0F);
        list.add(2.0D);
        list.add(2L);
        Iterator<Number> i = list.iterator();
        while(i.hasNext()) {
            Number n = i.next();
            System.out.println(n.toString());
        }
    }
}
