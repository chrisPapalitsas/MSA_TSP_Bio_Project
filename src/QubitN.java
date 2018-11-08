//package com.papalitsas;


public class QubitN {

    private double [] number;


    public QubitN(double n1) {
        number = new double[2];
        number[0] = n1;
        number[1] = Math.sqrt(1- Math.pow(2,n1));
    }


    public double getNumber(int pos) {
        return number[pos];
    }

}
