package com.company;

class Bee extends Thread {
    private static int honey = 0;
    private static int capacity = 20;
    private static Object sync = new Object();

    private String name;

    public Bee(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 1000000000; i++) {
            synchronized (sync) {
                honey++;
                System.out.println("Total amount of honey is " + honey + " after " + name);
                if(honey >= capacity) {
                    System.out.println("Pot of honey is full!!!\n" + name + " has woken up the bear");
                    System.out.println("Bear is eating...");
                    try {
                        sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Bear has eaten all heney from the pot");
                    honey = 0;
                }
            }
            try {
                sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("finish " + Thread.currentThread().getName());
    }

    void showTest(int n) {
        Bee[] bees = new Bee[n];

        for(int i = 0; i < n; i++)
            bees[i] = new Bee("Bee" + (i + 1));

        for(Bee bee : bees)
            bee.start();
    }
}
