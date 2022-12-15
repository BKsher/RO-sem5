package com.company;

public class FoxAlgorithm {
    private int numThreads;

    public FoxAlgorithm(int numThreads) {
        this.numThreads = numThreads;
    }

    // Method that performs parallel matrix multiplication by the Fox method:
    public Matrix multiply(Matrix a, Matrix b) {

        Matrix result = new Matrix(a.getSize());

        int blockSize = a.getSize() / this.numThreads;

        Thread[] threads = new Thread[this.numThreads];

        // Start threads:
        for (int i = 0; i < this.numThreads; i++) {
            int row = i * blockSize;
            threads[i] = new Thread(new FoxMultiplyThread(a, b, result, row, row + blockSize));
            threads[i].start();
        }
        // Wait for all threads to complete:
        for (int i = 0; i < this.numThreads; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return result;
    }
}


