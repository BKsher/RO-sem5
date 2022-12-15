package com.company;

public class CannonMultiplyThread implements Runnable {
    private Matrix a;
    private Matrix b;
    private Matrix result;

    private int startRow;
    private int endRow;

    public CannonMultiplyThread(Matrix a, Matrix b, Matrix result, int startRow, int endRow) {
        this.a = a;
        this.b = b;
        this.result = result;
        this.startRow = startRow;
        this.endRow = endRow;
    }

    public void run() {
        for (int i = this.startRow; i < this.endRow; i++) {
            for (int j = 0; j < this.result.getSize(); j++) {
                for (int k = 0; k < this.result.getSize(); k++) {
                    this.result.setValue(i, j, this.result.getValue(i, j) + this.a.getValue(i, k) * this.b.getValue(k, j));
                }
            }
        }
    }
}
