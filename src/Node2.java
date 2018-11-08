//package com.papalitsas;

import java.util.ArrayList;
import java.util.Comparator;


public class Node2 {

    private double eigenValue = -1;

    private int number; //CUST_NO
    private Double x;
    private Double y;
    
    public Node2 (Node2 a){
        this.number=a.number;
        this.x = a.x;
        this.y = a.y;    
    }
    public Node2(int number,Double x, Double y) {
        this.number=number;
        this.x = x;
        this.y = y;
    }
    public Node2() {

    }
    //GETTERS KAI SETTERS

    public double getEigenValue() {
        return eigenValue;
    }
    public void setEigenValue(double value) {
        eigenValue = value;
    }

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }

    public int getNumber() {
        return number;
    }
    //ΜΟΥ ΕΠΙΣΤΡΕΦΕΙ ΤΙΣ ΤΙΜΕΣ ΤΩΝ ΙΔΙΟΤΗΤΩΝ ΣΕ STRING ΟΤΑΝ ΘΕΛΩ ΝΑ ΤΥΠΩΣΩ ΤΟΝ ΚΟΜΒΟ

    @Override
    public String toString() {
        return "Node [number=" + number + ", x=" + x + ", y=" + y + "]";
    }


}

