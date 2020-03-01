package com.expressionlambda.util.fuction;


import java.util.function.BiFunction;
import java.util.function.Function;

public class FunctionExample {

    public static void main(String[] args) {
        // Function which takes in a number
        // and returns half of it
        Function<Integer, Double> half = a -> (Double) (a / 2.0);
        // apply the function to get the result
        System.out.println(half.apply(10));

        BiFunction<Integer, Integer, Double> divide = (a, b) -> {
            return (double) (a / b);
        };

        System.out.println(divide.apply(10, 2));
    }
}