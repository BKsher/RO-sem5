package com.company;

public class Main {

    private static final int SIZE = 512;

    public static void main(String[] args) {
	    Matrix a = new Matrix(SIZE);
        Matrix b = new Matrix(SIZE);
        a.fillRandom();
        b.fillRandom();
        long start = System.currentTimeMillis();
        Matrix c = a.multiplyNaive(b);
        long stop = System.currentTimeMillis();
        //c.output();
        System.out.println("Time with Naive algorithm: " + (stop - start) + "ms");

        start = System.currentTimeMillis();
        FoxAlgorithm fox = new FoxAlgorithm(1);
        c = fox.multiply(a, b);
        stop = System.currentTimeMillis();
        //c.output();
        System.out.println("Time with Fox algorithm and 1 thread: " + (stop - start) + "ms");

        start = System.currentTimeMillis();
        fox = new FoxAlgorithm(2);
        c = fox.multiply(a, b);
        stop = System.currentTimeMillis();
        //c.output();
        System.out.println("Time with Fox algorithm and 2 thread: " + (stop - start) + "ms");

        start = System.currentTimeMillis();
        fox = new FoxAlgorithm(4);
        c = fox.multiply(a, b);
        stop = System.currentTimeMillis();
        //c.output();
        System.out.println("Time with Fox algorithm and 4 thread: " + (stop - start) + "ms");
        a.fillRandom();
        b.fillRandom();

        start = System.currentTimeMillis();
        StringAlgorithm str = new StringAlgorithm(1);
        c = str.multiply(a, b);
        stop = System.currentTimeMillis();
        //c.output();
        System.out.println("Time with String Scheme algorithm and 1 thread: " + (stop - start) + "ms");

        start = System.currentTimeMillis();
        str = new StringAlgorithm(2);
        c = str.multiply(a, b);
        stop = System.currentTimeMillis();
        //c.output();
        System.out.println("Time with String Scheme algorithm and 2 thread: " + (stop - start) + "ms");

        start = System.currentTimeMillis();
        str = new StringAlgorithm(4);
        c = str.multiply(a, b);
        stop = System.currentTimeMillis();
        //c.output();
        System.out.println("Time with String Scheme algorithm and 4 thread: " + (stop - start) + "ms");
        a.fillRandom();
        b.fillRandom();

        start = System.currentTimeMillis();
        CannonAlgorithm con = new CannonAlgorithm(1);
        c = con.multiply(a, b);
        stop = System.currentTimeMillis();
        //c.output();
        System.out.println("Time with Cannon algorithm and 1 thread: " + (stop - start) + "ms");

        start = System.currentTimeMillis();
        con = new CannonAlgorithm(2);
        c = con.multiply(a, b);
        stop = System.currentTimeMillis();
        //c.output();
        System.out.println("Time with Cannon algorithm and 2 thread: " + (stop - start) + "ms");

        start = System.currentTimeMillis();
        con = new CannonAlgorithm(4);
        c = con.multiply(a, b);
        stop = System.currentTimeMillis();
        //c.output();
        System.out.println("Time with Cannon algorithm and 4 thread: " + (stop - start) + "ms");
    }
}
