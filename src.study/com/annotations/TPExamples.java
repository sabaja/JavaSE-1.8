package com.annotations;

import java.io.Serializable;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.*;

class TPExamples<T extends @TestTP List<T>> extends @TestTP Object implements @TestTP Serializable {
    private static final long serialVersionUID = 1L;
    public static void main(String args[])throws @TestTP Exception {
        byte i =(@TestTP byte)1000;
        Object o = new @TestTP String();
        if (o instanceof @TestTP String) {
            String s = (@TestTP String)o;
        }
        List<@TestTP String> list = null;
        List<? extends @TestTP Serializable> list2 = null;
        @TestTP String array @TestTP[] @TestTP[];
    }
};

@Retention (RetentionPolicy.RUNTIME)
@Target ({
//			ElementType.FIELD,
//			ElementType.TYPE_PARAMETER,
//			ElementType.LOCAL_VARIABLE,
//			ElementType.METHOD,
//			ElementType.ANNOTATION_TYPE,
			ElementType.TYPE_USE
			
		})
@interface TestTP {};