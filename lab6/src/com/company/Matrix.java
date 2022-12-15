package com.company;

import java.util.Random;

public class Matrix {
    private int size;
    private int[] values;

    public Matrix(int size) {
        this.size = size;
        this.values = new int[size * size];
    }

    public void setValue(int row, int col, int value) {
        this.values[row * size + col] = value;
    }

    public int getValue(int row, int col) {
        return this.values[row * size + col];
    }

    public int getSize() {
        return this.size;
    }

    public void fillRandom() {
        Random random = new Random();
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                this.setValue(i, j, random.nextInt(10));
            }
        }
    }

    public Matrix multiplyNaive(Matrix other) {
        Matrix result = new Matrix(this.size);
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                for (int k = 0; k < this.size; k++) {
                    result.setValue(i, j, result.getValue(i, j) + this.getValue(i, k) * other.getValue(k, j));
                }
            }
        }
        return result;
    }

    public void output() {
        System.out.println("Matrix " + this.size + "x" + this.size + ":");

        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                System.out.print(this.getValue(i, j) + " ");
            }
            System.out.println();
        }

        System.out.println("------------------------------");
    }

}

