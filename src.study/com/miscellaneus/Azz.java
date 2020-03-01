package com.miscellaneus;

public class Azz {
    public static void main(String[] args) {
        System.out.println("sbtjcp76p17l219i".toUpperCase());
        System.out.println("IT80S0329601601000067037713".length());
        System.out.println("IT97M0558401799000000093357".length());

        AA a = new AA();
        BB bb = a.getBb();
        bb.setAbc(Abc.Avv);
        System.out.println(
                a.getBb()

        );

    }
}

class AA {
    char a;
    char mychar = 'a';
    private BB bb;


    public AA() {
        super();
    }

    public AA(char a, char mychar) {
        super();
        this.a = a;
        this.mychar = mychar;
    }

    void print() {
        a = mychar;
        System.out.println(a);
        a = 123;
        System.out.println(a);
    }

    public BB getBb() {
        return bb;
    }

    public void setBb(BB bb) {
        this.bb = bb;
    }
}

class BB {
    Abc abc;
    private String ss;

    public Abc getAbc() {
        return abc;
    }

    public void setAbc(Abc abc) {
        this.abc = abc;
    }

    public String getSs() {
        return ss;
    }

    public void setSs(String ss) {
        this.ss = ss;
    }

}

enum Abc {
    Avv, Bvv, Cvv
}