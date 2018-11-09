//package com.papalitsas;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

public class QubitNManager {

    // The qubits
    private ArrayList<QubitN> elements;

    //
    private ArrayList<Double> eigenValues;

    private ArrayList<Node2>nodes;

    private int qubitNumber;


    public QubitNManager(  ArrayList<Node2> nodes) {
        this.nodes = nodes;
    }

    public ArrayList<Node2> getNodes() {
        return nodes;
    }

    public void prepeareAll() {
        // calculate the number of qubitNumbers
        calculateQubitNumber();

        // generate the qubitNumbers
        elements = new ArrayList<QubitN>(qubitNumber);
        Random rand = new Random();
        for ( int i = 0 ; i < qubitNumber; i++) {
            double val;
            do { // we dont need 1 and 0 values
                val = rand.nextDouble();
            }while (val == 0.0 || val == 1.0);
            elements.add(new QubitN(val));
        }

        // we generate the eigen values
        generateEigenValues();

        //  we random map the eigen values to nodes.
        // Not all eigen values are mapped.
        
             
        int epos = -1;
        for(int i = 0 ; i < nodes.size(); i++) {

        	//-level;
        	
            do {
                epos = rand.nextInt(eigenValues.size());
            } while ( eigenValues.get(epos) == -1 );
            nodes.get(i).setEigenValue(eigenValues.get(epos));
            eigenValues.set(epos, new Double(-1));
          
        }        
        // we sort the nodes using the eigen values
        Collections.sort(nodes, new Comparator<Node2>() {
            @Override
            public int compare(Node2 lhs, Node2 rhs) {
                // -1 - less than, 1 - greater than, 0 - equal,
                return lhs.getEigenValue() > rhs.getEigenValue() ? -1 : (lhs.getEigenValue() < rhs.getEigenValue() ) ? 1 : 0;
            }
        });

        // at this point the nodes are ordered according to random selection of the eigen values.
    

    }

    private void calculateQubitNumber() {
        int nodeSize = nodes.size();
        int res = 2;
        qubitNumber = 1;
        do {
            res = res *2;
            qubitNumber++;
        } while (nodeSize > res);
    }

    public ArrayList<QubitN> getElements() {
        return elements;
    }

    public void setElements(ArrayList<QubitN> elements) {
        this.elements = elements;

    }


    private void generateEigenValues( ) {

        int power2 = (int)Math.pow(2,elements.size());
        eigenValues = new ArrayList<Double>(power2);

        for (int i = 0 ; i < power2 ; i++) {
            //System.out.println("NUMBER:" + i);
            eigenValues.add(generateInternal(i));
        }
    }


    private double generateInternal(  int number ) {
        double res;
        int mask = 1;
        int pos;
        pos = number & mask;
		
        res = elements.get(0).getNumber(  number & mask );

        for (int i = 1 ; i < elements.size(); i++ ) {
            mask = 1 << i;
            pos = (number & mask)!= 0 ? 1 : 0 ;
            //System.out.print("   "+pos);
            res = res * elements.get(i).getNumber( pos);
        }
        return res;
    }

}
