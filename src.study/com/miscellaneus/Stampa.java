package com.miscellaneus;

public class Stampa {

    public static void main(String[] args) {
        print(6);
    }

    private static void print(final int NUM) {
        for (int i = 0; i < NUM; i++) {
            for (int y = 0; y <= i; y++) {
                System.out.printf("%s", y < i ? "*" : "*\n");
            }
        }
    }
}
